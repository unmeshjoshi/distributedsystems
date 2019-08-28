package com.distributed;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import java.util.Arrays;

public class FingerTableTest {

    public static final int NO_OF_BITS = 9;

    @Test @Ignore
    public void shouldGiveFingerTableEntries() {
        //no. of bits = 9
        Integer maxNodeId = (int)(Math.pow(2, NO_OF_BITS));
        Ring ring = new Ring(Arrays.<Integer>asList(1, 12, 123, 234, 345, 456, 501), maxNodeId);
        FingerTable.createFor(ring, NO_OF_BITS);
    }

    @Test
    public void shouldGiveFingerTableEntries1() {
        //no. of bits = 8
        int noOfBits = 8;
        Integer maxNodeId = (int)(Math.pow(2, noOfBits));
        Ring ring = new Ring(Arrays.<Integer>asList(45, 32, 132, 234, 99, 199), maxNodeId);
        FingerTable.createFor(ring, NO_OF_BITS);
    }
}