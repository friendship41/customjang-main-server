package com.friendship41.customjangmainserver.service;

import com.friendship41.customjangmainserver.data.security.ClientUser;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service("AuthService")
public class AuthServiceImpl implements AuthService {
  private RestTemplate restTemplate;

  public AuthServiceImpl(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  @Override
  public ClientUser getClientUser(final String accessToken) {
    return null;
  }

}
