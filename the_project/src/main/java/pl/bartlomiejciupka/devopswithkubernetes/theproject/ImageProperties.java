package pl.bartlomiejciupka.devopswithkubernetes.theproject;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.convert.DurationUnit;

import java.nio.file.Path;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

@ConfigurationProperties(prefix = "app.image")
public record ImageProperties(Path imageFile,
                              Path timestampFile,
                              String imageUrl,
                              @DurationUnit(ChronoUnit.MINUTES) Duration imageTtlMinutes) {
}
