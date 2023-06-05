package <%= packageName %>.domain.ports.interfaces;

import <%= packageName %>.domain.entities.<%= entityName %>;

import java.util.Optional;
import <%= packageName %>.domain.entities.<%= entityName %>;
import <%= packageName %>.application.adapters.model.response.PagedResult;

public interface <%= entityName %>ServicePort{

    PagedResult<<%= entityName %>> findAll<%= entityName %>s(
        int pageNo, int pageSize, String sortBy, String sortDir);

    Optional<<%= entityName %>> find<%= entityName %>ById(Long id);

    <%= entityName %> save<%= entityName %>(<%= entityName %> <%= entityVarName %>);

    void delete<%= entityName %>ById(Long id);

}
