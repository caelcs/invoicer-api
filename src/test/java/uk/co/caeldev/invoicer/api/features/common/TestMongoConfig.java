package uk.co.caeldev.invoicer.api.features.common;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class TestMongoConfig {

    @Bean
    @Primary
    public MongoClient mongoClient() {
        return new Fongo("copyshare").getMongo();
    }
}
