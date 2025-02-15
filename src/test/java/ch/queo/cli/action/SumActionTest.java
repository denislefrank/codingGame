package ch.queo.cli.action;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SumActionTest {

    private static Stream<Arguments> provideSumTestData() {
        return Stream.of(
                Arguments.of(List.of(1.0, 2.0, 3.0), 6.0 + "\n"),
                Arguments.of(List.of(-1.0, -2.0, -3.0), -6.0 + "\n"),
                Arguments.of(List.of(0.0, 0.0, 0.0), 0.0 + "\n"),
                Arguments.of(List.of(1.5, 2.5, 3.0), 7.0 + "\n"),
                // empty input
                Arguments.of(List.of(), 0.0 + "\n")
        );
    }

    @ParameterizedTest
    @MethodSource("provideSumTestData")
    void testExecute(List<Double> inputValues, String expected) {
        // Arrange
        SumAction sumAction = new SumAction();

        // Act
        String result = sumAction.execute(inputValues);

        // Assert
        assertEquals(expected, result);
    }
}
