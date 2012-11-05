package org.multibit.store.resources;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.multibit.store.model.BaseModel;
import org.multibit.store.views.PublicFreemarkerView;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for public category page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
@Path("/checkout")
@Produces(MediaType.TEXT_HTML)
public class PublicCheckoutResource extends BaseResource {

  /**
   * Provide the initial view on to the system
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView retrieveAllByPage() {

    // Populate the model
    BaseModel model = newBaseModel();

    if (getClientUserFromSession().isPresent()) {
      return new PublicFreemarkerView<BaseModel>("store/checkout.ftl", model);
    }

    // Default is anonymous
    return new PublicFreemarkerView<BaseModel>("store/checkout-guest.ftl", model);

  }

}
