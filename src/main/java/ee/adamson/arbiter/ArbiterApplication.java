package ee.adamson.arbiter;

import ee.adamson.arbiter.rating.control.MockRatingsService;
import ee.adamson.arbiter.rating.control.RatingsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ArbiterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArbiterApplication.class, args);
    }

    @Bean
    public RatingsService ratingsService() {
        return new MockRatingsService();
    }

}
