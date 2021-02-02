package ee.adamson.arbiter.evaluation.step;

import ee.adamson.arbiter.evaluation.Evaluation;
import lombok.Builder;
import lombok.Value;

import java.util.function.Consumer;

public interface Step extends Consumer<Evaluation> {

    default String name() {
        return getClass().getSimpleName();
    }

    @Value
    @Builder
    class Parameters {
        boolean isSuccessTerminal;
        boolean isFailureTerminal;

        public static Parameters failFast() {
            return Parameters.builder()
                    .isSuccessTerminal(false)
                    .isFailureTerminal(true)
                    .build();
        }

        public static Parameters passFast() {
            return Parameters.builder()
                    .isSuccessTerminal(true)
                    .isFailureTerminal(false)
                    .build();
        }
    }

}
