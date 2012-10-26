package org.multibit.store.resources;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.dropwizard.views.View;
import com.yammer.metrics.annotation.Timed;
import org.multibit.mbm.api.request.cart.PublicCartItem;
import org.multibit.mbm.auth.Authority;
import org.multibit.mbm.auth.annotation.RestrictedTo;
import org.multibit.mbm.client.PublicMerchantClient;
import org.multibit.mbm.model.ClientCart;
import org.multibit.mbm.model.ClientUser;
import org.multibit.mbm.resources.ResourceAsserts;
import org.multibit.store.model.CartModel;
import org.multibit.store.views.PublicCartView;
import org.multibit.store.views.PublicFreemarkerView;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for public cart page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
@Path("/cart")
@Produces(MediaType.TEXT_HTML)
public class PublicCartResource extends BaseResource {

  /**
   * Provide the current state of the cart
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView viewCart(
    @RestrictedTo({Authority.ROLE_PUBLIC})
    ClientUser publicUser
  ) {

    ClientCart cart= PublicMerchantClient
      .newInstance(getLocale())
      .cart()
      .retrieveCart(publicUser);

    // Populate the model
    CartModel model = new CartModel();
    model.setUser(publicUser);
    model.setCart(cart);

    return new PublicCartView("store/cart.ftl",model);
  }

  /**
   * Update all the items in the cart in a single hit
   *
   * @return A localised view containing HTML
   */
  @POST
  @Timed
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response updateCart(
    @RestrictedTo({Authority.ROLE_PUBLIC})
    ClientUser publicUser,
    MultivaluedMap<String, String> formParams) {

    // Validation
    ResourceAsserts.assertNotNull(formParams,"formParams");

    // Decode the map into cart items
    List<PublicCartItem> cartItems = Lists.newArrayList();
    for (Map.Entry<String, List<String>> formParam:formParams.entrySet()) {
      ResourceAsserts.assertNotNull(formParam.getValue(),"value");
      ResourceAsserts.assertTrue(!formParam.getValue().isEmpty(), "empty");
      Integer quantity;
      try {
        quantity = Integer.valueOf(formParam.getValue().get(0));
      } catch (NumberFormatException e) {
        throw new WebApplicationException(Response.Status.BAD_REQUEST);
      }
      ResourceAsserts.assertPositive(quantity,"quantity");
      PublicCartItem cartItem = new PublicCartItem(formParam.getKey(),quantity);
      cartItems.add(cartItem);
    }

    PublicMerchantClient
      .newInstance(getLocale())
      .cart()
      .updateCartItems(publicUser, cartItems);

    // Use the shopping cart view
    View view = viewCart(publicUser);

    // Refresh the session cookie
    return Response
      .created(UriBuilder.fromResource(PublicCartResource.class).build())
      .cookie(createOrUpdateSessionToken(Optional.of(publicUser)))
      .entity(view)
      .build();
  }

}
