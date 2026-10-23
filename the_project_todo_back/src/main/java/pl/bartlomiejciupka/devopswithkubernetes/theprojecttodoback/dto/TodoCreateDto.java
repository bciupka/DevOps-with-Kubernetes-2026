package pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.dto;

import jakarta.validation.constraints.NotBlank;

public record TodoCreateDto(@NotBlank String description) {
}
