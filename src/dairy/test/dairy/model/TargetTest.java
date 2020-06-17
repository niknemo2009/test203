package dairy.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TargetTest {

    static Stream<Arguments> stringProvider(){
        return Stream.of(Arguments.of("sampleTarget",Integer.MIN_VALUE),
                Arguments.of("sampleTarget", Integer.MAX_VALUE));
    }

    @ParameterizedTest
    @DisplayName("My first MethodSource test: show records from interval")
    @MethodSource("stringProvider")
    void addTarget(String targetName, int timeOnTargetRequired) {
        Target target = new Target();
        target.addTarget(targetName, timeOnTargetRequired);
        assertTrue(target.getTargetsArrayList().size()>=1);
    }
}