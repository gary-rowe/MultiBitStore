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
 * TODO Add support for backing bean with i18n etc
 *             @Context HttpServletResponse response      // Drop the session cookie on to the user
Cookie cookie = new Cookie(MBM_SESSION_TOKEN,clientUser.get().getSessionToken().toString());
// TODO Introduce HTTPS
cookie.setSecure(false);
response.addCookie(cookie);
 
 */
public class CustomerFreemarkerView extends PublicFreemarkerView {

  private final ClientUser clientUser;

  public CustomerFreemarkerView(String templateName, ClientUser clientUser) {
    super("/views/ftl/"+templateName);
    this.clientUser = clientUser;
  }

  public ClientUser getClientUser() {
    return clientUser;
  }
}
