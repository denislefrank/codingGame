package ch.queo.cli.dto;

import lombok.Builder;

@Builder
public record ParsedArgument(String input, String output, String action) {
}
