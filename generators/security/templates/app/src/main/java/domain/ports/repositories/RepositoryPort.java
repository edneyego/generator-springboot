package <%= packageName %>.domain.ports.repositories;

import <%= packageName %>.domain.<%= entityName %>;

import java.util.Optional;

public interface <%= entityName %>RepositoryPort {

    public Optional<<%= entityName %>> findByUsername(String username);

    public <%= entityName %> save(<%= entityName %> <%= entityVarName %>);

}
