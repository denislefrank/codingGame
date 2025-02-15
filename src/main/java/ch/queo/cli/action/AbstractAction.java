package ch.queo.cli.action;

import ch.queo.cli.type.ActionType;

import java.util.List;

/**
 * Abstract class for defining actions that can be executed based on the specified {@link ActionType}.
 * <p>
 * {@link #execute(List)} method, which subclasses must implement. It also includes a static
 * {@link #executeAction(ActionType, List)} method that delegates execution to the appropriate subclass
 * based on the given {@link ActionType}.
 * </p>
 */
public abstract class AbstractAction {

    /**
     * Executes an action based on the provided {@link ActionType} and input values.
     *
     * @param actionType  The type of the action to execute (e.g., SUM).
     * @param inputValues The input values on which the action is to be performed.
     * @return The result of the action as a {@link String}.
     * @throws IllegalArgumentException if the specified {@link ActionType} is not supported.
     */
    public static String executeAction(final ActionType actionType, final List<Double> inputValues) {
        // Add here all possible actionTypes
        switch (actionType) {
            case SUM:
                return new SumAction().execute(inputValues);
            default:
                throw new IllegalArgumentException("Unsupported action: " + actionType);
        }
    }

    /**
     * Executes the specific logic for the action.
     *
     * @param inputValues The input values on which the action is to be performed.
     * @return The result of the action as a {@link String}.
     */
    protected abstract String execute(List<Double> inputValues);
}
