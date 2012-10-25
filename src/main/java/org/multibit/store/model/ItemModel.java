package org.multibit.store.model;

import org.multibit.mbm.model.ClientItem;

/**
 * <p>Base class to provide the following to views:</p>
 * <ul>
 * <li>Access to common data (user, cart, adverts etc)</li>
 *
 * @since 0.0.1
 *         
 */
public class ItemModel extends BaseModel {

  private final ClientItem item;

  public ItemModel(ClientItem item) {
    this.item = item;
  }

  public ClientItem getItem() {
    return item;
  }
}
