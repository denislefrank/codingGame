package ch.queo.cli.action;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Lt4ActionTest {

    static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(List.of(5.0, 6.5, 7.2, 4.1), "[]"),
                Arguments.of(List.of(1.0, 2.5, 4.0), "[1.0, 2.5]"),
                Arguments.of(List.of(), "[]"),
                Arguments.of(List.of(1.0, 4.5, 6.0, 3.0, 8.0), "[1.0, 3.0]"),
                Arguments.of(List.of(4.0, 4.0, 4.1), "[]"),
                Arguments.of(List.of(10.0, 5.0, -3.0), "[-3.0]")
        );
    }

    @ParameterizedTest(name = "[{index}] Input: {0} -> Expected: {1}")
    @MethodSource("provideTestCases")
    void testLt4Action(List<Double> numbers, String expectedResult) {
        // Arrange
        Lt4Action action = new Lt4Action();

        // Act
        String result = action.execute(numbers);

        // Assert
        assertEquals(expectedResult, result);
    }
}
