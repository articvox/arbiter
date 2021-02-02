package ee.adamson.arbiter.evaluation.calculator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.OptionalInt;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class DefaultCalculator implements Calculator {
    private final Parameters parameters;

    public DefaultCalculator(Parameters parameters) {
        this.parameters = parameters;
    }

    public BigDecimal getLoanPotential(int modifier, int loanPeriodInMonths) {
        return new BigDecimal(modifier * loanPeriodInMonths);
    }

    public BigDecimal getMaxPossibleLoanAmount(int modifier, int loanPeriodInMonths) {
        return adjustToLimits(getLoanPotential(modifier, loanPeriodInMonths));
    }

    public BigDecimal adjustToLimits(BigDecimal amount) {
        return amount
                .min(parameters.maxLoanAmount.get())
                .max(parameters.minLoanAmount.get());
    }

    public OptionalInt getNextAcceptablePeriod(int modifier, int startPeriodInMonths) {
        return getCandidateLoanPeriods(startPeriodInMonths)
                .filter(period -> isPeriodLoanPotentialAcceptable(period, modifier))
                .findFirst();
    }

    private IntStream getCandidateLoanPeriods(int start) {
        return IntStream.rangeClosed(
                new PeriodAdjustment(start).toLimits().get(),
                parameters.maxLoanPeriodInMonths.get());
    }

    private boolean isPeriodLoanPotentialAcceptable(int period, int modifier) {
        return getLoanPotential(modifier, period).compareTo(parameters.minLoanAmount.get()) >= 0;
    }

    public boolean isAboveMinLimit(BigDecimal amount) {
        return parameters.minLoanAmount.get().compareTo(amount) <= 0;
    }

    public boolean isWithinLimits(int loanPeriodInMonths) {
        return isBetween(loanPeriodInMonths,
                parameters.minLoanPeriodInMonths.get(),
                parameters.maxLoanPeriodInMonths.get());
    }

    private static <T extends Comparable<T>> boolean isBetween(T value, T start, T end) {
        return value.compareTo(start) >= 0 && value.compareTo(end) <= 0;
    }

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Parameters {
        Supplier<BigDecimal> minLoanAmount = () -> BigDecimal.ZERO;
        Supplier<BigDecimal> maxLoanAmount = () -> BigDecimal.ZERO;
        Supplier<Integer> minLoanPeriodInMonths = () -> 0;
        Supplier<Integer> maxLoanPeriodInMonths = () -> 0;

        @Builder
        public static Parameters build(Supplier<BigDecimal> minLoanAmount,
                                       Supplier<BigDecimal> maxLoanAmount,
                                       Supplier<Integer> minLoanPeriodInMonths,
                                       Supplier<Integer> maxLoanPeriodInMonths) {
            return new Parameters(
                    minLoanAmount,
                    maxLoanAmount,
                    minLoanPeriodInMonths,
                    maxLoanPeriodInMonths);
        }
    }

    @Data
    private class PeriodAdjustment {
        private int period;

        PeriodAdjustment(int period) {
            this.period = period;
        }

        public PeriodAdjustment adjustMin() {
            period = Math.max(period, parameters.minLoanPeriodInMonths.get());
            return this;
        }

        public PeriodAdjustment adjustMax() {
            period = Math.min(period, parameters.maxLoanPeriodInMonths.get());
            return this;
        }

        public PeriodAdjustment toLimits() {
            return this
                    .adjustMin()
                    .adjustMax();
        }

        public int get() {
            return period;
        }
    }

}
