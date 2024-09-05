package telran.util;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public abstract class AbstractMapTest {

    Integer[] keys = {1, -1};
    Map<Integer, Integer> map;
    Integer[] array = {3, -10, 20, 1, 10, 8, 100, 17};

    void setUp() {
        Arrays.stream(array).forEach(n -> map.put(n, newValue(n)));
    }

    private Integer newValue(Integer key) {
      return key * 10;
  }

    abstract <T> void runTest(T[] expected, T[] actual);

    void getTest() {
        for (Integer key : array) {
            Integer expected = key * 10;
            Integer actual = map.get(key);
            assertEquals(expected, actual);
        }
    }

    @Test
    void putTest () {
        assertNull(map.put(array[0], newValue(array[0])));
        assertEquals(newValue(2000), map.put(2000, newValue(2000)));
        assertEquals(newValue(2000), map.get(2000));
        assertEquals(newValue(5000), map.put(5000, newValue(5000)));
        assertEquals(newValue(5000), map.get(5000));
    }

    @Test
    void containsKeyTest() {
        assertTrue(map.containsKey(8));
        assertFalse(map.containsKey(6));
    }

    @Test
    void containsValueTest() {
        assertTrue(map.containsValue(80));
        assertFalse(map.containsValue(60));
    }


    @Test
    void valuesTest() {
        Integer[] expected = { 30, -100, 200, 10, 100, 80, 1000, 170 };
        Collection<Integer> collection = (Collection<Integer>) map.values();
        runTest(expected, collection.stream().sorted().toArray(Integer[]::new));
    }

    @Test
    void keySetTest() {
        Set<Integer> set = (Set<Integer>) map.keySet();
        runTest(array, set.stream().toArray(Integer[]::new));
    }

    @Test
    void sizeTest() {
        assertEquals(array.length, map.size());
        map.put(9, 4);
        assertEquals(array.length+1, map.size());
    }


    @Test
    void isEmptyTest() {
        assertFalse(map.isEmpty());
    }



}
