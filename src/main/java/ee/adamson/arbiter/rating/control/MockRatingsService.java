package ee.adamson.arbiter.rating.control;

import ee.adamson.arbiter.rating.RiskRating;

import java.util.Map;

public class MockRatingsService implements RatingsService {
    private final Map<String, RiskRating> DATA = Map.of(
            "49002010965", RiskRating.DEBT,
            "49002010976", RiskRating.S1,
            "49002010987", RiskRating.S2,
            "49002010998", RiskRating.S3
    );

    @Override
    public RiskRating getRating(String personalIdCode) {
        return DATA.getOrDefault(personalIdCode, RiskRating.NA);
    }
}
