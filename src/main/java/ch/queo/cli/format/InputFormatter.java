package ch.queo.cli.format;

import ch.queo.cli.dto.ParsedArgument;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.security.InvalidParameterException;
import java.util.List;

@UtilityClass
public class InputFormatter {

    public List<Double> checkInputFormat(final String inputValue, final ParsedArgument parsedArgument) {
        if (parsedArgument.inputFormat().equals("csv")) {

            // fail fast
            if (inputValue.equals("[]")) {
                throw new InvalidParameterException("Input is empty");
            }

            val formattedInput = transformString(inputValue);

            try {
                return formattedInput.stream()
                        .map(String::trim)
                        .map(Double::parseDouble)
                        .toList();
            } catch (NumberFormatException e) {
                throw new NumberFormatException("Input has an not floating number: " + e.getMessage());
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private List<String> transformString(final String inputValue) {
        if (!inputValue.startsWith("[") || !inputValue.endsWith("]")) {
            throw new InvalidParameterException("Expecting CSV Format");
        }
        return List.of(inputValue.substring(1, inputValue.length() - 1).split(","));
    }
}
