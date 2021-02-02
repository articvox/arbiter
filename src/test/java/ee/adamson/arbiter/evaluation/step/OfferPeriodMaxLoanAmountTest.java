package ee.adamson.arbiter.evaluation.step;

import ee.adamson.arbiter.evaluation.Evaluation;
import ee.adamson.arbiter.evaluation.EvaluationFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OfferPeriodMaxLoanAmountTest {
    private OfferPeriodMaxLoanAmount step;

    @BeforeEach
    public void setup() {
        step = new OfferPeriodMaxLoanAmount(EvaluationFixture.getDefaultCalculator());
    }

    @Test
    public void test_stepPassWithNoAdjustments() {
        Evaluation evaluation = new Evaluation(Evaluation.Evaluand.builder()
                .amount(BigDecimal.valueOf(6000))
                .period(60)
                .modifier(100)
                .build());

        step.accept(evaluation);
        Assertions.assertTrue(evaluation.isPassed());
        Assertions.assertEquals(BigDecimal.valueOf(6000).compareTo(evaluation.getAmount()), 0);
    }

    @Test
    public void test_stepPassWithAdjustedAmount() {
        Evaluation evaluation = new Evaluation(Evaluation.Evaluand.builder()
                .amount(BigDecimal.valueOf(6000))
                .period(60)
                .modifier(300)
                .build());

        step.accept(evaluation);
        Assertions.assertTrue(evaluation.isPassed());
        Assertions.assertEquals(BigDecimal.valueOf(10000).compareTo(evaluation.getAmount()), 0);
    }

    @Test
    public void test_stepFailIfInadequateLoanPotential() {
        Evaluation evaluation = new Evaluation(Evaluation.Evaluand.builder()
                .amount(BigDecimal.valueOf(6000))
                .period(12)
                .modifier(100)
                .build());

        step.accept(evaluation);
        Assertions.assertFalse(evaluation.isPassed());
        Assertions.assertEquals(BigDecimal.valueOf(6000).compareTo(evaluation.getAmount()), 0);
    }

}
