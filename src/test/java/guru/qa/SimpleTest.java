package guru.qa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("SimpleTest")
public class SimpleTest {


    @Test
    void simpleGreenTest() {
        assertTrue(3 > 2);
    }

    @Test
    void simpleRedTest() {
        assertTrue(2 > 3);
    }

    @Test
    void simpleBrokenTest() throws IllegalAccessException {
        throw new IllegalAccessException();
    }

}
