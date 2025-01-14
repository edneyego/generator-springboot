package <%= packageName %>.infrastructure.adapters.mappers;


import java.util.List;
import java.util.Optional;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import <%= packageName %>.domain.<%= entityName %>;
import <%= packageName %>.infrastructure.adapters.entities.<%= entityName %>Entity;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface <%= entityName %>Mapper {

    <%= entityName %> from(<%= entityName %>Entity <%= entityVarName %>Entity);

    List<<%= entityName %>Entity> toList(List<<%= entityName %>> <%= entityVarName %>s);

    <%= entityName %>Entity to(<%= entityName %> <%= entityVarName %>);

}
