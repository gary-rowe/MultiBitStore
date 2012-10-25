package org.multibit.store.resources;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.yammer.dropwizard.auth.AuthenticationException;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.dropwizard.logging.Log;
import com.yammer.metrics.annotation.Timed;
import org.jasypt.util.password.PasswordEncryptor;
import org.jasypt.util.password.rfc2307.RFC2307SHAPasswordEncryptor;
import org.multibit.mbm.auth.webform.WebFormClientAuthenticator;
import org.multibit.mbm.auth.webform.WebFormClientCredentials;
import org.multibit.mbm.model.ClientUser;
import org.multibit.store.model.BaseModel;
import org.multibit.store.views.PublicFreemarkerView;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for customer account page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
@Path("/account")
@Produces(MediaType.TEXT_HTML)
public class PublicSignInResource extends BaseResource {

  private static final Log LOG = Log.forClass(PublicSignInResource.class);

  /**
   * Can only use an unsalted one-pass digest to synchronize with the server side
   */
  private final PasswordEncryptor passwordEncryptor = new RFC2307SHAPasswordEncryptor();
  private WebFormClientAuthenticator authenticator = new WebFormClientAuthenticator();

  /**
   * Provide the initial view on to the signin/registration page
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public Response showSignin() {

    // Populate the model
    BaseModel model = newBaseModel(Optional.<ClientUser>absent());

    return Response.ok(new PublicFreemarkerView<BaseModel>("account/signin.ftl",model))
      .cookie(invalidateSessionToken()) // Secure
      .build();
  }

  /**
   * Provide the initial view on to the signin/registration page
   *
   * @return A localised view containing HTML
   */
  @POST
  @Timed
  @Path("/register")
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView register(
    @FormParam("username") String rawUsername,
    @FormParam("password") String rawPassword) {

    // TODO Attempt to authenticate

    // Populate the model
    BaseModel model = newBaseModel(Optional.<ClientUser>absent());

    return new PublicFreemarkerView<BaseModel>("account/history.ftl",model);
  }

  /**
   * Provide the initial view on to the signin/registration page
   *
   * @return A localised view containing HTML
   */
  @POST
  @Timed
  @Path("/signin")
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public Response signin(
    @FormParam("username") String rawUsername,
    @FormParam("password") String rawPassword) {

    // Input validation
    try {
      Preconditions.checkNotNull(rawUsername);
      Preconditions.checkState(rawUsername.length() > 0);
      Preconditions.checkState(rawUsername.length() < 30);
      Preconditions.checkNotNull(rawPassword);
      Preconditions.checkState(rawPassword.length() > 0);
      Preconditions.checkState(rawPassword.length() < 50);
    } catch (NullPointerException e) {
      return handleFailedSignIn();
    } catch (IllegalStateException e) {
      return handleFailedSignIn();
    }

    // Digest the plain password to offer minimal protection in transit
    String digestedPassword = passwordEncryptor.encryptPassword(rawPassword);

    WebFormClientCredentials credentials = new WebFormClientCredentials(rawUsername, digestedPassword);

    // Attempt to authenticate
    Optional<ClientUser> clientUser;
    try {
      clientUser = authenticator.authenticate(credentials);

      if (!clientUser.isPresent()) {
        return handleFailedSignIn();
      }

    } catch (AuthenticationException e) {
      LOG.error(e, e.getMessage());
      return handleFailedSignIn();
    }

    // Must be OK to be here
    return Response
      .seeOther(UriBuilder.fromResource(CustomerHistoryResource.class).build())
      .cookie(createOrUpdateSessionToken(clientUser))
      .build();
  }

  @GET
  @Path("/signout")
  public Response signout() {

    // Populate the model
    BaseModel model = newBaseModel(Optional.<ClientUser>absent());

    return Response
      .ok(new PublicFreemarkerView<BaseModel>("store/signout.ftl",model))
      .cookie(invalidateSessionToken())
      .build();
  }

  private Response handleFailedSignIn() {

    // TODO Provide a failed sign in alert across the top of the page
    // TODO Consider tarpitting based on

    // Populate the model
    BaseModel model = newBaseModel(Optional.<ClientUser>absent());

    // Ensure we erase any local cookies just in case
    return Response.ok(new PublicFreemarkerView<BaseModel>("account/signin.ftl",model))
      .cookie(invalidateSessionToken())
      .build();
  }

  /**
   * Reduced visibility for testing
   *
   * @param authenticator An alternative to the default
   */
  /* package */ void setAuthenticator(WebFormClientAuthenticator authenticator) {
    this.authenticator = authenticator;
  }

}
