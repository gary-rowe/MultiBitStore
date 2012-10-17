package org.multibit.store.resources;

import com.yammer.dropwizard.client.JerseyClient;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Locale;

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

  // All resources must allow for upstream communication
  protected final JerseyClient jerseyClient;

  protected final URI mbmBaseUri;

  /**
   * @param jerseyClient The {@link com.yammer.dropwizard.client.JerseyClient} for upstream communication
   * @param mbmBaseUri   The MBM base URI to locate the upstream server
   */
  public BaseResource(JerseyClient jerseyClient, URI mbmBaseUri) {
    this.jerseyClient = jerseyClient;
    this.mbmBaseUri = mbmBaseUri;
  }

  /**
   * @return The most appropriate locale for the upstream request (never null)
   */
  public Locale getLocale() {
    Locale locale = httpHeaders.getLanguage();
    if (locale == null) {
      // TODO This should be a configuration setting
      locale = Locale.getDefault();
    }
    return locale;
  }

  public URI getMbmBaseUri() {
    return mbmBaseUri;
  }
}
