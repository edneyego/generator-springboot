package <%= packageName %>.domain.ports.interfaces;

import <%= packageName %>.domain.<%= entityName %>;
import java.util.Optional;

public interface <%= entityName %>ServicePort {

    public <%= entityName %> registerUser(<%= entityName %> <%= entityName %>);

}
