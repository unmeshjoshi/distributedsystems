package com.distributed;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class RingTest {

    public static final int NO_OF_BITS = 8;

    @Test
    public void testGetNodeIdToTheRightOf() throws Exception {
        Integer maxOfRing = (int)(Math.pow(2, NO_OF_BITS));
        Ring ring = new Ring(Arrays.asList(45, 32, 132, 234, 99, 199), maxOfRing);
        assertEquals((long)45, (long)ring.getNodeIdToTheRightOf(32));
    }

    @Test
    public void getKeyMappedTo() {
        Integer maxOfRing = (int)(Math.pow(2, NO_OF_BITS));
        Ring ring = new Ring(Arrays.asList(45, 32, 132, 234, 99, 199), maxOfRing);
        Integer key = 12;
        assertEquals((long) 32, (long) ring.getMappingNode(key));
    }
}