package org.multibit.store.model;

import org.multibit.mbm.model.ClientItem;

import java.util.List;

/**
 * <p>Base class to provide the following to views:</p>
 * <ul>
 * <li>Access to common data (user, cart, adverts etc)</li>
 *
 * @since 0.0.1
 *         
 */
public class HomeModel extends BaseModel {

  private final List<ClientItem> promotionalItems;

  public HomeModel(List<ClientItem> promotionalItems) {
    this.promotionalItems = promotionalItems;
  }

  public List<ClientItem> getPromotionalItems() {
    return promotionalItems;
  }
}
