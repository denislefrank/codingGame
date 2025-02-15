package ch.queo.format;

import ch.queo.cli.dto.ParsedArgument;
import ch.queo.cli.format.InputFormatter;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class InputFormatterTest {

    static Stream<Arguments> validInputsProvider() {
        return Stream.of(
                Arguments.of("[1.2, 3.4, 5.6]", new ParsedArgument("csv"), List.of(1.2, 3.4, 5.6)),
                Arguments.of("[ 1.2 ,  3.4 ,  5.6 ]", new ParsedArgument("csv"), List.of(1.2, 3.4, 5.6))
        );
    }

    static Stream<Arguments> invalidInputsProvider() {
        return Stream.of(
                Arguments.of("[1.2, abc, 3.4]", new ParsedArgument("csv"), NumberFormatException.class),
                Arguments.of("1.2, 3.4, 5.6", new ParsedArgument("csv"), InvalidParameterException.class),
                Arguments.of("[1.2, , 3.4]", new ParsedArgument("csv"), NumberFormatException.class),
                Arguments.of("[1.2, 3.4, 5.6]", new ParsedArgument("json"), IllegalArgumentException.class),
                Arguments.of("[]", new ParsedArgument("csv"), InvalidParameterException.class)
        );
    }

    // Test Data Providers

    @ParameterizedTest(name = "[{index}] Input: \"{0}\" -> Expected: {1}")
    @MethodSource("validInputsProvider")
    @DisplayName("Test valid CSV inputs")
    void testValidCsvInputs(String inputValue, ParsedArgument parsedArgument, List<Double> expectedOutput) {
        // Act
        val result = InputFormatter.checkInputFormat(inputValue, parsedArgument);

        // Assert
        assertNotNull(result);
        assertEquals(expectedOutput, result);
    }

    @ParameterizedTest(name = "[{index}] Input: \"{0}\"")
    @MethodSource("invalidInputsProvider")
    @DisplayName("Test invalid CSV inputs")
    void testInvalidCsvInputs(String inputValue, ParsedArgument parsedArgument, Class<? extends Exception> expectedException) {
        // Act & Assert
        assertThrows(expectedException, () -> InputFormatter.checkInputFormat(inputValue, parsedArgument));
    }
}
