package logoutput;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }
}


@Service
class LogOutputService {
    private final RestClient restClient;

    @Value("${pingpong.uri}")
    private String pingPongUri;

    public LogOutputService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String getLogOutputString() {
        String fileContent;
        Path outputFilePath = Path.of(System.getenv()
                .getOrDefault("OUTPUT_FILE", "../outputFile.txt"));
        try {
            fileContent = Files.readString(outputFilePath).trim();
        } catch (IOException e) {
            return "Ooops... log output file not found";
        }

        return fileContent;
    }

    public String getPongCount() {
        String response = restClient.get()
                .uri(pingPongUri + "/pings")
                .retrieve()
                .requiredBody(String.class);


        return "Ping / Pongs: " + response;
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
        String out = logOutputService.getLogOutputString() +
                System.lineSeparator() + logOutputService.getPongCount();

        return ResponseEntity.ok(out);
    }
}
