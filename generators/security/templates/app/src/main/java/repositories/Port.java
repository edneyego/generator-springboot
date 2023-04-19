package <%= packageName %>.repositories;

import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.entities.<%= entityName %>;

import java.util.Optional;

public interface <%= entityName %>RepositoryPort {

    public Optional<<%= entityName %>> findByUsername(String username);

    public Boolean existsByEmail(String email);

    public <%= entityName %> registerUser(<%= entityName %>Mapper <%= entityName %>);

}
