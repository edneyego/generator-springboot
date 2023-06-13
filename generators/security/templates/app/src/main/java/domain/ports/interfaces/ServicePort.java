package <%= packageName %>.domain.ports.interfaces;

import <%= packageName %>.domain.dtos.<%= entityName %>DTO;
import java.util.Optional;

public interface <%= entityName %>ServicePort {

    public <%= entityName %>DTO registerUser(<%= entityName %>DTO <%= entityName %>);

}
