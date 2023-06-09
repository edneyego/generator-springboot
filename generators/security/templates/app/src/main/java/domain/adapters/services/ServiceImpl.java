package <%= packageName %>.adapter;

import java.util.Optional;
import <%= packageName %>.repositories.<%= entityName %>Repository;
import <%= packageName %>.domain.entities.<%= entityName %>;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.mapper.Converter;
import org.springframework.stereotype.Component;

@Component
public class <%= entityName %>ServiceImpl implements <%= entityName %>ServicePort {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private <%= entityName %>RepositoryPort repositoryPort;

  public <%= entityName %> registerUser(<%= entityName %>Mapper <%= entityVarName %>){
    <%= entityVarName %>.setPassword(passwordEncoder.encode(<%= entityVarName %>.getPassword()));
    return repositoryPort.save((<%= entityName %>) Converter.toModel(<%= entityVarName %>, <%= entityName %>.class));
  }

}