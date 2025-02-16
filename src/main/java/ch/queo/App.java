package ch.queo;

import ch.queo.cli.action.AbstractAction;
import ch.queo.cli.format.OutputFormatter;
import ch.queo.cli.type.ActionType;
import ch.queo.cli.util.CommandLineParser;
import ch.queo.cli.util.InputParser;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Java application that performs simple actions on a list of floating point numbers.
 */
@Slf4j
public class App {
    public static void main(String[] args) {
        try {
            log.info("Given Arguments " + Arrays.toString(args));
            val parseArguments = CommandLineParser.parseArguments(args);
            log.info("Parsed Arguments " + parseArguments);

            val numbers = InputParser.readFromInput(parseArguments);

            val calculation = AbstractAction.executeAction(ActionType.fromString(parseArguments.action()), numbers);

            val formattedOutput = OutputFormatter.formatOutput(parseArguments, calculation);
            System.out.println("Your Result is");
            System.out.println(formattedOutput + "\n");
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.exit(2);
        } catch (InvalidParameterException e) {
            System.err.println(e.getMessage());
            System.exit(4);
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
