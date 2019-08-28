import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class BloomFilterTest {

    @Test
    public void shouldSetBitSet() {
        BloomFilter filter = new BloomFilter();
        filter.add(2010);
        filter.add(2013);
        filter.add(2007);
        filter.add(2004);
        Map<Integer, Boolean> allBitsSet = filter.getAllBitsSet();
        assertEquals(32, allBitsSet.size());
        System.out.println("allBitsSet = " + allBitsSet);
    }

    @Test
    public void shouldTellIfValueExists() {
        BloomFilter filter = new BloomFilter();
        filter.add(2010);
        filter.add(2013);
        filter.add(2007);
        filter.add(2004);
        Map<Integer, Boolean> allBitsSet = filter.getAllBitsSet();
        assertEquals(32, allBitsSet.size());
        System.out.println("allBitsSet = " + allBitsSet);
        assertTrue(filter.contains(2010));
        assertTrue(filter.contains(2013));
        assertTrue(filter.contains(2007));
        assertTrue(filter.contains(2004));
    }

}