package org.multibit.store.resources;

import com.google.common.base.Optional;
import com.yammer.dropwizard.jersey.caching.CacheControl;
import com.yammer.metrics.annotation.Timed;
import org.multibit.mbm.model.ClientUser;
import org.multibit.store.model.BaseModel;
import org.multibit.store.views.PublicFreemarkerView;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;

/**
 * <p>Resource to provide the following to application:</p>
 * <ul>
 * <li>Provision of configuration for customer registration page</li>
 * </ul>
 *
 * @since 0.0.1
 */
@Component
@Path("/register")
@Produces(MediaType.TEXT_HTML)
public class CustomerRegisterResource extends BaseResource {

  /**
   * Provide the initial view on to the system
   *
   * @return A localised view containing HTML
   */
  @GET
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView showRegistration() {

    // Populate the model
    BaseModel model = newBaseModel();

    return new PublicFreemarkerView<BaseModel>("account/register.ftl",model);
  }

  /**
   * Provide the initial view on to the system
   *
   * @return A localised view containing HTML
   */
  @POST
  @Timed
  @CacheControl(noCache = true)
  public PublicFreemarkerView handleRegistration(
    @FormParam("username") String rawUsername,
    @FormParam("password1") String rawPassword1,
    @FormParam("password2") String rawPassword2
    ) {

    // TODO Add the client registration call

    // Populate the model
    BaseModel model = newBaseModel();

    return new PublicFreemarkerView<BaseModel>("account/profile.ftl",model);
  }

}
