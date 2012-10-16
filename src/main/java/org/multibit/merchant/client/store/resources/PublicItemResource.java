package org.multibit.merchant.client.store.resources;

import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.multibit.merchant.client.store.model.Item;
import org.multibit.merchant.client.store.views.PublicFreemarkerView;
import org.multibit.merchant.client.store.views.PublicItemView;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
public class PublicItemResource {



  /**
   * Provide the initial view on to the system
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(maxAge = 5, maxAgeUnit = TimeUnit.MINUTES)
  public PublicFreemarkerView retrieveAllByPage() {

    // TODO Retrieve the Item from MBM with i18n
    Item item = new Item();
    item.setName("Gary's iPod!");

    return new PublicItemView("store/item.ftl",item);
  }

}
