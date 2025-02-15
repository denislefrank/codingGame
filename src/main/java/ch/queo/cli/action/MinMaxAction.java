package ch.queo.cli.action;

import java.util.Collections;
import java.util.List;

/**
 * Implementation of the {@link AbstractAction} that calculates the smallest and biggest number of a list of numbers.
 */
public class MinMaxAction extends AbstractAction {

    /**
     * Calculates the Min and Max Value of the provided list of numbers.
     *
     * @param numbers The list of {@link Double} values to sum.
     * @return The Min and Max of the numbers as a {@link String}.
     */
    @Override
    public String execute(final List<Double> numbers) {
        return Collections.min(numbers) + "," + Collections.max(numbers);
    }
}
