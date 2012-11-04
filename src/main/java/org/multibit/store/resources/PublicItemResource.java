package org.multibit.store.resources;

import com.google.common.base.Optional;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.multibit.mbm.api.hal.HalMediaType;
import org.multibit.mbm.client.PublicMerchantClient;
import org.multibit.mbm.model.ClientItem;
import org.multibit.mbm.resources.ResourceAsserts;
import org.multibit.store.model.BaseModel;
import org.multibit.store.model.ItemModel;
import org.multibit.store.views.PublicFreemarkerView;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
@Produces(HalMediaType.TEXT_HTML_UTF8)
public class PublicItemResource extends BaseResource {

  /**
   * Find a single item based on its Stock Keeping Unit (SKU)
   *
   * @return A localised view containing HTML
   */
  @GET
  @Path("/{sku}")
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView retrieveBySku(
    @PathParam("sku") String rawSku ) {

    // TODO Validate the SKU

    // TODO Retrieve the Item from MBM with i18n
    Optional<ClientItem> item = PublicMerchantClient
      .newInstance(getLocale())
      .item()
      .retrieveBySku(rawSku);
    ResourceAsserts.assertPresent(item,"item");

    // Populate the model
    ItemModel model = new ItemModel(item.get());
    model.setCart(populateCartSummary());
    model.setUser(getClientUserFromSession().orNull());

    return new PublicFreemarkerView<BaseModel>("store/item.ftl",model);
  }

}
