package ee.adamson.arbiter.evaluation.report;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ReportBuilder {
    private final List<String> buffer = new ArrayList<>();
    private final StringBuilder log = new StringBuilder();
    private final String separator;

    public ReportBuilder() {
        this(StringUtils.LF);
    }

    public ReportBuilder(String separator) {
        this.separator = separator;
    }

    public void append(String line) {
        buffer.add(line);
    }

    public String build() {
        log.append(getBuffered());
        buffer.clear();
        return log.toString();
    }

    private String getBuffered() {
        return StringUtils.join(buffer, separator);
    }

    public static String decorated(String value) {
        return String.format("[%s]", value);
    }

    public static String list(String value) {
        return String.format("* %s", value);
    }

}
