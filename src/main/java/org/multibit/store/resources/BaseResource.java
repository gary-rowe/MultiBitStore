package org.multibit.store.resources;

import com.google.common.base.Optional;
import org.multibit.mbm.auth.InMemorySessionTokenCache;
import org.multibit.mbm.client.PublicMerchantClient;
import org.multibit.mbm.model.ClientCart;
import org.multibit.mbm.model.ClientUser;
import org.multibit.store.StoreConfiguration;
import org.multibit.store.model.BaseModel;

import javax.ws.rs.CookieParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.UriInfo;
import java.util.Locale;
import java.util.UUID;

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
   * The current session token
   */
  @CookieParam(value = StoreConfiguration.SESSION_TOKEN_NAME)
  protected String rawSessionToken;

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
   * @return An empty Cart configured according to user preferences
   */
  public ClientCart getEmptyCart() {
    ClientCart emptyCart = new ClientCart();
    emptyCart.setItemTotal("0");
    emptyCart.setQuantityTotal("0");
    // TODO Link this into the Preferences
    emptyCart.setCurrencyCode("BTC");
    emptyCart.setCurrencySymbol("Ƀ");
    emptyCart.setPriceTotal("0.0000");
    return emptyCart;
  }

  /**
   * @return A summary of their cart
   */
  protected ClientCart populateCartSummary() {

    // Get the current user
    Optional<ClientUser> clientUser = getClientUserFromSession();

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
   * Utility method to create a base model present on all non-authenticated resources
   *
   * @return A base model
   */
  protected BaseModel newBaseModel() {

    // Populate the model
    BaseModel model = new BaseModel();
    model.setCart(populateCartSummary());
    model.setUser(getClientUserFromSession().orNull());

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

  /**
   * @return The user associated with the session token
   */
  protected Optional<ClientUser> getClientUserFromSession() {

    // Fail fast
    if (!isSessionTokenPresent()) {
      return Optional.absent();
    }

    return InMemorySessionTokenCache
      .INSTANCE
      .getBySessionToken(
        Optional.of(
          UUID.fromString(rawSessionToken)));

  }

  /**
   * @return True if a session token was offered in the request
   */
  protected boolean isSessionTokenPresent() {

    return rawSessionToken != null;

  }
}
