package pl.bartlomiejciupka.devopswithkubernetes.pingpong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@SpringBootApplication
public class PingPongApplication {

    public static void main(String[] args) {
        SpringApplication.run(PingPongApplication.class, args);
    }

}

@Service
class PingPongService {
    private final Path pongFilepath = Path.of(System.getenv()
            .getOrDefault("PONG_FILE", "./pongFile.txt"));

    public PingPongService() {
        try {
            if (!Files.exists(pongFilepath)) {
                Files.createDirectories(pongFilepath.getParent());
                Files.writeString(pongFilepath, "0", StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public synchronized String doPong() {
        String pongCountString;
        try {
            pongCountString = Files.readString(pongFilepath).trim();
        } catch (IOException e) {
            pongCountString = "0";
        }

        int pongCount = Integer.parseInt(pongCountString);

        try {
            Files.writeString(
                    pongFilepath,
                    String.valueOf(pongCount + 1),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "pong " + pongCount;
    }
}

@RestController
@RequestMapping("/pingpong")
class PingPongController {
    private final PingPongService pingPongService;

    public PingPongController(PingPongService pingPongService) {
        this.pingPongService = pingPongService;
    }

    @GetMapping
    public ResponseEntity<String> getPong() {
        return ResponseEntity.ok(pingPongService.doPong());
    }
}
