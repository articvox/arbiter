package ee.adamson.arbiter.rating;

public enum RiskRating {
    NA(0),
    DEBT(0),
    S1(100),
    S2(300),
    S3(1000);

    public final int modifier;

    RiskRating(int modifier) {
        this.modifier = modifier;
    }

    @Override
    public String toString() {
        return String.format("[rating=%s,modifier=%s]", name(), modifier);
    }
}
