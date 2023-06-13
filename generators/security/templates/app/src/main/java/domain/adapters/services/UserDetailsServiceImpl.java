package <%= packageName %>.domain.adapters.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import <%= packageName %>.infrastructure.adapters.repositories.<%= entityName %>Repository;
import <%= packageName %>.domain.<%= entityName %>;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  <%= entityName %>Repository userRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    <%= entityName %> user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

    return UserDetailsImpl.build(user);
  }

}
