package <%= packageName %>.domain.ports.repositories;
import org.springframework.data.domain.Pageable;
import <%= packageName %>.domain.<%= entityName %>;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import <%= packageName %>.application.adapters.model.response.PagedResult;

public interface <%= entityName %>RepositoryPort{

    PagedResult<<%= entityName %>> findAll(Pageable pageable);

    Optional<<%= entityName %>> findById(Long id);

    <%= entityName %> save(<%= entityName %> <%= entityVarName %>);

    void deleteById(Long id);

}
