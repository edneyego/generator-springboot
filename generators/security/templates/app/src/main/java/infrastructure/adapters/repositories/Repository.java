package <%= packageName %>.infrastructure.adapters.repositories;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import <%= packageName %>.infrastructure.adapters.entities.<%= entityName %>Entity;
import <%= packageName %>.domain.ports.repositories.<%= entityName %>RepositoryPort;

@Component
@Repository
public class <%= entityName %>Repository implements <%= entityName %>RepositoryPort {
  
    private final Spring<%= entityName %>Repository springRepository;

    public <%= entityName %>Repository(Spring<%= entityName %>Repository springRepository) {
        this.springRepository = springRepository;
    }

    @Override
    public Optional<<%= entityName %>Entity> findByUsername(String username){
        return springRepository.findByUsername(username);
    }

    @Override
    public <%= entityName %>Entity save(<%= entityName %>Entity <%= entityVarName %>) {
        return springRepository.save(<%= entityVarName %>);
    }

}