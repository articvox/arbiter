package ee.adamson.arbiter.evaluation;

import ee.adamson.arbiter.evaluation.report.ReportBuilder;
import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.function.Function;

import static org.apache.commons.lang3.StringUtils.LF;


public class Evaluation {
    private final ReportBuilder log = new ReportBuilder();
    private Evaluand output;
    @Getter
    private boolean passed;

    public Evaluation(Evaluand input) {
        this.output = input;
    }

    public Evaluation adjust(Function<Evaluand, Evaluand> adjustment) {
        output = adjustment.apply(output);
        return this;
    }

    public Evaluation commentIf(boolean condition, String comment, Object... arguments) {
        return condition ? comment(comment, arguments) : this;
    }

    public Evaluation comment(String comment, Object... arguments) {
        log.append(ReportBuilder.list(String.format(comment, arguments)));
        return this;
    }

    public void logStart(String step) {
        log.append(StringUtils.join(LF, ReportBuilder.decorated(step)));
    }

    public void pass() {
        log.append(ReportBuilder.list("PASS"));
        passed = true;
    }

    public void fail() {
        log.append(ReportBuilder.list("FAIL"));
        passed = false;
    }

    public BigDecimal getAmount() {
        return output.getAmount();
    }

    public Integer getPeriod() {
        return output.getPeriod();
    }

    public Integer getModifier() {
        return output.getModifier();
    }

    public String getReport() {
        return log.build();
    }

    @Value
    @Builder(toBuilder = true)
    public static class Evaluand {
        BigDecimal amount;
        int period;
        int modifier;

        @Override
        public String toString() {
            return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
        }
    }

}
