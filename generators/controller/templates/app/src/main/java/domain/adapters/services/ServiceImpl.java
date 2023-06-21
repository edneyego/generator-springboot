package <%= packageName %>.domain.adapters.services;

import <%= packageName %>.domain.<%= entityName %>;
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
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor
@Service
@Transactional
public class <%= entityName %>ServiceImpl implements <%= entityName %>ServicePort{

    private final <%= entityName %>RepositoryPort <%= entityVarName %>Repository;


    @Override
    public PagedResult<<%= entityName %>> findAll(Pageable pageable) {
        return <%= entityVarName %>Repository.findAll(pageable);
    }

    @Override
    public Optional<<%= entityName %>> findById(Long id) {
        return <%= entityVarName %>Repository.findById(id);
    }

    @Override
    public <%= entityName %> save(<%= entityName %> <%= entityVarName %>) {
        return <%= entityVarName %>Repository.save(<%= entityVarName %>);
    }

    @Override
    public void deleteById(Long id) {
        <%= entityVarName %>Repository.deleteById(id);
    }
}
