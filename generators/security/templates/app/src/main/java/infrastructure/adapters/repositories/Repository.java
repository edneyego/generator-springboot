package <%= packageName %>.infrastructure.adapters.repositories;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import <%= packageName %>.domain.<%= entityName %>;
import <%= packageName %>.domain.ports.repositories.<%= entityName %>RepositoryPort;
import lombok.AllArgsConstructor;
import <%= packageName %>.infrastructure.adapters.entities.<%= entityName %>Entity;
import <%= packageName %>.infrastructure.adapters.mappers.<%= entityName %>Mapper;

@AllArgsConstructor
@Component
@Repository
public class <%= entityName %>Repository implements <%= entityName %>RepositoryPort {
  
    private final Spring<%= entityName %>Repository springRepository;
    private <%= entityName %>Mapper <%= entityVarName %>Mapper;


    @Override
    public Optional<<%= entityName %>> findByUsername(String username){
        return Optional.ofNullable(<%= entityVarName %>Mapper.from(springRepository.findByUsername(username).get()));
    }

    @Override
    public <%= entityName %> save(<%= entityName %> <%= entityVarName %>) {
        <%= entityName %>Entity entity=  <%= entityVarName %>Mapper.to(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.from(springRepository.save(entity));
    }

}