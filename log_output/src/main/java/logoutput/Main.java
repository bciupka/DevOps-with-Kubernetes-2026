package logoutput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.UUID;

@SpringBootApplication
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

@Service
class LogOutputService {
    private static final String randomString = UUID.randomUUID().toString();

    public String getRandomStringWithTimestamp() {
        return Instant.now() + ": " + randomString;
    }
}

@RestController
class LogOutputController {
    private final LogOutputService logOutputService;

    public LogOutputController(LogOutputService logOutputService) {
        this.logOutputService = logOutputService;
    }

    @GetMapping("/")
    public ResponseEntity<String> getRandomStringLog() {
        return ResponseEntity.ok(logOutputService.getRandomStringWithTimestamp());
    }
}

@Component
class LogOutputConsoleController {
    private final LogOutputService logOutputService;

    public LogOutputConsoleController(LogOutputService logOutputService) {
        this.logOutputService = logOutputService;
    }

    @Scheduled(fixedRate = 5_000)
    public void logOutputToConsole() {
        IO.println(logOutputService.getRandomStringWithTimestamp());
    }
}
