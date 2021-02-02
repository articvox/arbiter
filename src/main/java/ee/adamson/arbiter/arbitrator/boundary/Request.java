package ee.adamson.arbiter.arbitrator.boundary;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Request {
    private String idCode;
    private BigDecimal amount;
    private int period;
}
