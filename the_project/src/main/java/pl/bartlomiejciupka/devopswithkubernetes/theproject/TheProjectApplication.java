package pl.bartlomiejciupka.devopswithkubernetes.theproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;

@SpringBootApplication
public class TheProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheProjectApplication.class, args);
    }

    @EventListener
    private void onServerStarted(WebServerInitializedEvent event) {
        int port = event.getWebServer().getPort();
        IO.println("Server started in port " + port);
    }

    @Bean
    RestClient restClient() {
        HttpClient httpClient = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.NORMAL)
                .build();

        return RestClient.builder()
                .requestFactory(new JdkClientHttpRequestFactory(httpClient))
                .build();
    }
}
