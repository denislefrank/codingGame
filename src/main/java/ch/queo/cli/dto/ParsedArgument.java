package ch.queo.cli.dto;

import lombok.Builder;

/**
 * A record that represents parsed command-line arguments.
 */
@Builder
public record ParsedArgument(String input, String output, String action, String inputFormat, String outputFormat) {
    public ParsedArgument(String csv) {
        this(null, null, null, csv, null);
    }
}
