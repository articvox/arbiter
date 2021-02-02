package ee.adamson.arbiter.evaluation.calculator;

import java.math.BigDecimal;
import java.util.OptionalInt;

public interface Calculator {

    BigDecimal getLoanPotential(int modifier, int loanPeriodInMonths);

    BigDecimal getMaxPossibleLoanAmount(int modifier, int loanPeriodInMonths);

    OptionalInt getNextAcceptablePeriod(int modifier, int startPeriodByMonths);

    BigDecimal adjustToLimits(BigDecimal amount);

    boolean isAboveMinLimit(BigDecimal amount);

    boolean isWithinLimits(int loanPeriodInMonths);

}
