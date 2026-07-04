package pl.bartlomiejciupka.devopswithkubernetes.logoutputwriter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class Main {
    private static final String randomString = UUID.randomUUID().toString();

    public static void main(String[] args) throws IOException {
        Path outputFilePath = Path.of(System.getenv()
                .getOrDefault("OUTPUT_FILE", "../outputFile.txt"));
        Files.createDirectories(outputFilePath.getParent());
        Files.writeString(outputFilePath, "",
                StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);

        try (ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor()) {
            ScheduledFuture<?> task = executorService.scheduleAtFixedRate(() -> {
                        try {
                            Files.writeString
                                    (outputFilePath, Instant.now() + ": " + randomString,
                                    StandardCharsets.UTF_8, StandardOpenOption.CREATE,
                                    StandardOpenOption.TRUNCATE_EXISTING);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }, 0, 5, TimeUnit.SECONDS);

            task.get();
        } catch (Exception e) {
            IO.println("Something went wrong... horribly!");
        }
    }
}