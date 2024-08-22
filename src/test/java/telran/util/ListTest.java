package telran.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

abstract public class ListTest extends CollectionTest{
    List<Integer> list;
    @Override
    void setUp() {
        super.setUp();
        list = (List<Integer>) collection;
    }
   @Test
    void listGetTest() {
        assertEquals(8, list.get(5));
        assertEquals(20, list.get(2));
        assertEquals(null, list.get(8));
    }

    @Test
    void listAddTest() {
        list.add(3, 17);
        list.add(9, 500);
        assertEquals(17, list.get(3));
        assertEquals(500, list.get(9));
        assertEquals(array.length + 2, list.size());
    }

    @Test
    void listRemoveTest() {
        assertEquals(1, list.remove(3));
        assertEquals(array.length - 1, list.size());
    }

    @Test
    void listIndexOfTest() {
        assertEquals(1, list.indexOf(10));
    }

    @Test
    void listLastIndexOfTest() {
        assertEquals(4, list.lastIndexOf(10));
    }
}