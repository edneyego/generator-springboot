package <%= packageName %>.domain.ports.repositories;

import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.infrastructure.adapters.entities.<%= entityName %>Entity;

import java.util.Optional;

public interface <%= entityName %>RepositoryPort {

    public Optional<<%= entityName %>Entity> findByUsername(String username);

    public <%= entityName %>Entity save(<%= entityName %>Entity <%= entityVarName %>);

}
