package ch.queo.cli.format;

import ch.queo.cli.dto.ParsedArgument;
import lombok.experimental.UtilityClass;
import lombok.val;

/**
 * Utility class for formatting the Output
 */
@UtilityClass
public class OutputFormatter {

    /**
     * Formats the output based on the provided parsed argument and value.
     *
     * @param parsedArgument an instance of {@link ParsedArgument} that specifies the output format
     *                       and action to be performed.
     * @param value          the value to be formatted, provided as a string. For "sum", it is used
     *                       directly, and for "minMax", it is expected to be a comma-separated string
     *                       representing minimum and maximum values.
     * @return a JSON-formatted string based on the action ("sum" or "minMax"),
     * or the raw {@code value} if the format or action does not match.
     * @throws NumberFormatException if the {@code value} is not a valid number when parsing
     *                               for "minMax" action.
     *                               <p>
     *                               <p>
     *                               Possible Unit tests for this Method to check if given values are formatted correctly
     */
    public String formatOutput(final ParsedArgument parsedArgument, final String value) {
        if ("JSON".equals(parsedArgument.outputFormat())) {
            if ("sum".equals(parsedArgument.action())) {
                return String.format("{\"sum\": %s}", value);
            }
            if ("minMax".equals(parsedArgument.action())) {
                val parts = value.split(",");
                val min = Double.parseDouble(parts[0].trim());
                val max = Double.parseDouble(parts[1].trim());

                return String.format("{ \"min\": %f, \"max\": %f }", min, max);
            }
        }
        return value;
    }
}
