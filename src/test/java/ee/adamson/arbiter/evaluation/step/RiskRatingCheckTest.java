package ee.adamson.arbiter.evaluation.step;

import ee.adamson.arbiter.evaluation.Evaluation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RiskRatingCheckTest {

    @Test
    public void test_badModifier() {
        RiskRatingCheck step = new RiskRatingCheck();
        Evaluation evaluation = getEvaluation(0);
        step.accept(evaluation);

        Assertions.assertFalse(evaluation.isPassed());
    }

    @Test
    public void test_goodModifier() {
        RiskRatingCheck step = new RiskRatingCheck();
        Evaluation evaluation = getEvaluation(100);
        step.accept(evaluation);

        Assertions.assertTrue(evaluation.isPassed());
    }

    private Evaluation getEvaluation(int modifier) {
        return new Evaluation(Evaluation.Evaluand.builder()
                .modifier(modifier)
                .build());
    }

}
