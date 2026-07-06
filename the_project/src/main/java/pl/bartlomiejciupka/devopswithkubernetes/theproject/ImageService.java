package pl.bartlomiejciupka.devopswithkubernetes.theproject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Duration;
import java.time.Instant;

@Service
public class ImageService {
    private final Path imagePath = Path.of(System.getenv().getOrDefault(
            "IMAGE_FILE", "./image.jpg"
    ));
    private final Path timestampFile = Path.of(System.getenv().getOrDefault(
            "TIMESTAMP_FILE", "./timestamp.txt"
    ));
    private final RestClient restClient;

    public ImageService(RestClient restClient) {
        this.restClient = restClient;
    }

    public byte[] getImage() {
        checkAndCreateFiles();

        try {
            Instant lastChange = Instant.parse(Files.readString(timestampFile).trim());
            byte[] currImage = Files.readAllBytes(imagePath);
            if (Instant.now().minus(Duration.ofMinutes(10)).isAfter(lastChange)) {
                byte[] newImage = restClient.get()
                        .uri("https://picsum.photos/1200")
                        .retrieve()
                        .body(byte[].class);

                if (newImage == null) {
                    throw new RuntimeException("Image download fail");
                }
                Files.write(imagePath, newImage, StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);

                Files.writeString(timestampFile, Instant.now().toString(),
                        StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);

            }
            return currImage;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkAndCreateFiles() {
        try {
            Files.createDirectories(imagePath.getParent());
            Files.createDirectories(timestampFile.getParent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!Files.exists(imagePath) || !Files.exists(timestampFile)) {
            try {
                byte[] image = restClient.get()
                        .uri("https://picsum.photos/1200")
                        .retrieve()
                        .body(byte[].class);

                if (image == null) {
                    throw new RuntimeException("Image download fail");
                }

                Files.write(imagePath, image, StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);

                Files.writeString(timestampFile, Instant.now().toString(),
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
