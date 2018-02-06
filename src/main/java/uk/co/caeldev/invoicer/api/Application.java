package uk.co.caeldev.invoicer.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import uk.co.caeldev.spring.moprhia.EnableSpringMorphia;

@SpringBootApplication
@EnableSpringMorphia
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
