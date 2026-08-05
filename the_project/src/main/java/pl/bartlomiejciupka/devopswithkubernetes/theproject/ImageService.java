package pl.bartlomiejciupka.devopswithkubernetes.theproject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

@Service
public class ImageService {
    private final RestClient restClient;
    private final ImageProperties imageProperties;

    public ImageService(RestClient restClient, ImageProperties imageProperties) {
        this.restClient = restClient;
        this.imageProperties = imageProperties;
    }

    public byte[] getImage() {
        checkAndCreateFiles();

        try {
            Instant lastChange = Instant.parse(Files.readString(imageProperties.timestampFile()).trim());
            byte[] currImage = Files.readAllBytes(imageProperties.imageFile());
            if (Instant.now().minus(imageProperties.imageTtlMinutes()).isAfter(lastChange)) {
                byte[] newImage = restClient.get()
                        .uri(imageProperties.imageUrl())
                        .retrieve()
                        .body(byte[].class);

                if (newImage == null) {
                    throw new RuntimeException("Image download fail");
                }
                Files.write(imageProperties.imageFile(), newImage, StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);

                Files.writeString(imageProperties.timestampFile(), Instant.now().toString(),
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
            Files.createDirectories(imageProperties.imageFile().getParent());
            Files.createDirectories(imageProperties.timestampFile().getParent());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (!Files.exists(imageProperties.imageFile()) || !Files.exists(imageProperties.timestampFile())) {
            try {
                byte[] image = restClient.get()
                        .uri(imageProperties.imageUrl())
                        .retrieve()
                        .body(byte[].class);

                if (image == null) {
                    throw new RuntimeException("Image download fail");
                }

                Files.write(imageProperties.imageFile(), image, StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);

                Files.writeString(imageProperties.timestampFile(), Instant.now().toString(),
                        StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
