package ch.queo.cli.action;

import java.util.List;

/**
 * Implementation of the {@link AbstractAction} that calculates the sum of a list of numbers.
 *
 * <p>This class overrides the {@link AbstractAction#execute(List)} method to perform
 * the sum calculation for the provided list of numbers.</p>
 */
public class SumAction extends AbstractAction {

    /**
     * Calculates the sum of the provided list of numbers.
     *
     * @param numbers The list of {@link Double} values to sum.
     * @return The sum of the numbers as a {@link String}.
     */
    @Override
    public String execute(List<Double> numbers) {
        double sum = numbers.stream().mapToDouble(Double::doubleValue).sum();
        return String.valueOf(sum);
    }
}
