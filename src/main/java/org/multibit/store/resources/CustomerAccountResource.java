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
import org.multibit.store.views.PublicFreemarkerView;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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
public class CustomerAccountResource extends BaseResource {

  private static final Log LOG = Log.forClass(CustomerAccountResource.class);

  /**
   * Can only use an unsalted one-pass digest to synchronize with the server side
   */
  private final PasswordEncryptor passwordEncryptor = new RFC2307SHAPasswordEncryptor();
  private WebFormClientAuthenticator authenticator = new WebFormClientAuthenticator();

  /**
   * Provide the initial view on to the login/registration page
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView showLogin() {
    // TODO Add i18n
    // TODO Add security (page should only be served through HTTPS)
    return new PublicFreemarkerView("account/login.ftl");
  }

  /**
   * Provide the initial view on to the login/registration page
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

    // Attempt to authenticate

    return new PublicFreemarkerView("account/history.ftl");
  }

  /**
   * Provide the initial view on to the login/registration page
   *
   * @return A localised view containing HTML
   */
  @POST
  @Timed
  @Path("/login")
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView login(
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
      return handleFailedLogin();
    } catch (IllegalStateException e) {
      return handleFailedLogin();
    }

    // Digest the plain password to offer minimal protection in transit
    String digestedPassword = passwordEncryptor.encryptPassword(rawPassword);

    WebFormClientCredentials credentials = new WebFormClientCredentials(rawUsername, digestedPassword);

    // Attempt to authenticate
    try {
      Optional<ClientUser> clientUser = authenticator.authenticate(credentials);

      if (!clientUser.isPresent()) {
        return handleFailedLogin();
      }

    } catch (AuthenticationException e) {
      LOG.error(e,e.getMessage());
      return handleFailedLogin();
    }

    // Must be OK to be here

    // Drop the session cookie on to the user

    return new PublicFreemarkerView("account/history.ftl");
  }

  private PublicFreemarkerView handleFailedLogin() {
    // TODO Provide a failed login message
    // TODO Consider tarpitting based on
    return new PublicFreemarkerView("account/login.ftl");
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
