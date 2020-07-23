package com.friendship41.customjangmainserver.config.security;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenProviderChain;
import org.springframework.security.oauth2.client.token.ClientTokenServices;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeAccessTokenProvider;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

@Configuration
@EnableOAuth2Client
public class ClientConfig {
  @Autowired
  private ClientTokenServices clientTokenServices;
  @Autowired
  private OAuth2ClientContext oAuth2ClientContext;

  @Value("${auth.server.uri.access_token}")
  private String accessTokenUri;
  @Value("${auth.server.uri.user_authorization}")
  private String userAuthorizationUri;
  @Value("${auth.server.uri.preSettedRedirectUri}")
  private String preSettedRedirectUri;

  @Bean
  public OAuth2ProtectedResourceDetails authorizationCode() {
    AuthorizationCodeResourceDetails resourceDetails = new AuthorizationCodeResourceDetails();
    resourceDetails.setId("oauth2server");
    resourceDetails.setTokenName("oauth_token");
    resourceDetails.setClientId("customjangMainApp");
    resourceDetails.setClientSecret("123");
    resourceDetails.setAccessTokenUri(accessTokenUri);
    resourceDetails.setUserAuthorizationUri(userAuthorizationUri);
    resourceDetails.setScope(Arrays.asList("read_profile"));
    resourceDetails.setPreEstablishedRedirectUri(preSettedRedirectUri);
    resourceDetails.setUseCurrentUri(false);
    resourceDetails.setClientAuthenticationScheme(AuthenticationScheme.header);
    return resourceDetails;
  }

  @Bean
  public OAuth2RestTemplate oAuth2RestTemplate() {
    OAuth2ProtectedResourceDetails resourceDetails = this.authorizationCode();
    OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(resourceDetails, this.oAuth2ClientContext);
    AccessTokenProviderChain providerChain =
        new AccessTokenProviderChain(Arrays.asList(new AuthorizationCodeAccessTokenProvider()));
    providerChain.setClientTokenServices(this.clientTokenServices);
    restTemplate.setAccessTokenProvider(providerChain);
    return restTemplate;
  }
}
