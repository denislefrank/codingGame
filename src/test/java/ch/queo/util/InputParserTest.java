package ch.queo.util;

import ch.queo.cli.util.InputParser;
import lombok.val;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class InputParserTest {
    static Stream<Arguments> provideTestCases() {
        return Stream.of(
                // Valid Input
                Arguments.of("1.2, 3.4, 5.6", List.of(1.2, 3.4, 5.6), null),
                Arguments.of("0.1, -2.3, 4E5", List.of(0.1, -2.3, 4E5), null),

                // Empty Input
                Arguments.of(" ", null, IllegalArgumentException.class),

                // Invalid Input
                Arguments.of("abc, 123", null, NumberFormatException.class),
                Arguments.of("1.2, 3.4, xyz", null, NumberFormatException.class)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testReadFromStdIn(String input, List<Double> expectedOutput, Class<? extends Exception> expectedException) {
        val testInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(testInput);

        if (expectedException != null) {
            // if exception is given, check it
            assertThrows(expectedException, InputParser::readFromStdIn);
        } else {
            // otherwise, check values
            List<Double> result = InputParser.readFromStdIn();
            assertEquals(expectedOutput, result);
        }
    }
}
