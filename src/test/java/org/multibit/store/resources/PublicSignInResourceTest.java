package org.multibit.store.resources;

import com.google.common.base.Optional;
import org.hamcrest.BaseMatcher;
import org.jasypt.util.password.PasswordEncryptor;
import org.junit.Test;
import org.mockito.Matchers;
import org.multibit.mbm.auth.webform.WebFormClientAuthenticator;
import org.multibit.mbm.auth.webform.WebFormClientCredentials;
import org.multibit.mbm.model.ClientUser;
import org.multibit.store.StoreConfiguration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PublicSignInResourceTest {

  private WebFormClientAuthenticator authenticator=mock(WebFormClientAuthenticator.class);
  private PasswordEncryptor encryptor = mock(PasswordEncryptor.class);
  private HttpServletResponse response = mock(HttpServletResponse.class);

  @Test
  public void testSignIn() throws Exception {

    final UUID sessionToken = UUID.randomUUID();
    final StoreConfiguration storeConfiguration = new StoreConfiguration();

    ClientUser expectedClientUser=new ClientUser();
    expectedClientUser.setSessionToken(sessionToken);

    PublicSignInResource testObject = new PublicSignInResource(storeConfiguration);
    testObject.setAuthenticator(authenticator);

    // Create a matcher for the credentials argument
    BaseMatcher webFormCredentialsMatcher=new BaseMatcher() {
      @Override
      public boolean matches(Object rawCredentials) {
        WebFormClientCredentials credentials = (WebFormClientCredentials) rawCredentials;
        return (
          "alice".equals(credentials.getUsername()) &&
          "{SHA}V1BuYu7hE+IDMzpJaUCk00ryNGY=".equals(credentials.getPasswordDigest()));
      }

      @Override
      public void describeTo(org.hamcrest.Description description) {
        // Ignore
      }

    };

    // Create a matcher for the credentials argument
    BaseMatcher cookieMatcher=new BaseMatcher() {
      @Override
      public boolean matches(Object rawCookie) {
        Cookie cookie = (Cookie) rawCookie;
        return (
          storeConfiguration.getSessionTokenName().equals(cookie.getName()) &&
          sessionToken.toString().equals(cookie.getValue()));
      }

      @Override
      public void describeTo(org.hamcrest.Description description) {
        // Ignore
      }

    };

    // Examine the interactions with the dependencies
    when(authenticator
      .authenticate(Matchers.<WebFormClientCredentials>argThat(webFormCredentialsMatcher)))
      .thenReturn(Optional.of(expectedClientUser));
    when(encryptor
      .encryptPassword("alice1"))
      .thenReturn("TSHOq228zgicDQ/PUqb2G4GomJU8aNbsTVmA990DnY2Aqy5saRMU9obO/CDy0erX");

    testObject.signin("alice", "alice1");

    //verify(response).addCookie(Matchers.<Cookie>argThat(cookieMatcher));
  }
}
