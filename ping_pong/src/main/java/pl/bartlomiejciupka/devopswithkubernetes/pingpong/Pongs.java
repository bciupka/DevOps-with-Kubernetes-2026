package pl.bartlomiejciupka.devopswithkubernetes.pingpong;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Pongs {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    public UUID id;

    public int amount;
}
