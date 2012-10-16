package org.multibit.store.views;

import org.multibit.store.model.Item;

/**
 * <p>View to provide the following to resources:</p>
 * <ul>
 * <li>Representation of the public item detail page</li>
 * </ul>
 *
 * @since 0.0.1
 *
 * TODO Add support for backing bean with i18n etc
 *         
 */
public class PublicItemView extends PublicFreemarkerView {

  private final Item item;

  public PublicItemView(String templateName, Item item) {
    super(templateName);
    this.item=item;
  }

  public Item getItem() {
    return item;
  }

}
