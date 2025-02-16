package ch.queo.cli.util;

import ch.queo.cli.dto.ParsedArgument;
import ch.queo.cli.format.InputFormatter;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
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
            case "FILE" -> readFromFile(inputArgument);
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

    /**
     * Reads a list of double values from a file provided by the user through console input.
     *
     * @param parsedArgument A {@link ParsedArgument} object used to validate and process the input file lines.
     * @return A {@link List} of {@link Double} values extracted from the file.
     * @throws NumberFormatException If the file does not exist, is not a regular file, or an error occurs
     *                               during file processing.
     */
    public List<Double> readFromFile(final ParsedArgument parsedArgument) {
        System.out.println("Please provide the local File:");
        val scanner = new Scanner(System.in);
        val input = scanner.nextLine();
        val path = Path.of(input);
        if (Files.exists(path) && Files.isRegularFile(path)) {
            try {
                return Files.lines(path)
                        .map(String::trim)
                        .filter(line -> !line.isEmpty())
                        .map(line -> {
                            try {
                                return InputFormatter.checkInputFormat(line, parsedArgument);
                            } catch (NumberFormatException e) {
                                throw new NumberFormatException("The given File does not exist");
                            }
                        })
                        .flatMap(Collection::stream)
                        .toList();
            } catch (IOException exception) {
                throw new NumberFormatException("The given File does not exist");
            }
        }
        throw new NumberFormatException("The given File does not exist");
    }
}
