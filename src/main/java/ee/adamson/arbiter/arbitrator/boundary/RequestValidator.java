package ee.adamson.arbiter.arbitrator.boundary;

public class RequestValidator {

    public static boolean isValid(Request request) {
        return !isAnyNull(
                request.getIdCode(),
                request.getAmount(),
                request.getPeriod()
        );
    }

    private static boolean isAnyNull(Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                return true;
            }
        }
        return false;
    }

}
