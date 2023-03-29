package <%= packageName %>.outbound.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import <%= packageName %>.entities.<%= entityName %>;

@Repository
public interface <%= entityName %>Repository extends JpaRepository<<%= entityName %>, Long> {
  
  Optional<<%= entityName %>> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);
}
