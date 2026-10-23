package pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.dto;

import java.util.UUID;

public record TodoDto(UUID id, String description) {
}
