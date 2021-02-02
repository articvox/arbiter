package ee.adamson.arbiter.evaluation;

import ee.adamson.arbiter.evaluation.calculator.Calculator;
import ee.adamson.arbiter.evaluation.calculator.DefaultCalculator;

import java.math.BigDecimal;

public class EvaluationFixture {
    public static final BigDecimal MIN_LOAN_AMOUNT = BigDecimal.valueOf(2000);
    public static final BigDecimal MAX_LOAN_AMOUNT = BigDecimal.valueOf(10000);

    public static final int MIN_PERIOD = 12;
    public static final int MAX_PERIOD = 60;

    public static Calculator getDefaultCalculator() {
        return new DefaultCalculator(
                DefaultCalculator.Parameters.builder()
                        .minLoanAmount(() -> MIN_LOAN_AMOUNT)
                        .maxLoanAmount(() -> MAX_LOAN_AMOUNT)
                        .minLoanPeriodInMonths(() -> MIN_PERIOD)
                        .maxLoanPeriodInMonths(() -> MAX_PERIOD)
                        .build()
        );
    }

}
