package org.multibit.store.resources;

import com.google.common.base.Optional;
import org.multibit.mbm.client.PublicMerchantClient;
import org.multibit.mbm.model.ClientCart;
import org.multibit.mbm.model.ClientUser;
import org.multibit.store.StoreConfiguration;
import org.multibit.store.model.BaseModel;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.UriInfo;
import java.util.Currency;
import java.util.Locale;

/**
 * <p>Abstract base class to provide the following to subclasses:</p>
 * <ul>
 * <li>Provision of common methods</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public abstract class BaseResource {

  /**
   * Jersey creates a fresh resource every request so this is safe
   */
  @Context
  protected UriInfo uriInfo;

  /**
   * Jersey creates a fresh resource every request so this is safe
   */
  @Context
  protected HttpHeaders httpHeaders;

  /**
   * @return The most appropriate locale for the upstream request (never null)
   */
  public Locale getLocale() {
    // TODO This should be a configuration setting
    Locale defaultLocale = Locale.UK;

    Locale locale;
    if (httpHeaders == null) {
      locale = defaultLocale;
    } else {
      locale = httpHeaders.getLanguage();
      if (locale == null) {
        locale = defaultLocale;
      }
    }
    return locale;
  }

  /**
   * @return The most appropriate locale for the upstream request (never null)
   */
  public ClientCart getEmptyCart() {
    Locale locale = getLocale();
    ClientCart emptyCart = new ClientCart();
    emptyCart.setItemTotal("0");
    emptyCart.setQuantityTotal("0");
    emptyCart.setBtcTotal("0.0000");
    emptyCart.setLocalTotal("0.00");
    emptyCart.setLocalSymbol(Currency.getInstance(locale).getSymbol());
    return emptyCart;
  }

  /**
   * @param clientUser The user associated with the session
   *
   * @return A summary of their cart
   */
  protected ClientCart populateCartSummary(Optional<ClientUser> clientUser) {

    // No cart activity in this session
    if (!clientUser.isPresent()) {
      return getEmptyCart();
    }

    return PublicMerchantClient
      .newInstance(getLocale())
      .cart()
      .retrieveCartNoItems(clientUser.get());
  }

  /**
   * @param clientUser The optional client user
   *
   * @return A base model
   */
  protected BaseModel newBaseModel(Optional<ClientUser> clientUser) {
    // Populate the model
    BaseModel model = new BaseModel();
    model.setCart(populateCartSummary(clientUser));
    model.setUser(clientUser.orNull());

    return model;
  }

  /**
   * @return The invalidated session token cookie
   */
  protected NewCookie invalidateSessionToken() {
    return new NewCookie(
      StoreConfiguration.SESSION_TOKEN_NAME,
      "Invalidated",     // Value
      "/",   // Path
      null,   // Domain
      null,   // Comment
      0,      // Max age
      false);
  }

  /**
   * @param clientUser The authenticated user
   *
   * @return The associated session token for subsequent cookie authentication
   */
  protected NewCookie createOrUpdateSessionToken(Optional<ClientUser> clientUser) {
    return new NewCookie(
      StoreConfiguration.SESSION_TOKEN_NAME,
      clientUser.get().getSessionToken().toString(),   // Value
      "/",   // Path
      null,   // Domain
      null,   // Comment
      NewCookie.DEFAULT_MAX_AGE, // Max age - expire on close
      false);
  }
}
