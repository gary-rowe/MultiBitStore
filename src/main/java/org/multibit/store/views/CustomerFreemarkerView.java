package org.multibit.store.views;

import org.multibit.mbm.model.ClientCart;

/**
 * <p>View to provide the following to resources:</p>
 * <ul>
 * <li>Representation of a simple page with only User customisation</li>
 * </ul>
 *
 * @since 0.0.1
 *
 */
public class CustomerFreemarkerView extends PublicFreemarkerView {

  private final ClientCart cart;

  public CustomerFreemarkerView(String templateName,
                                ClientCart cart) {
    super(templateName);
    this.cart = cart;
  }

  public ClientCart getCart() {
    return cart;
  }
}
