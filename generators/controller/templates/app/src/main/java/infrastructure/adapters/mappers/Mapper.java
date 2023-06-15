package <%= packageName %>.infrastructure.adapters.mappers;


import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import <%= packageName %>.domain.<%= entityName %>;
import <%= packageName %>.infrastructure.adapters.entities.<%= entityName %>Entity;
import org.springframework.data.domain.Page;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface <%= entityName %>Mapper {

    <%= entityName %> from(<%= entityName %>Entity <%= entityVarName %>Entity);

    <%= entityName %> fromList(<%= entityName %>Entity <%= entityVarName %>Entity);

    default Page<<%= entityName %>> fromListMap(Page<<%= entityName %>Entity> page) {
        return page.map(this::from);
    }

    List<<%= entityName %>Entity> toList(List<<%= entityName %>> <%= entityVarName %>s);

    <%= entityName %>Entity to(<%= entityName %> <%= entityVarName %>);

}
