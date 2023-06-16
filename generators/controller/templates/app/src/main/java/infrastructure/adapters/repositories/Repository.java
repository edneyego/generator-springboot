package <%= packageName %>.infrastructure.adapters.repositories;

import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import <%= packageName %>.domain.<%= entityName %>;
import <%= packageName %>.domain.ports.repositories.<%= entityName %>RepositoryPort;
import lombok.AllArgsConstructor;
import <%= packageName %>.infrastructure.adapters.entities.<%= entityName %>Entity;
import <%= packageName %>.infrastructure.adapters.mappers.<%= entityName %>Mapper;
import <%= packageName %>.application.adapters.model.response.PagedResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Component
@Repository
public class <%= entityName %>Repository implements <%= entityName %>RepositoryPort {
  
    private Spring<%= entityName %>Repository springRepository;
    private <%= entityName %>Mapper <%= entityVarName %>Mapper;


    @Override
    public <%= entityName %> save(<%= entityName %> <%= entityVarName %>) {
        <%= entityName %>Entity entity=  <%= entityVarName %>Mapper.to(<%= entityVarName %>);
        return <%= entityVarName %>Mapper.from(springRepository.save(entity));
    }


    @Override
    public PagedResult<<%= entityName %>> findAll(Pageable pageable){
        Page<<%= entityName %>> result =<%= entityVarName %>Mapper.fromListMap(springRepository.findAll(pageable));
        return new PagedResult<>(result);
    }

    @Override
    public Optional<<%= entityName %>> findById(Long id){
        return Optional.ofNullable(<%= entityVarName %>Mapper.from(springRepository.findById(id).get()));
    }

    @Override
    public void deleteById(Long id){
        springRepository.deleteById(id);
    }

}