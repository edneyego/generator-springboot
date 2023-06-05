package <%= packageName %>.adapter;

import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.domain.entities.<%= entityName %>;

import java.util.Optional;

public interface I<%= entityName %>RepositoryAdapter {

    public Optional<<%= entityName %>> findByUsername(String username);

    public Boolean existsByEmail(String email);

    public <%= entityName %> registerUser(<%= entityName %>Mapper <%= entityName %>);

}
