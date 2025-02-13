package ch.queo.cli.util;

import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

@UtilityClass
public class InputParser {
    private final String FLOATING_POINT_REGEX =
            "^[+-]?(\\d+(\\.\\d*)?|\\.\\d+)([eE][+-]?\\d+)?$";

    public List<Double> readFromStdIn() {
        System.out.println("Please provide a List of floating Numbers:");
        val scanner = new Scanner(System.in);
        val input = scanner.nextLine();

        if (input.isBlank()) {
            System.err.println("Error, Input is empty");
            System.exit(1);
        }

        System.out.println("Your Input was " + input);

        val inputValues = Stream.of(input.split(","))
                .map(String::trim)
                .toList();

        for (val value : inputValues) {
            if (!isValidFloatingPoint(value)) {
                System.err.println("Input has an not floating number: " + value);
                System.exit(4);
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
