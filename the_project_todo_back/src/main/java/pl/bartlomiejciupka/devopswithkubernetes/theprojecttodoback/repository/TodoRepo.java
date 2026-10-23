package pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.entity.Todo;

import java.util.UUID;

@Repository
public interface TodoRepo extends JpaRepository<Todo, UUID> {
}
