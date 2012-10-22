package org.multibit.store.resources;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.multibit.mbm.auth.Authority;
import org.multibit.mbm.auth.annotation.RestrictedTo;
import org.multibit.mbm.model.ClientUser;
import org.multibit.store.views.CustomerFreemarkerView;
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
 * <li>Provision of configuration for customer history page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
@Path("/history")
@Produces(MediaType.TEXT_HTML)
public class CustomerHistoryResource extends BaseResource {

  /**
   * Provide the initial view on to the system
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView showHistory(
    @RestrictedTo({Authority.ROLE_CUSTOMER}) ClientUser user) {
    return new CustomerFreemarkerView("account/history.ftl", user);
  }

}
