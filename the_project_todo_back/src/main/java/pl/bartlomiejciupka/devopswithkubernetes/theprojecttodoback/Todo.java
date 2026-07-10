package pl.bartlomiejciupka.devopswithkubernetes.theprojecttodoback;

import jakarta.validation.constraints.NotBlank;

public record Todo(@NotBlank String desc) {
}
