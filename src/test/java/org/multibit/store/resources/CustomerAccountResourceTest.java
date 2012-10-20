package org.multibit.store.resources;

import com.google.common.base.Optional;
import org.hamcrest.BaseMatcher;
import org.jasypt.util.password.PasswordEncryptor;
import org.junit.Test;
import org.mockito.Matchers;
import org.multibit.mbm.auth.webform.WebFormClientAuthenticator;
import org.multibit.mbm.auth.webform.WebFormClientCredentials;
import org.multibit.mbm.model.ClientUser;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerAccountResourceTest {

  private WebFormClientAuthenticator authenticator=mock(WebFormClientAuthenticator.class);
  private PasswordEncryptor encryptor = mock(PasswordEncryptor.class);

  @Test
  public void testLogin() throws Exception {

    ClientUser expectedClientUser=new ClientUser();
    expectedClientUser.setUsername("alice");

    CustomerAccountResource testObject = new CustomerAccountResource();
    testObject.setAuthenticator(authenticator);

    // Create a matcher for the credentials argument
    BaseMatcher webFormCredentialsMatcher=new BaseMatcher() {
      @Override
      public boolean matches(Object rawCredentials) {
        WebFormClientCredentials credentials = (WebFormClientCredentials) rawCredentials;
        return (
          "alice".equals(credentials.getUsername()));
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


    testObject.login("alice","alice1");

  }
}
