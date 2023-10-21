package <%= packageName %>.domain;

import java.io.Serializable;
import lombok.Builder;

@Builder
public record <%= entityName %>(Long id, String text) implements Serializable {
  public <%= entityName %> updateId(Long id) {
    return new <%= entityName %>(id, text);
  }
}