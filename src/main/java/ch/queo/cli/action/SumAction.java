package ch.queo.cli.action;

import java.util.List;

/**
 * Implementation of the {@link AbstractAction} that calculates the sum of a list of numbers.
 */
public class SumAction extends AbstractAction {

    /**
     * Calculates the sum of the provided list of numbers.
     *
     * @param numbers The list of {@link Double} values to sum.
     * @return The sum of the numbers as a {@link String}.
     */
    @Override
    public String execute(final List<Double> numbers) {
        return String.valueOf(numbers.stream().mapToDouble(Double::doubleValue).sum());
    }
}
