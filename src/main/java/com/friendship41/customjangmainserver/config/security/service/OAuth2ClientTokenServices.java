package com.friendship41.customjangmainserver.config.security.service;

import com.friendship41.customjangmainserver.config.security.data.ClientUser;
import com.friendship41.customjangmainserver.config.security.data.ClientUserRepository;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OAuth2ClientTokenServices implements ClientTokenServices {

  @Autowired
  private ClientUserRepository clientUserRepository;

  @Override
  public OAuth2AccessToken getAccessToken(final OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails,
      final Authentication authentication) {
    ClientUser clientUser = this.getClientUser(authentication);
    String accessToken = clientUser.getAccessToken();
    Calendar expirationDate = clientUser.getAccessTokenValidity();
    if (accessToken == null) {
      log.error("accessToken not found");
      return null;
    }
    DefaultOAuth2AccessToken oAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);
    oAuth2AccessToken.setExpiration(expirationDate.getTime());

    return oAuth2AccessToken;
  }

  @Override
  public void saveAccessToken(final OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails,
      final Authentication authentication, final OAuth2AccessToken oAuth2AccessToken) {
    Calendar expirationDate =Calendar.getInstance();
    expirationDate.setTime(oAuth2AccessToken.getExpiration());
    ClientUser clientUser = null;
    try {
      clientUser = this.getClientUser(authentication);
    } catch (Exception e) {
      log.error("user not found");
      return;
    }
    clientUser.setAccessToken(oAuth2AccessToken.getValue());
    clientUser.setAccessTokenValidity(expirationDate);
    clientUserRepository.save(clientUser);
  }

  @Override
  public void removeAccessToken(final OAuth2ProtectedResourceDetails oAuth2ProtectedResourceDetails,
      final Authentication authentication) {
    ClientUser clientUser = null;
    try {
       clientUser = this.getClientUser(authentication);
    } catch (Exception e) {
      log.error("user not found");
      return;
    }
    clientUser.setAccessToken(null);
    clientUser.setRefreshToken(null);
    clientUser.setAccessTokenValidity(null);
    clientUserRepository.save(clientUser);
  }




  private ClientUser getClientUser(Authentication authentication) {
    ClientUserDetails loggedUser = (ClientUserDetails) authentication.getPrincipal();
    int userId = loggedUser.getClientUser().getId();
    System.out.println("qwerasdf"+clientUserRepository.findAll());
    return clientUserRepository.findById(userId).get();
  }
}
