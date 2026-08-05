package pl.bartlomiejciupka.devopswithkubernetes.theproject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "server.port=0",
        "app.image.image-file=${java.io.tmpdir}/the-project-test/image.jpg",
        "app.image.timestamp-file=${java.io.tmpdir}/the-project-test/timestamp.txt",
        "app.image.image-url=https://example.com/image.jpg",
        "app.image.image-ttl-minutes=10"
})
class TheProjectApplicationTests {

    @Test
    void contextLoads() {
    }

}
