package org.multibit.store.model;

import org.multibit.mbm.model.ClientCart;
import org.multibit.mbm.model.ClientUser;

/**
 * <p>Base class to provide the following to views:</p>
 * <ul>
 * <li>Access to common data (user, cart, adverts etc)</li>
 *
 * @since 0.0.1
 *         
 */
public class BaseModel {

  private ClientUser user;
  private ClientCart cart;

  // TODO Add preferences

  public ClientUser getUser() {
    return user;
  }

  public void setUser(ClientUser user) {
    this.user = user;
  }

  public ClientCart getCart() {
    return cart;
  }

  public void setCart(ClientCart cart) {
    this.cart = cart;
  }
}
