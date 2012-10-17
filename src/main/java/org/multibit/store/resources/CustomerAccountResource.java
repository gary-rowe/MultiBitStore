package org.multibit.store.resources;

import com.yammer.dropwizard.client.JerseyClient;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.multibit.store.views.PublicFreemarkerView;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.net.URI;
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

  /**
   * @param jerseyClient The {@link com.yammer.dropwizard.client.JerseyClient} for upstream communication
   * @param mbmBaseUri   The MBM base URI to locate the upstream server
   */
  public CustomerAccountResource(JerseyClient jerseyClient, URI mbmBaseUri) {
    super(jerseyClient, mbmBaseUri);
  }

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
    return new PublicFreemarkerView("account/account.ftl");
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
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView login(
    @FormParam("username") String rawUsername,
    @FormParam("password") String rawPassword) {

    // Attempt to authenticate

    return new PublicFreemarkerView("account/history.ftl");
  }


}
