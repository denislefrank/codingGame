package ch.queo.cli.dto;

import lombok.Builder;

/**
 * A record that represents parsed command-line arguments.
 */
@Builder
public record ParsedArgument(String input, String output, String action) {
}
