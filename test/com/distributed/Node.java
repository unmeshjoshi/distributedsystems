package com.distributed;

/**
 * Created by unmesh on 1/7/16.
 */
public class Node {
    Integer id;
    FingerTable fingerTable;

    public Node(Integer id) {
        this.id = id;
    }

    public void setFingerTable(FingerTable fingerTable) {
        this.fingerTable = fingerTable;
    }
}
