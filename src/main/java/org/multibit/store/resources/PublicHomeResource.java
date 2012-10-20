package org.multibit.store.resources;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.multibit.store.views.PublicFreemarkerView;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for public home page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
@Path("/")
@Produces(MediaType.TEXT_HTML)
public class PublicHomeResource extends BaseResource {

  /**
   * Provide the initial view on to the system
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView viewHome() {
    // TODO Add i18n
    return new PublicFreemarkerView("store/home.ftl");
  }

  /**
   * Provide the initial view on to the system
   *
   * @return A the favicon images from the assets
   */
  @GET
  @Path("/favicon.ico")
  @Timed
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public Response viewFavicon() {

    InputStream is = PublicHomeResource.class.getResourceAsStream("/assets/favicon.ico");

    return Response.ok(is).build();
  }

}
