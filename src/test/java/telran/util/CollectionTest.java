package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public abstract class CollectionTest {
    protected Collection<Integer> collection;
    Integer[] array = {3, 10, 20, 1, 10, 8, 100 , 17};
    void setUp() {
        Arrays.stream(array).forEach(collection::add);
    }
    @Test
    void addTest() {
        assertTrue(collection.add(200));
        assertTrue(collection.add(17));
        assertEquals(array.length + 2, collection.size());
    }
    @Test
    void sizeTest() {
        assertEquals(array.length, collection.size());
    }

    @Test
    void removeTest(){
        assertTrue(collection.remove(1));
        assertTrue(collection.remove(3));
        assertEquals(array.length - 2, collection.size());
    }
    
    @Test
    void containsTest() {
        assertTrue(collection.contains(100));
        assertFalse(collection.contains(500));
    }

    @Test
    void isEmptyTest() {
        assertFalse(collection.isEmpty());
        collection.remove(3);
        collection.remove(10);
        collection.remove(20);
        collection.remove(1);
        collection.remove(10);
        collection.remove(8);
        collection.remove(100);
        collection.remove(17);
        assertTrue(collection.isEmpty());
    }

    @Test
    void iteratorTest() {
        Iterator<Integer> it = collection.iterator();
        Integer[] actual = new Integer[array.length];;

        int index = 0;
        while (it.hasNext()) {
            actual[index++] = it.next();
        }

        assertArrayEquals(array, actual);
        assertThrows(NoSuchElementException.class, it::next);
    }

    @Test
    void streamTest() {
        streamUniqTest(collection.stream());
        streamUniqTest(collection.parallelStream());

        streamSumTest(collection.stream());
        streamSumTest(collection.parallelStream());

        streamSortTest(collection.stream());
        streamSortTest(collection.parallelStream());
    }

    private void streamUniqTest(Stream<Integer> stream) {
        Object[] result = stream.distinct().toArray();
        assertEquals(7, result.length);
    }

    private void streamSumTest(Stream<Integer> stream) {
        int result = stream.mapToInt(Integer::intValue).sum();
        assertEquals(169, result);
    }

    private void streamSortTest(Stream<Integer> stream) {
        Object[] result = stream.sorted().toArray();
        Object[] expended = {1, 3, 8, 10, 10, 17, 20, 100};
        assertArrayEquals(expended, result);
    }

}