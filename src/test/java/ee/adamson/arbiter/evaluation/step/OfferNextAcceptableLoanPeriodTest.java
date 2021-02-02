package ee.adamson.arbiter.evaluation.step;

import ee.adamson.arbiter.evaluation.Evaluation;
import ee.adamson.arbiter.evaluation.EvaluationFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public class OfferNextAcceptableLoanPeriodTest {
    private OfferNextAcceptableLoanPeriod step;

    @BeforeEach
    public void setup() {
        step = new OfferNextAcceptableLoanPeriod(EvaluationFixture.getDefaultCalculator());
    }

    @Test
    public void test_stepPassWithNormalInput() {
        Evaluation evaluation = new Evaluation(Evaluation.Evaluand.builder()
                .amount(BigDecimal.valueOf(6000))
                .period(19)
                .modifier(100)
                .build());

        step.accept(evaluation);
        Assertions.assertTrue(evaluation.isPassed());
        Assertions.assertEquals(20, evaluation.getPeriod());
        Assertions.assertEquals(0, BigDecimal.valueOf(2000).compareTo(evaluation.getAmount()));
    }

    @Test
    public void test_stepPassWithBelowMinLimitPeriod() {
        Evaluation evaluation = new Evaluation(Evaluation.Evaluand.builder()
                .amount(BigDecimal.valueOf(6000))
                .period(1)
                .modifier(100)
                .build());

        step.accept(evaluation);
        Assertions.assertTrue(evaluation.isPassed());
        Assertions.assertEquals(20, evaluation.getPeriod());
        Assertions.assertEquals(0, BigDecimal.valueOf(2000).compareTo(evaluation.getAmount()));
    }

    @Test
    public void test_stepPassWithAboveMaxLimitPeriod() {
        Evaluation evaluation = new Evaluation(Evaluation.Evaluand.builder()
                .amount(BigDecimal.valueOf(6000))
                .period(70)
                .modifier(100)
                .build());

        step.accept(evaluation);
        Assertions.assertTrue(evaluation.isPassed());
        Assertions.assertEquals(60, evaluation.getPeriod());
        Assertions.assertEquals(0, BigDecimal.valueOf(6000).compareTo(evaluation.getAmount()));
    }

}
