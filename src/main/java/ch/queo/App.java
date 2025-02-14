package ch.queo;

import ch.queo.cli.util.CommandLineParser;
import ch.queo.cli.util.InputParser;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        try {
            log.info("Given Arguments " + Arrays.toString(args));
            val parseArguments = CommandLineParser.parseArguments(args);
            log.info("Parsed Arguments " + parseArguments);

            // Read stdIn
            List<Double> numbers = InputParser.readFromStdIn();
            log.info(numbers.toString());
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            System.exit(4);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
