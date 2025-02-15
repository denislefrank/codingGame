package ch.queo;

import ch.queo.cli.action.AbstractAction;
import ch.queo.cli.type.ActionType;
import ch.queo.cli.util.CommandLineParser;
import ch.queo.cli.util.InputParser;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import java.security.InvalidParameterException;
import java.util.Arrays;

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

            val numbers = InputParser.readFromInput(parseArguments);

            val result = AbstractAction.executeAction(ActionType.fromString(parseArguments.action()), numbers);
            System.out.println("Your Result is " + result);
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
