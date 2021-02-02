package ee.adamson.arbiter;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

@Data
@ConfigurationProperties(prefix = "arbiter")
public class Configuration {
    private BigDecimal maxLoanAmount;
    private BigDecimal minLoanAmount;

    private int minLoanPeriodInMonths;
    private int maxLoanPeriodInMonths;
}
