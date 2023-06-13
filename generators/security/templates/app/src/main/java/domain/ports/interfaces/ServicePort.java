package <%= packageName %>.domain.ports.interfaces;

import <%= packageName %>.application.adapters.mapper.<%= entityName %>Mapper;
import <%= packageName %>.infrastructure.adapters.entities.<%= entityName %>Entity;

import java.util.Optional;

public interface <%= entityName %>ServicePort {

    public <%= entityName %>Entity registerUser(<%= entityName %>Mapper <%= entityName %>);

}
