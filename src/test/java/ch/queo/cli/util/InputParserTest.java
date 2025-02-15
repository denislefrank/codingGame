package ch.queo.cli.util;

import ch.queo.cli.dto.ParsedArgument;
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
                Arguments.of(
                        "[1.2, 3.4, 5.6]",
                        List.of(1.2, 3.4, 5.6),
                        null,
                        createDefaultParsedArgument()),
                Arguments.of(
                        "[0.1, -2.3, 4E5]",
                        List.of(0.1, -2.3, 4E5),
                        null,
                        createDefaultParsedArgument()),

                // Empty Input
                Arguments.of(
                        " ",
                        null,
                        IllegalArgumentException.class,
                        createDefaultParsedArgument())
        );
    }

    private static ParsedArgument createDefaultParsedArgument() {
        return ParsedArgument.builder()
                .input("-")
                .output("-")
                .action("sum")
                .inputFormat("csv")
                .outputFormat("csv")
                .build();
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
    void testReadFromStdIn(String input,
                           List<Double> expectedOutput,
                           Class<? extends Exception> expectedException,
                           ParsedArgument parsedArgument) {

        val testInput = new ByteArrayInputStream(input.getBytes());
        System.setIn(testInput);

        if (expectedException != null) {
            // if exception is given, check it
            assertThrows(expectedException, () -> InputParser.readFromStdIn(parsedArgument));
        } else {
            // otherwise, check values
            val result = InputParser.readFromStdIn(parsedArgument);
            assertEquals(expectedOutput, result);
        }
    }
}
