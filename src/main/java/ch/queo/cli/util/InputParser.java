package ch.queo.cli.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Utility class for parsing command-line values.
 */
@UtilityClass
@Slf4j
public class InputParser {
    private final String FLOATING_POINT_REGEX =
            "^[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)?$";

    /**
     * Reads a list of floating-point numbers from standard input.
     *
     * @return a list of parsed double values
     * @throws IllegalArgumentException if the input is empty
     * @throws NumberFormatException    if any value in the input is not a valid floating-point number
     */
    public List<Double> readFromStdIn() {
        log.info("Please provide a List of floating Numbers:");
        val scanner = new Scanner(System.in);
        val input = scanner.nextLine();

        if (input.isBlank()) {
            log.error("Error, Input is empty");
            throw new IllegalArgumentException("Error, Input is empty");
        }

        log.info("Your Input was " + input);

        val inputValues = Stream.of(input.split(","))
                .map(String::trim)
                .toList();

        for (val value : inputValues) {
            if (!isValidFloatingPoint(value)) {
                throw new NumberFormatException("Input has an not floating number: " + value);
            }
        }
        return inputValues.stream()
                .map(Double::parseDouble)
                .toList();
    }

    private boolean isValidFloatingPoint(String value) {
        return value.matches(FLOATING_POINT_REGEX);
    }
}
