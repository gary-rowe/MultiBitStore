package org.multibit.store.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;

/**
 * <p>Abstract base class to provide the following to subclasses:</p>
 * <ul>
 * <li>Provision of common methods</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public abstract class BaseResource {

  // TODO Verify thread safety
  @Context
  protected UriInfo uriInfo;

  // TODO Verify thread safety
  @Context
  protected HttpHeaders httpHeaders;
}
