package ee.adamson.arbiter.evaluation;

import ee.adamson.arbiter.evaluation.step.Step;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class EvaluationStrategy {
    @Getter
    private Evaluation evaluation;
    private final Map<Step, Step.Parameters> setup = new LinkedHashMap<>();


    public EvaluationStrategy evaluate(Evaluation.Evaluand evaluand) {
        log.info("Starting evaluation of subject {}", evaluand);
        evaluation = new Evaluation(evaluand);
        return executeSteps();
    }

    public EvaluationStrategy addStep(Step step, Step.Parameters parameters) {
        if (setup.containsKey(step)) {
            log.warn("Step {} already registered - overwriting parameters", step);
        }
        setup.put(step, parameters);
        return this;
    }

    private EvaluationStrategy executeSteps() {
        for (Step step : setup.keySet()) {
            evaluation.logStart(step.name());
            step.accept(evaluation);

            if (isFinalized(evaluation, step)) {
                log.info("Concluding evaluation - step {} result is final", step.name());
                break;
            }
        }
        log.info("Evaluation complete. Report: " + evaluation.getReport());
        return this;
    }

    private boolean isFinalized(Evaluation evaluation, Step step) {
        return evaluation.isPassed() ?
                setup.get(step).isSuccessTerminal() :
                setup.get(step).isFailureTerminal();
    }

}
