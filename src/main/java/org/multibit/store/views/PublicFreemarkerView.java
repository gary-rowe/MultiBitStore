package org.multibit.store.views;

import com.yammer.dropwizard.views.View;

/**
 * <p>View to provide the following to resources:</p>
 * <ul>
 * <li>Representation of a simple page with only User customisation</li>
 * </ul>
 *
 * @since 0.0.1
 *
 * TODO Add support for backing bean with i18n etc
 *         
 */
public class PublicFreemarkerView extends View {

  public PublicFreemarkerView(String templateName) {
    super("/views/ftl/"+templateName);
  }

}
