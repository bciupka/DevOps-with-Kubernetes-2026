package pl.bartlomiejciupka.devopswithkubernetes.pingpong;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface PongsRepo extends JpaRepository<Pongs, UUID> {
    @Modifying
    @Query(value = "LOCK TABLE pongs IN SHARE ROW EXCLUSIVE MODE", nativeQuery = true)
    void lockPongsTable();

    @Query(value = "UPDATE pongs SET amount = amount + 1 WHERE id = (SELECT id FROM pongs LIMIT 1) RETURNING amount - 1", nativeQuery = true)
    Optional<Integer> incrementAmountAndReturnPreviousValue();
}
