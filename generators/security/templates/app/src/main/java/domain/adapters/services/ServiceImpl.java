package <%= packageName %>.domain.adapters.services;

import java.util.Optional;
import <%= packageName %>.domain.ports.repositories.<%= entityName %>RepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import <%= packageName %>.domain.ports.interfaces.<%= entityName %>ServicePort;
import org.springframework.stereotype.Component;
import <%= packageName %>.domain.<%=entityName%>;

@AllArgsConstructor
@Component
public class <%= entityName %>ServiceImpl implements <%= entityName %>ServicePort {

  private PasswordEncoder passwordEncoder;

  private <%= entityName %>RepositoryPort repositoryPort;

  public <%= entityName %> registerUser(<%= entityName %> <%= entityVarName %>){
    <%= entityVarName %>.setPassword(passwordEncoder.encode(<%= entityVarName %>.getPassword()));
    return repositoryPort.save(<%= entityVarName %>);
  }

}