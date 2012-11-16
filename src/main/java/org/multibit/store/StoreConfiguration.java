package org.multibit.store;

import com.yammer.dropwizard.client.JerseyClientConfiguration;
import com.yammer.dropwizard.config.Configuration;
import org.codehaus.jackson.annotate.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * <p>DropWizard Configuration to provide the following to application:</p>
 * <ul>
 * <li>Initialisation code</li>
 * </ul>
 *
 * @since 0.0.1
 *         
 */
public class StoreConfiguration extends Configuration {

  /**
   * The cookie name for the session token
   */
  public static final String SESSION_TOKEN_NAME ="MBM-Session";

  /**
   * The cookie name for the "remember me" token
   */
  private static final String rememberMeName ="MBM-RememberMe";


  @NotEmpty
  @JsonProperty
  private String assetCachePolicy="maximumSize=10000, expireAfterAccess=5s";

  /**
   * How long a session cookie authentication can remain inactive before the user must signin in
   * TODO Implement this
   */
  @NotEmpty
  @JsonProperty
  private String cookieAuthenticationCachePolicy ="maximumSize=10000, expireAfterAccess=600s";

  /**
   * The base URI to locate the upstream server
   */
  @NotEmpty
  @JsonProperty
  private String serverBaseUri ="http://localhost:8080";

  /**
   * The context to locate the upstream server application
   */
  @JsonProperty
  private String serverContext ="/";

  /**
   * The client API key to allow this application to authenticate with the upstream server
   * This would normally be injected through startup command line parameters
   */
  @NotEmpty
  @JsonProperty
  private String clientApiKey ="store123";

  /**
   * The client secret key to allow this application to authenticate with the upstream server
   * This would normally be injected through startup command line parameters
   */
  @NotEmpty
  @JsonProperty
  private String clientSecretKey ="store456";

  @Valid
  @NotNull
  @JsonProperty
  private JerseyClientConfiguration httpClient = new JerseyClientConfiguration();

  public String getAssetCachePolicy() {
    return assetCachePolicy;
  }

  public JerseyClientConfiguration getJerseyClientConfiguration() {
    return httpClient;
  }

  public String getServerBaseUri() {
    return serverBaseUri;
  }

  public String getServerContext() {
    return "/".equals(serverContext) ? "" : serverContext;
  }

  public String getClientApiKey() {
    return clientApiKey;
  }

  public String getClientSecretKey() {
    return clientSecretKey;
  }

  public String getCookieAuthenticationCachePolicy() {
    return cookieAuthenticationCachePolicy;
  }

  public String getSessionTokenName() {
    return SESSION_TOKEN_NAME;
  }

  public String getRememberMeName() {
    return rememberMeName;
  }
}
