package ch.queo;

import ch.queo.cli.CommandLineParser;
import ch.queo.cli.util.InputParser;
import lombok.val;

import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Given Arguments " + Arrays.toString(args));
        val parseArguments = CommandLineParser.parseArguments(args);
        System.out.println("Parsed Arguments " + parseArguments);

        // Read stdIn
        List<Double> numbers = InputParser.readFromStdIn();
        System.out.println(numbers);
    }
}
