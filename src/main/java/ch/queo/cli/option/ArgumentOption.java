package ch.queo.cli.option;

import lombok.Getter;

/**
 * Enum representing the command-line argument options for the application.
 */
@Getter
public enum ArgumentOption {
    /**
     * Represents the input option.
     */
    INPUT("i", "input", "Input Value"),

    /**
     * Represents the output option.
     */
    OUTPUT("o", "output", "Output Value"),

    /**
     * Represents the action option.
     */
    ACTION("a", "action", "Action: sum, minMax oder lt4");

    private final String shortOption;
    private final String longOption;
    private final String description;

    /**
     * Constructs an {@code ArgumentOption} with the specified properties.
     *
     * @param shortOption The short form of the option (e.g., `-i`).
     * @param longOption  The long form of the option (e.g., `--input`).
     * @param description A description explaining the purpose of the option.
     */
    ArgumentOption(String shortOption, String longOption, String description) {
        this.shortOption = shortOption;
        this.longOption = longOption;
        this.description = description;
    }
}
