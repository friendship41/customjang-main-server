package com.friendship41.customjangmainserver.config.security.data;

import com.friendship41.customjangmainserver.config.security.data.ClientUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientUserRepository extends JpaRepository<ClientUser, Integer> {
  Optional<ClientUser> findByUsername(String username);
}
