package ee.adamson.arbiter.control;

import ee.adamson.arbiter.Configuration;
import ee.adamson.arbiter.evaluation.calculator.Calculator;
import ee.adamson.arbiter.evaluation.calculator.DefaultCalculator;
import org.springframework.stereotype.Component;

@Component
public class CalculatorFactory {
    private final Configuration configuration;

    public CalculatorFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    public Calculator getDefault() {
        return new DefaultCalculator(DefaultCalculator.Parameters.builder()
                .minLoanAmount(configuration::getMinLoanAmount)
                .maxLoanAmount(configuration::getMaxLoanAmount)
                .minLoanPeriodInMonths(configuration::getMinLoanPeriodInMonths)
                .maxLoanPeriodInMonths(configuration::getMaxLoanPeriodInMonths)
                .build()
        );
    }
}
