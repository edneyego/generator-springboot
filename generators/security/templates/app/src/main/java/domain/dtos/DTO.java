package <%= packageName %>.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class <%= entityName %>DTO {

    private Long id;

    private String text;

    private String username;

    private String email;

    private String password;
}
