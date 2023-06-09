package <%= packageName %>.domain.ports.interfaces;

import <%= packageName %>.mapper.<%= entityName %>Mapper;
import <%= packageName %>.domain.entities.<%= entityName %>;

import java.util.Optional;

public interface <%= entityName %>ServicePort {

    public <%= entityName %> registerUser(<%= entityName %>Mapper <%= entityName %>);

}
