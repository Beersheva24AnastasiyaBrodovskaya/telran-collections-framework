package telran.util;

import java.util.Arrays;
import static java.util.Arrays.sort;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;

public class TreeMapTest extends AbstractMapTest {
  

    @Override
    <T> void runTest(T[] expected, T[] actual) {
        T[] expectedSorted = Arrays.copyOf(expected, expected.length);
        sort(expectedSorted);

        assertArrayEquals(expectedSorted, actual);
    }

    @BeforeEach
    @Override
    void setUp() {
        map = new TreeMap<>();
        super.setUp();
    }
}