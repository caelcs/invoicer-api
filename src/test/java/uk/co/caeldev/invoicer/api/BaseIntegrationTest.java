package uk.co.caeldev.invoicer.api;

import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import uk.co.caeldev.invoicer.api.features.common.TestMongoConfig;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Import(TestMongoConfig.class)
public abstract class BaseIntegrationTest {

    @LocalServerPort
    protected int serverPort;

}
