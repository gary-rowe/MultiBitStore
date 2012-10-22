package org.multibit.store.views;

import com.google.common.base.Optional;
import com.yammer.dropwizard.views.View;
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
public class PublicRememberedView extends View {

  private final ClientUser rememberedUser;

  public PublicRememberedView(String templateName, Optional<ClientUser> rememberedUser) {
    super(templateName);
    if (!rememberedUser.isPresent()) {
      this.rememberedUser = null;
    } else {
      this.rememberedUser = rememberedUser.get();
    }
  }


}
