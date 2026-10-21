package pl.bartlomiejciupka.devopswithkubernetes.pingpong;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PingPongApplication {

    public static void main(String[] args) {
        SpringApplication.run(PingPongApplication.class, args);
    }


    @Bean
    public CommandLineRunner init(PingPongService pingPongService) {
        return args -> pingPongService.initializePong();
    }
}


@Service
class PingPongService {
    private final PongsRepo pongsRepo;

    PingPongService(PongsRepo pongsRepo) {
        this.pongsRepo = pongsRepo;
    }

    public String doPong() {
        int prevVal = pongsRepo.incrementAmountAndReturnPreviousValue().orElseThrow();
        return "pong " + prevVal;
    }

    public String getCount() {
        int cur = pongsRepo.findAll().stream().findFirst().orElseThrow().amount;
        return String.valueOf(cur);
    }

    @Transactional
    public void initializePong() {
        pongsRepo.lockPongsTable();

        if (pongsRepo.count() == 0) {
            pongsRepo.save(new Pongs());
        }
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
