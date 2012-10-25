package org.multibit.store.resources;

import com.google.common.base.Optional;
import com.sun.jersey.api.core.HttpContext;
import com.yammer.dropwizard.logging.Log;
import org.multibit.mbm.model.ClientUser;
import org.multibit.store.model.BaseModel;
import org.multibit.store.views.PublicFreemarkerView;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * <p>Provider to provide the following to Jersey framework:</p>
 * <ul>
 * <li>Provision of exception to response mapping</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {

  private static final Log LOG = Log.forClass(RuntimeExceptionMapper.class);

  @Context
  HttpContext httpContext;

  @Override
  public Response toResponse(RuntimeException runtime) {

    // Build default response
    Response defaultResponse = Response
      .serverError()
      .entity(new PublicErrorResource().view500())
      .build();

    // Check for any specific handling
    if (runtime instanceof WebApplicationException) {

      return handleWebApplicationException(runtime, defaultResponse);
    }

    // Use the default
    LOG.error(runtime, runtime.getMessage());
    return defaultResponse;

  }

  private Response handleWebApplicationException(RuntimeException exception, Response defaultResponse) {
    WebApplicationException webAppException = (WebApplicationException) exception;

    // No logging
    if (webAppException.getResponse().getStatus() == 401) {
      return Response
        .status(Response.Status.UNAUTHORIZED)
        .entity(new PublicErrorResource().view401())
        .build();
    }
    if (webAppException.getResponse().getStatus() == 404) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .entity(new PublicErrorResource().view404())
        .build();
    }

    // Debug logging

    // Warn logging

    // Error logging
    LOG.error(exception, exception.getMessage());

    return defaultResponse;
  }

}
