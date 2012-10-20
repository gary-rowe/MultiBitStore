package org.multibit.store.resources;

import com.yammer.dropwizard.logging.Log;
import org.multibit.store.views.PublicFreemarkerView;

import javax.ws.rs.WebApplicationException;
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

  @Override
  public Response toResponse(RuntimeException runtime) {

    // Build default response
    Response defaultResponse = Response
      .serverError()
      .entity(new PublicFreemarkerView("error/500.ftl"))
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
        .entity(new PublicFreemarkerView("error/401.ftl"))
        .build();
    }
    if (webAppException.getResponse().getStatus() == 404) {
      return Response
        .status(Response.Status.NOT_FOUND)
        .entity(new PublicFreemarkerView("error/404.ftl"))
        .build();
    }

    // Debug logging

    // Warn logging

    // Error logging
    LOG.error(exception, exception.getMessage());

    return defaultResponse;
  }

}
