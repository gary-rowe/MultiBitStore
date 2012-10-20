package org.multibit.store.resources;

import com.yammer.dropwizard.logging.Log;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
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

    // TODO Remove this
    LOG.error(runtime,runtime.getMessage());

    // Build default response
    // TODO Render the HTML response using the error resource without a misleading redirect
    Response defaultResponse = Response.seeOther(
      UriBuilder.fromResource(PublicErrorResource.class).path("{status}").build(500)
    ).build();

    // Check for any specific handling
    if (runtime instanceof WebApplicationException) {

      return handleWebApplicationException(runtime, defaultResponse);
    }

    // Use the default
    return defaultResponse;

  }

  private Response handleWebApplicationException(RuntimeException exception, Response defaultResponse) {
    WebApplicationException webAppException = (WebApplicationException) exception;

    // No logging
    if (webAppException.getResponse().getStatus() == 404) {
      // TODO Render the 404 HTML response using the error resource without a misleading redirect
      return Response.seeOther(
        UriBuilder.fromResource(PublicErrorResource.class).path("{status}").build(404)
      ).build();
    }

    // Debug logging

    // Warn logging

    // Error logging
    LOG.error(exception, exception.getMessage());

    return defaultResponse;
  }

}
