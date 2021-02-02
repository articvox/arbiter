package ee.adamson.arbiter.rating.control;

import ee.adamson.arbiter.rating.RiskRating;

public interface RatingsService {

    RiskRating getRating(String personalIdCode);

}
