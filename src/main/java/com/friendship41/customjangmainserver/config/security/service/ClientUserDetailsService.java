package com.friendship41.customjangmainserver.config.security.service;

import com.friendship41.customjangmainserver.config.security.data.ClientUser;
import com.friendship41.customjangmainserver.config.security.data.ClientUserRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ClientUserDetailsService implements UserDetailsService {
  @Autowired
  private ClientUserRepository clientUserRepository;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    Optional<ClientUser> optionalClientUser = clientUserRepository.findByUsername(username);
    System.out.println("qwer"+clientUserRepository.findAll());
    if (!optionalClientUser.isPresent()) {
      throw new UsernameNotFoundException("invalid id or password");
    }


    return new ClientUserDetails(optionalClientUser.get());
  }
}
