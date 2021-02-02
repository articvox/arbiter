package ee.adamson.arbiter.evaluation;

import ee.adamson.arbiter.evaluation.calculator.Calculator;
import ee.adamson.arbiter.evaluation.step.OfferNextAcceptableLoanPeriod;
import ee.adamson.arbiter.evaluation.step.OfferPeriodMaxLoanAmount;
import ee.adamson.arbiter.evaluation.step.RiskRatingCheck;
import ee.adamson.arbiter.evaluation.step.Step;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EvaluationStrategyTest {
    private final Calculator calculator = EvaluationFixture.getDefaultCalculator();

    @Test
    public void test() {
        assertEqualsIgnoringWhitespace(readClassPath("strategy-output.txt"), new EvaluationStrategy()
                .addStep(new RiskRatingCheck(), Step.Parameters.failFast())
                .addStep(new OfferPeriodMaxLoanAmount(calculator), Step.Parameters.passFast())
                .addStep(new OfferNextAcceptableLoanPeriod(calculator), Step.Parameters.failFast())
                .evaluate(Evaluation.Evaluand.builder()
                        .amount(BigDecimal.valueOf(6500))
                        .period(12)
                        .modifier(100)
                        .build())
                .getEvaluation()
                .getReport());
    }

    public void assertEqualsIgnoringWhitespace(String a, String b) {
        Assertions.assertEquals(StringUtils.deleteWhitespace(a), StringUtils.deleteWhitespace(b));
    }

    public String readClassPath(String filename) {
        try {
            return Files.readString(Paths.get(getClass().getResource(filename).toURI()), StandardCharsets.UTF_8);
        } catch (IOException | URISyntaxException e) {
            return null;
        }
    }

}
