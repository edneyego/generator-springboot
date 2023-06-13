package <%= packageName %>.domain.adapters.services;

import java.util.Optional;
import <%= packageName %>.domain.ports.repositories.<%= entityName %>RepositoryPort;
import <%= packageName %>.infrastructure.adapters.entities.<%= entityName %>Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import <%= packageName %>.application.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.application.adapters.mapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class <%= entityName %>ServiceImpl implements <%= entityName %>ServicePort {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private <%= entityName %>RepositoryPort repositoryPort;

  public <%= entityName %>Entity registerUser(<%= entityName %>Mapper <%= entityVarName %>){
    <%= entityVarName %>.setPassword(passwordEncoder.encode(<%= entityVarName %>.getPassword()));
    return repositoryPort.save((<%= entityName %>Entity) Converter.toModel(<%= entityVarName %>, <%= entityName %>Entity.class));
  }

}