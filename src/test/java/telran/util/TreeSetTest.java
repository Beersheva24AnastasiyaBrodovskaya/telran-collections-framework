package telran.util;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;

public class TreeSetTest extends SetTest {
    @BeforeEach
    @Override
    void setUp() {
        collection = new TreeSet<>();
        super.setUp();
    }
    
    @Override
    protected void runTest (Integer[] expected) {
        Integer [] expectedSorted = Arrays.copyOf(expected, expected.length);
        Arrays.sort(expectedSorted);
        Integer [] actual = collection.stream().toArray(Integer[]::new);
        
        assertArrayEquals(expectedSorted, actual);
        assertEquals(expected.length, collection.size());
    }

}