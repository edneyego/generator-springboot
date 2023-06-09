package <%= packageName %>.infrastructure.adapters.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import <%= packageName %>.domain.entities.<%= entityName %>;

@Repository
public interface Spring<%= entityName %>Repository extends JpaRepository<<%= entityName %>, Long> {
  
  Optional<<%= entityName %>> findByUsername(String username);

  Boolean existsByUsername(String username);

}
