package <%= packageName %>.domain.adapters.services;

import java.util.Optional;
import <%= packageName %>.domain.ports.repositories.<%= entityName %>RepositoryPort;
import <%= packageName %>.domain.dtos.<%= entityName %>DTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import <%= packageName %>.application.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.application.adapters.mapper.Converter;
import <%= packageName %>.domain.ports.interfaces.<%= entityName %>ServicePort;
import org.springframework.stereotype.Component;
import <%= packageName %>.domain.<%= s %>;

@Component
public class <%= entityName %>ServiceImpl implements <%= entityName %>ServicePort {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private <%= entityName %>RepositoryPort repositoryPort;

  public <%= entityName %>DTO registerUser(<%= entityName %>DTO <%= entityVarName %>DTO){
    <%= entityVarName %>DTO.setPassword(passwordEncoder.encode(<%= entityVarName %>DTO.getPassword()));
    <%= entityName %> <%= entityVarName %> = (<%= entityName %>) Converter.toModel(<%= entityVarName %>DTO, <%= entityName %>.class);
  return (<%= entityName %>DTO) Converter.toModel(repositoryPort.save(<%= entityVarName %>), <%= entityName %>.class);
}

}