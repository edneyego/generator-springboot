package <%= packageName %>.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class <%= entityName %>Mapper {

    private Long id;

    private String username;

    private String email;

    private String password;

}
