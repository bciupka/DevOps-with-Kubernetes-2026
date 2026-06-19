package pl.bartlomiejciupka.devopswithkubernetes.theproject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.context.WebServerInitializedEvent;
import org.springframework.context.event.EventListener;

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
}
