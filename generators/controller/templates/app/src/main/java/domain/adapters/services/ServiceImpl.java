package <%= packageName %>.services;

import <%= packageName %>.domain.entities.<%= entityName %>;
import <%= packageName %>.application.adapters.model.response.PagedResult;
import <%= packageName %>.domain.ports.repositories.<%= entityName %>RepositoryPort;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import <%= packageName %>.domain.ports.interfaces.<%= entityName %>ServicePort;

@Service
@Transactional
public class <%= entityName %>ServiceImpl implements <%= entityName %>ServicePort{

    private final <%= entityName %>RepositoryPort <%= entityVarName %>Repository;

    @Autowired
    public <%= entityName %>Service(<%= entityName %>Repository <%= entityVarName %>Repository) {
        this.<%= entityVarName %>Repository = <%= entityVarName %>Repository;
    }

    @Override
    public PagedResult<<%= entityName %>> findAll<%= entityName %>s(
        int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort =
        sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<<%= entityName %>> <%= entityVarName %>sPage = <%= entityVarName %>Repository.findAll(pageable);

        return new PagedResult<>(<%= entityVarName %>sPage);
    }

    @Override
    public Optional<<%= entityName %>> find<%= entityName %>ById(Long id) {
        return <%= entityVarName %>Repository.findById(id);
    }

    @Override
    public <%= entityName %> save<%= entityName %>(<%= entityName %> <%= entityVarName %>) {
        return <%= entityVarName %>Repository.save(<%= entityVarName %>);
    }

    @Override
    public void delete<%= entityName %>ById(Long id) {
        <%= entityVarName %>Repository.deleteById(id);
    }
}
