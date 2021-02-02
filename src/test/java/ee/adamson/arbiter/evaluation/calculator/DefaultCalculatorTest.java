package ee.adamson.arbiter.evaluation.calculator;

import ee.adamson.arbiter.evaluation.EvaluationFixture;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.OptionalInt;

public class DefaultCalculatorTest {
    private final Calculator calculator = EvaluationFixture.getDefaultCalculator();

    @Test
    public void test_getLoanPotential() {
        BigDecimal result = calculator.getLoanPotential(100, 12);
        BigDecimal expected = BigDecimal.valueOf(1200);
        Assertions.assertEquals(0, expected.compareTo(result));
    }

    @Test
    public void test_getMaxPossibleLoanAmount_aboveMaxAdjustsDown() {
        BigDecimal result = calculator.getMaxPossibleLoanAmount(300, 60);
        Assertions.assertEquals(0, EvaluationFixture.MAX_LOAN_AMOUNT.compareTo(result));
    }

    @Test
    public void test_getMaxPossibleLoanAmount_belowMinAdjustsUp() {
        BigDecimal result = calculator.getMaxPossibleLoanAmount(100, 12);
        Assertions.assertEquals(0, EvaluationFixture.MIN_LOAN_AMOUNT.compareTo(result));
    }

    @Test
    public void test_getMaxPossibleLoanAmount_withinLimitsNoAdjusting() {
        BigDecimal result = calculator.getMaxPossibleLoanAmount(100, 60);
        BigDecimal expected = BigDecimal.valueOf(6000);
        Assertions.assertEquals(0, expected.compareTo(result));
    }

    @Test
    public void test_getNextAcceptablePeriod_returnsInputPeriodIfAcceptable() {
        OptionalInt result = calculator.getNextAcceptablePeriod(100, 30);
        int expected = 30;
        Assertions.assertEquals(expected, result.orElse(0));
    }

    @Test
    public void test_getNextAcceptablePeriod_accountsForMinLoanAmount() {
        OptionalInt result = calculator.getNextAcceptablePeriod(100, 12);
        int expected = 20;
        Assertions.assertEquals(expected, result.orElse(0));
    }

    @Test
    public void test_getNextAcceptablePeriod_handlesPeriodBelowMin() {
        OptionalInt result = calculator.getNextAcceptablePeriod(300, 1);
        Assertions.assertEquals(EvaluationFixture.MIN_PERIOD, result.orElse(0));
    }

    @Test
    public void test_getNextAcceptablePeriod_handlesPeriodAboveMax() {
        OptionalInt result = calculator.getNextAcceptablePeriod(300, 90);
        Assertions.assertEquals(EvaluationFixture.MAX_PERIOD, result.orElse(0));
    }

    @Test
    public void test_adjustToLimits_adjustsAboveMax() {
        BigDecimal result = calculator.adjustToLimits(BigDecimal.valueOf(99999));
        Assertions.assertEquals(0, EvaluationFixture.MAX_LOAN_AMOUNT.compareTo(result));

    }

    @Test
    public void test_adjustToLimits_adjustsBelowMin() {
        BigDecimal result = calculator.adjustToLimits(BigDecimal.valueOf(991));
        Assertions.assertEquals(0, EvaluationFixture.MIN_LOAN_AMOUNT.compareTo(result));

    }

    @Test
    public void test_isAboveMinLimit_false() {
        Assertions.assertFalse(calculator.isAboveMinLimit(BigDecimal.valueOf(991)));
    }

    @Test
    public void test_isAboveMinLimit_true() {
        Assertions.assertTrue(calculator.isAboveMinLimit(BigDecimal.valueOf(9999)));
    }

    @Test
    public void test_isWithinLimits_true() {
        Assertions.assertTrue(calculator.isWithinLimits(30));
    }

    @Test
    public void test_isWithinLimits_false() {
        Assertions.assertFalse(calculator.isWithinLimits(8));
        Assertions.assertFalse(calculator.isWithinLimits(69));
    }
}
