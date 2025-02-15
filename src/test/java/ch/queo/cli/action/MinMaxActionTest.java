package ch.queo.cli.action;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinMaxActionTest {

    static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of(List.of(1.2, 3.4, 5.6, 7.8), "1.2,7.8"),
                Arguments.of(List.of(-1.2, -3.4, -5.6, -7.8), "-7.8,-1.2"),
                Arguments.of(List.of(-1.2, 3.4, 0.0, 7.8), "-1.2,7.8"),
                Arguments.of(Collections.singletonList(42.0), "42.0,42.0"),
                Arguments.of(List.of(0.0, 0.0, 0.0), "0.0,0.0")
        );
    }

    @ParameterizedTest(name = "[{index}] Input: {0} -> Expected: {1}")
    @MethodSource("provideTestCases")
    @DisplayName("Test MinMaxAction with various inputs")
    void testMinMaxAction(List<Double> numbers, String expectedResult) {
        // Arrange
        val action = new MinMaxAction();

        // Act
        val result = action.execute(numbers);

        // Assert
        assertEquals(expectedResult, result);
    }
}
