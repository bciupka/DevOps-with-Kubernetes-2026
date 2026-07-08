package pl.bartlomiejciupka.devopswithkubernetes.pingpong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
public class PingPongApplication {

    public static void main(String[] args) {
        SpringApplication.run(PingPongApplication.class, args);
    }

}

@Service
class PingPongService {
    private final AtomicInteger pongCount = new AtomicInteger();

    public String doPong() {
        return "pong " + pongCount.getAndIncrement();
    }

    public String getCount() {
        return String.valueOf(pongCount.get());
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

    @GetMapping(value = "/pings", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getPings() {
        return ResponseEntity.ok(pingPongService.getCount());
    }
}
