package ch.queo.cli.util;

import ch.queo.cli.dto.ParsedArgument;
import ch.queo.cli.option.ArgumentOption;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import lombok.val;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

/**
 * Utility class for parsing command-line arguments into a structured format.
 */
@UtilityClass
public class CommandLineParser {

    /**
     * Parses the provided command-line arguments and converts them into a {@link ParsedArgument} object.
     *
     * @param args The array of command-line arguments passed to the application.
     * @return A {@link ParsedArgument} object containing the parsed input, output, and action values.
     * @throws IllegalArgumentException if the mandatory arguments `-i` (input) or `-a` (action) are missing.
     */
    @SneakyThrows
    public ParsedArgument parseArguments(String[] args) {

        val options = new Options();
        options.addOption(ArgumentOption.INPUT.getShortOption(), ArgumentOption.INPUT.getLongOption(), true, ArgumentOption.INPUT.getDescription());
        options.addOption(ArgumentOption.OUTPUT.getShortOption(), ArgumentOption.OUTPUT.getLongOption(), true, ArgumentOption.OUTPUT.getDescription());
        options.addOption(ArgumentOption.ACTION.getShortOption(), ArgumentOption.ACTION.getLongOption(), true, ArgumentOption.ACTION.getDescription());

        val parser = new DefaultParser().parse(options, args);

        if (!parser.hasOption(ArgumentOption.INPUT.getShortOption()) || !parser.hasOption(ArgumentOption.ACTION.getShortOption())) {
            throw new IllegalArgumentException("Missing Arguments: -i and -a are mandatory.");
        }

        return ParsedArgument
                .builder()
                .input(parser.getOptionValue(ArgumentOption.INPUT.getShortOption()))
                .output(parser.getOptionValue(ArgumentOption.OUTPUT.getShortOption()))
                .action(parser.getOptionValue(ArgumentOption.ACTION.getShortOption()))
                .build();

    }
}
