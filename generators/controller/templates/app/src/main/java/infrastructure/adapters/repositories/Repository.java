package <%= packageName %>.infrastructure.adapters.repositories;

import <%= packageName %>.domain.entities.<%= entityName %>;
import org.springframework.data.jpa.repository.JpaRepository;

public interface <%= entityName %>Repository extends JpaRepository<<%= entityName %>, Long> {}
