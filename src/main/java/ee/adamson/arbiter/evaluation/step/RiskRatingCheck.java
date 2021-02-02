package ee.adamson.arbiter.evaluation.step;


import ee.adamson.arbiter.evaluation.Evaluation;

public class RiskRatingCheck implements Step {

    @Override
    public void accept(Evaluation evaluation) {
        int modifier = evaluation.getModifier();

        if (0 == modifier) {
            evaluation
                    .comment("Bad credit modifier (%s)", modifier)
                    .fail();
        } else {
            evaluation
                    .comment("Credit modifier %s acceptable", modifier)
                    .pass();
        }
    }
}
