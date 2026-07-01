package logoutput;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}

@Service
class LogOutputService {
    public String getLogOutputString() {
        String fileContent;
        Path outputFilePath = Path.of(System.getenv()
                .getOrDefault("OUTPUT_FILE", "../outputFile.txt"));
        try {
            fileContent = Files.readString(outputFilePath);
        } catch (IOException e) {
            return "Ooops... file not found";
        }

        return fileContent;
    }
}

@RestController
class LogOutputController {
    private final LogOutputService logOutputService;

    public LogOutputController(LogOutputService logOutputService) {
        this.logOutputService = logOutputService;
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getLogOutput() {
        return ResponseEntity.ok(logOutputService.getLogOutputString());
    }
}
