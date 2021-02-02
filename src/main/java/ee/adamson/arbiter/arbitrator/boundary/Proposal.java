package ee.adamson.arbiter.arbitrator.boundary;

import lombok.Builder;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

@Data
@Builder
public class Proposal {
    private final boolean approved;
    private final BigDecimal amount;
    private final int period;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.NO_CLASS_NAME_STYLE);
    }
}
