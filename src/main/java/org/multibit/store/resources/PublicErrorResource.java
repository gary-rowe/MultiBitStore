package org.multibit.store.resources;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.multibit.store.views.PublicFreemarkerView;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of demonstration error pages</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
@Path("/error")
@Produces(MediaType.TEXT_HTML)
public class PublicErrorResource extends BaseResource {

  /**
   * Provide the 404 error page
   *
   * @return A localised view containing HTML
   */
  @GET
  @Path("/404")
  @Timed
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView view404() {
    return new PublicFreemarkerView("error/404.ftl");
  }

  /**
   * Provide the 404 error page
   *
   * @return A localised view containing HTML
   */
  @GET
  @Path("/500")
  @Timed
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView view500() {
    return new PublicFreemarkerView("error/500.ftl");
  }

}
