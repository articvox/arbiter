package ee.adamson.arbiter.evaluation.step;

import ee.adamson.arbiter.evaluation.Evaluation;
import ee.adamson.arbiter.evaluation.calculator.Calculator;

import java.math.BigDecimal;

public class OfferPeriodMaxLoanAmount implements Step {
    private static final String FAILURE_PERIOD_LIMIT = "Period not within limits (%s)";
    private static final String FAILURE_LOAN_LIMIT   = "Evaluand's loan potential (%s) for the " +
                                                       "requested period (%s) is too low";

    private final Calculator calculator;

    public OfferPeriodMaxLoanAmount(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void accept(Evaluation evaluation) {
        if (!checkPreconditions(evaluation)) {
            evaluation.fail();
            return;
        }
        BigDecimal maxLoanAmount = getMaxLoanAmount(evaluation);

        evaluation
                .commentIf(maxLoanAmount.compareTo(evaluation.getAmount()) != 0,
                           "Adjusting amount %s -> %s", evaluation.getAmount(), maxLoanAmount)
                .adjust(adjustment -> adjustment
                        .toBuilder()
                        .amount(maxLoanAmount)
                        .build())
                .pass();
        ;
    }

    private BigDecimal getPeriodLoanPotential(Evaluation evaluation) {
        return calculator.getLoanPotential(
                evaluation.getModifier(),
                evaluation.getPeriod());
    }

    private BigDecimal getMaxLoanAmount(Evaluation evaluation) {
        return calculator.getMaxPossibleLoanAmount(
                evaluation.getModifier(),
                evaluation.getPeriod());
    }

    private boolean checkPreconditions(Evaluation evaluation) {
        BigDecimal periodLoanLimit = getPeriodLoanPotential(evaluation);

        if (!calculator.isWithinLimits(evaluation.getPeriod())) {
            evaluation.comment(FAILURE_PERIOD_LIMIT, evaluation.getPeriod());
            return false;
        }
        if (!calculator.isAboveMinLimit(periodLoanLimit)) {
            evaluation.comment(FAILURE_LOAN_LIMIT, periodLoanLimit, evaluation.getPeriod());
            return false;
        }
        return true;
    }

}
