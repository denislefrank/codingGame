package ch.queo.cli.option;

import lombok.Getter;

@Getter
public enum ArgumentOption {
    INPUT("i", "input", "Input Value"),
    OUTPUT("o", "output", "Output Value"),
    ACTION("a", "action", "Action: sum, minMax oder lt4");

    private final String shortOption;
    private final String longOption;
    private final String description;

    ArgumentOption(String shortOption, String longOption, String description) {
        this.shortOption = shortOption;
        this.longOption = longOption;
        this.description = description;
    }
}
