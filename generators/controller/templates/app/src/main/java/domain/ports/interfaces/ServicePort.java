package <%= packageName %>.domain.ports.interfaces;

import <%= packageName %>.domain.<%= entityName %>;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import <%= packageName %>.domain.<%= entityName %>;
import <%= packageName %>.application.adapters.model.response.PagedResult;

public interface <%= entityName %>ServicePort{

    PagedResult<<%= entityName %>> findAll(Pageable pageable);

    Optional<<%= entityName %>> findById(Long id);

    <%= entityName %> save(<%= entityName %> <%= entityVarName %>);

    void deleteById(Long id);

}
