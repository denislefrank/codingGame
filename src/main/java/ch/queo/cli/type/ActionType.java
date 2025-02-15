package ch.queo.cli.type;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * Enum representing the selected Action
 */
@Getter
public enum ActionType {
    /**
     * Calculates the sum
     */
    SUM("sum"),
    MIN_MAX("minMax");

    private final String actionName;

    /**
     * Constructor for the Enum
     */
    ActionType(String actionName) {
        this.actionName = actionName;
    }

    /**
     * Method to find an ActionType from a string
     *
     * @param actionName The name of the action
     * @return The corresponding ActionType
     * @throws IllegalArgumentException if the actionName does not match any ActionType
     */
    public static ActionType fromString(String actionName) {
        return Stream.of(ActionType.values())
                .filter(action -> action.getActionName().equalsIgnoreCase(actionName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid action: " + actionName));
    }
}
