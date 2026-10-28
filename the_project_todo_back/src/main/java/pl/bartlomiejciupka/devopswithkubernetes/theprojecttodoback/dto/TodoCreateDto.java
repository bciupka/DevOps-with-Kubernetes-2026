package pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TodoCreateDto(
        @NotBlank @Size(max = 140) String description) {
}
