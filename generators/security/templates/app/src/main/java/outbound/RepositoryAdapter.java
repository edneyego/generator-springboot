package <%= packageName %>.outbound;

import java.util.Optional;
import <%= packageName %>.outbound.repositories.<%= entityName %>Repository;
import <%= packageName %>.ports.out.<%= entityName %>RepositoryPort;
import <%= packageName %>.entities.<%= entityName %>;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.mapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class <%= entityName %>RepositoryAdapter implements <%= entityName %>RepositoryPort {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private <%= entityName %>Repository userRepository;

  public Optional<<%= entityName %>> findByUsername(String username){
    return null;
  }

  public <%= entityName %> registerUser(<%= entityName %>Mapper <%= entityVarName %>){
    <%= entityVarName %>.setPassword(passwordEncoder.encode(<%= entityVarName %>.getPassword()));
    return userRepository.save((<%= entityName %>) Converter.toModel(<%= entityVarName %>, <%= entityName %>.class));
  }

  public Boolean existsByEmail(String email){
    return null;
  }
}