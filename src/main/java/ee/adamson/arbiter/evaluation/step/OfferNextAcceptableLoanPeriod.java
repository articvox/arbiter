package ee.adamson.arbiter.evaluation.step;

import ee.adamson.arbiter.evaluation.Evaluation;
import ee.adamson.arbiter.evaluation.calculator.Calculator;

import java.math.BigDecimal;
import java.util.OptionalInt;

public class OfferNextAcceptableLoanPeriod implements Step {
    private final Calculator calculator;
    private Evaluation evaluation;

    public OfferNextAcceptableLoanPeriod(Calculator calculator) {
        this.calculator = calculator;
    }

    @Override
    public void accept(Evaluation evaluation) {
        this.evaluation = evaluation;

        findNextAcceptablePeriod().ifPresentOrElse(this::offer, evaluation::fail);
    }

    private void offer(int loanPeriodInMonths) {
        BigDecimal maxPossibleLoanAmount = getMaxPossibleLoanAmount(loanPeriodInMonths);

        evaluation
                .commentIf(evaluation.getAmount().compareTo(maxPossibleLoanAmount) != 0,
                         "Adjusting amount %s -> %s", evaluation.getAmount(), maxPossibleLoanAmount)
                .comment("Adjusting period %s -> %s", evaluation.getPeriod(), loanPeriodInMonths)
                .adjust(adjustment -> adjustment.toBuilder()
                        .amount(maxPossibleLoanAmount)
                        .period(loanPeriodInMonths)
                        .build())
                .pass();
    }

    private OptionalInt findNextAcceptablePeriod() {
        return calculator.getNextAcceptablePeriod(
                evaluation.getModifier(),
                evaluation.getPeriod());
    }

    private BigDecimal getMaxPossibleLoanAmount(int loanPeriodInMonths) {
        return calculator.getMaxPossibleLoanAmount(
                evaluation.getModifier(),
                loanPeriodInMonths);
    }

}
