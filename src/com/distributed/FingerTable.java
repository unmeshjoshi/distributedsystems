package com.distributed;

import java.util.HashMap;
import java.util.Map;

public class FingerTable {
    Map<Integer, Integer> table = new HashMap<Integer, Integer>();

    public FingerTable(Map<Integer, Integer> table) {
        this.table = table;
    }

    public void print(String s) {
        for (Integer integer : table.keySet()) {
            System.out.println(s + integer + " " + table.get(integer));
        }
    }

    public static FingerTable createFor(int nodeId, int noOfBits, Ring ring) {
        Map<Integer, Integer> table = new HashMap<Integer, Integer>();
        for (int i = 0; i < noOfBits; i++) {
            int nextNodeId = (int) (nodeId + Math.pow(2, i));
            if (!ring.hasNode(nextNodeId)) {
                nextNodeId = ring.getNextNodeId(nextNodeId);
            }
            table.put(i, nextNodeId);
        }
        return new FingerTable(table);
    }

    public static void createFor(Ring ring, int notOfBits) {
        for (Integer nodeId : ring.getNodeIds()) {
            System.out.println("nodeId = " + nodeId);
            FingerTable.createFor(nodeId, notOfBits, ring).print(" ");
        }
    }
}
