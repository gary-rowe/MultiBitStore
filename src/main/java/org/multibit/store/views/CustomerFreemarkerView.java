package org.multibit.store.views;

import org.multibit.mbm.model.ClientUser;

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

  private final ClientUser authenticatedUser;

  public CustomerFreemarkerView(String templateName, ClientUser authenticatedUser) {
    super(templateName);
    this.authenticatedUser = authenticatedUser;
  }

  public ClientUser getAuthenticatedUser() {
    return authenticatedUser;
  }
}
