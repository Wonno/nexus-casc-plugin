package io.github.asharapov.nexus.casc.internal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junitpioneer.jupiter.SetEnvironmentVariable;

import java.net.URL;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class InterpolatorTest {

    @Test
    void interpolateWithFile() {
        final URL url = getClass().getClassLoader().getResource("test");
        assertNotNull(url);
        assertEquals("hello world", new Interpolator().interpolate("hello ${file:" + url.getPath() + "}"));
    }

    @SetEnvironmentVariable(key = "greet", value = "world")
    @ParameterizedTest
    @MethodSource("provideTemplatesWithExistingEnvVar")
    void interpolateWithEnvVar(String expected, String template) {
        assertEquals(expected, new Interpolator().interpolate(template));
    }

    @ParameterizedTest
    @MethodSource("provideTemplatesWithMissingEnvVar")
    void interpolateWithNonExistingEnvVar(String expected, String template) {
        assertEquals(expected, new Interpolator().interpolate(template));
    }

    private static Stream<Arguments> provideTemplatesWithMissingEnvVar() {
        return Stream.of(
                Arguments.of("hello $IDONOTEXIST", "hello $IDONOTEXIST"),
                Arguments.of("hello ${IDONOTEXIST}", "hello ${IDONOTEXIST}"),
                Arguments.of("hello ", "hello ${IDONOTEXIST:}"),
                Arguments.of("hello ", "hello ${IDONOTEXIST:}"),
                Arguments.of("hello world", "hello ${IDONOTEXIST:world}"),
                Arguments.of("hello world", "hello ${IDONOTEXIST:\"world\"}"));
    }

    private static Stream<Arguments> provideTemplatesWithExistingEnvVar() {
        return Stream.of(
                Arguments.of("hello world", "hello $greet"),
                Arguments.of("hello world", "hello ${greet}"),
                Arguments.of("hello world", "hello ${greet:\"\"}"),
                Arguments.of("hello world", "hello ${greet:}"),
                Arguments.of("hello world", "hello ${greet:foo}"));
    }

}
