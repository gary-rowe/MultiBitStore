package org.multibit.store.resources;

import com.google.common.base.Optional;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.multibit.mbm.client.PublicMerchantClient;
import org.multibit.mbm.model.PublicItem;
import org.multibit.mbm.resources.ResourceAsserts;
import org.multibit.store.views.PublicFreemarkerView;
import org.multibit.store.views.PublicItemView;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for public Item page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
@Path("/item")
@Produces(MediaType.TEXT_HTML)
public class PublicItemResource extends BaseResource {

  /**
   * Find a single item based on its Stock Keeping Unit (SKU)
   *
   * @return A localised view containing HTML
   */
  @GET
  @Path("/{sku}")
  @Timed
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView retrieveBySku(@PathParam("sku") String rawSku ) {

    // TODO Validate the SKU

    // TODO Retrieve the Item from MBM with i18n
    Optional<PublicItem> item = PublicMerchantClient
      .newInstance(getLocale())
      .item()
      .retrieveBySku(rawSku);
    ResourceAsserts.assertPresent(item,"item");

    return new PublicItemView("store/item.ftl",item.get());
  }

}
