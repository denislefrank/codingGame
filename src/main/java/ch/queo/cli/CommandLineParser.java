package ch.queo.cli;

import ch.queo.cli.dto.ParsedArgument;
import ch.queo.cli.option.ArgumentOption;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;

public class CommandLineParser {

    @SneakyThrows
    public static ParsedArgument parseArguments(String[] args) {

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
