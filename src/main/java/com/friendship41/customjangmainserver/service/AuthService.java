package com.friendship41.customjangmainserver.service;

import com.friendship41.customjangmainserver.data.security.ClientUser;

public interface AuthService {
  ClientUser getClientUser(String accessToken);
}
