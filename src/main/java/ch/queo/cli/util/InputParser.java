package ch.queo.cli.util;

import ch.queo.cli.dto.ParsedArgument;
import ch.queo.cli.format.InputFormatter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;
import java.util.Scanner;

/**
 * Utility class for parsing command-line values.
 */
@UtilityClass
@Slf4j
public class InputParser {

    public List<Double> readFromInput(final ParsedArgument inputArgument) {
        return switch (inputArgument.input()) {
            case "FILE" -> throw new UnsupportedOperationException("File is not supported yet");
            case "URL" -> throw new UnsupportedOperationException("URL is not supported yet");
            case "-" -> readFromStdIn(inputArgument);
            default -> throw new IllegalStateException("Unexpected value: " + inputArgument.input());
        };
    }

    /**
     * Reads a list of floating-point numbers from standard input.
     *
     * @return a list of parsed double values
     * @throws IllegalArgumentException if the input is empty
     * @throws NumberFormatException    if any value in the input is not a valid floating-point number
     */
    public List<Double> readFromStdIn(final ParsedArgument parsedArgument) {
        log.info("Please provide a List of floating Numbers:");
        val scanner = new Scanner(System.in);
        val input = scanner.nextLine();

        if (input.isBlank()) {
            log.error("Error, Input is empty");
            throw new IllegalArgumentException("Error, Input is empty");
        }

        log.info("Your Input was " + input);
        return InputFormatter.checkInputFormat(input, parsedArgument);
    }
}
