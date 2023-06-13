package <%= packageName %>.infrastructure.adapters.repositories;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import <%= packageName %>.domain.<%= entityName %>;
import <%= packageName %>.domain.ports.repositories.<%= entityName %>RepositoryPort;
import <%= packageName %>.application.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.application.adapters.mapper.Converter;

@Component
@Repository
public class <%= entityName %>Repository implements <%= entityName %>RepositoryPort {
  
    private final Spring<%= entityName %>Repository springRepository;

    public <%= entityName %>Repository(Spring<%= entityName %>Repository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Optional<<%= entityName %>> findByUsername(String username){
        return (<%= entityName %>Mapper) Converter.toModel(springRepository.findByUsername(username),<%= entityName %>Mapper.class);
    }

    @Override
    public <%= entityName %> save(<%= entityName %> <%= entityVarName %>) {
        return (<%= entityName %>Mapper) Converter.toModel(springRepository.save(<%= entityVarName %>),<%= entityName %>Mapper.class);
    }

}