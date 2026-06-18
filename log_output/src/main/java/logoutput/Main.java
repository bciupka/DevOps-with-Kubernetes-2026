package logoutput;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        String randomString = UUID.randomUUID().toString();

        try (ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor()) {
            executorService.scheduleAtFixedRate(() -> {
                IO.println(Instant.now() + ": " + randomString);
            }, 0, 5, TimeUnit.SECONDS);

            Thread.currentThread().join();
        } catch (Exception e) {
            IO.println("We've got some problem: " + e.getMessage());
        }
    }
}
