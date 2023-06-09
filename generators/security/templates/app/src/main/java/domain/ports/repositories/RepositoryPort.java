package <%= packageName %>.domain.ports.repositories;

import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.domain.entities.<%= entityName %>;

import java.util.Optional;

public interface <%= entityName %>RepositoryPort {

    public Optional<<%= entityName %>> findByUsername(String username);

    public <%= entityName %> save(<%= entityName %> <%= entityVarName %>)

}
