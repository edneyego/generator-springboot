package <%= packageName %>.infrastructure.adapters.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import <%= packageName %>.domain.entities.<%= entityName %>;
import <%= packageName %>.domain.ports.repositories.<%= entityName %>RepositoryPort;

@Component
@Repository
public class <%= entityName %>Repository implements <%= entityName %>RepositoryPort {
  
    private final Spring<%= entityName %>Repository springRepository;

    @Override
    public Optional<<%= entityName %>> findByUsername(String username){
        return springRepository.findByUsername(username);
    }

    @Override
    public Boolean existsByUsername(String username){
        return springRepository.existsByUsername(username);
    }

}