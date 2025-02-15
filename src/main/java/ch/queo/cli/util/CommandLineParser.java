package ch.queo.cli.util;

import ch.queo.cli.dto.ParsedArgument;
import ch.queo.cli.type.ArgumentType;
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
    public ParsedArgument parseArguments(final String[] args) {

        val options = mapOptions();

        val parser = new DefaultParser().parse(options, args);

        if (!parser.hasOption(ArgumentType.INPUT.getShortOption()) || !parser.hasOption(ArgumentType.ACTION.getShortOption())) {
            throw new IllegalArgumentException("Missing Arguments: -i and -a are mandatory.");
        }

        return ParsedArgument
                .builder()
                .input(parser.getOptionValue(ArgumentType.INPUT.getShortOption(), "-"))
                .output(parser.getOptionValue(ArgumentType.OUTPUT.getShortOption(), "-"))
                .action(parser.getOptionValue(ArgumentType.ACTION.getShortOption()))
                .inputFormat(parser.getOptionValue(ArgumentType.INPUT_FORMAT.getShortOption(), "csv"))
                .outputFormat(parser.getOptionValue(ArgumentType.OUTPUT_FORMAT.getShortOption(), "csv"))
                .build();

    }

    private Options mapOptions() {
        val options = new Options();
        options.addOption(ArgumentType.INPUT.getShortOption(), ArgumentType.INPUT.getLongOption(), true, ArgumentType.INPUT.getDescription());
        options.addOption(ArgumentType.OUTPUT.getShortOption(), ArgumentType.OUTPUT.getLongOption(), true, ArgumentType.OUTPUT.getDescription());
        options.addOption(ArgumentType.ACTION.getShortOption(), ArgumentType.ACTION.getLongOption(), true, ArgumentType.ACTION.getDescription());
        options.addOption(ArgumentType.INPUT_FORMAT.getShortOption(), ArgumentType.INPUT_FORMAT.getLongOption(), true, ArgumentType.INPUT_FORMAT.getDescription());
        options.addOption(ArgumentType.OUTPUT_FORMAT.getShortOption(), ArgumentType.OUTPUT_FORMAT.getLongOption(), true, ArgumentType.OUTPUT_FORMAT.getDescription());
        return options;
    }
}
