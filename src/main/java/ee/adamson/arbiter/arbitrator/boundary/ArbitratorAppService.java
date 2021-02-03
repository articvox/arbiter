package ee.adamson.arbiter.arbitrator.boundary;

import ee.adamson.arbiter.arbitrator.control.CalculatorFactory;
import ee.adamson.arbiter.evaluation.Evaluation;
import ee.adamson.arbiter.evaluation.EvaluationStrategy;
import ee.adamson.arbiter.evaluation.step.OfferNextAcceptableLoanPeriod;
import ee.adamson.arbiter.evaluation.step.OfferPeriodMaxLoanAmount;
import ee.adamson.arbiter.evaluation.step.RiskRatingCheck;
import ee.adamson.arbiter.evaluation.step.Step;
import ee.adamson.arbiter.rating.RiskRating;
import ee.adamson.arbiter.rating.control.RatingsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ArbitratorAppService {

    private final CalculatorFactory calculatorFactory;
    private final RatingsService ratingsService;

    public ArbitratorAppService(CalculatorFactory calculatorFactory,
                                RatingsService ratingsService) {
        this.calculatorFactory = calculatorFactory;
        this.ratingsService = ratingsService;
    }

    public Proposal negotiate(Request request) {
        Evaluation eval = new EvaluationStrategy()
                 .addStep(new RiskRatingCheck(), Step.Parameters.failFast())
                 .addStep(new OfferPeriodMaxLoanAmount(calculatorFactory.getDefault()), Step.Parameters.passFast())
                 .addStep(new OfferNextAcceptableLoanPeriod(calculatorFactory.getDefault()), Step.Parameters.failFast())
                 .evaluate(evaluand(request))
                 .getEvaluation();

        return proposal(eval);
    }

    private Evaluation.Evaluand evaluand(Request request) {
        return Evaluation.Evaluand.builder()
                .amount(request.getAmount())
                .period(request.getPeriod())
                .modifier(getCreditModifier(request.getIdCode()))
                .build();
    }

    private Proposal proposal(Evaluation evaluation) {
        return Proposal.builder()
                .amount(evaluation.getAmount())
                .period(evaluation.getPeriod())
                .approved(evaluation.isPassed())
                .build();
    }

    private int getCreditModifier(String personalIdCode) {
        RiskRating rating = ratingsService.getRating(personalIdCode);
        log.info("Found risk rating for person {} {}", personalIdCode, rating);

        return rating.modifier;
    }

}
