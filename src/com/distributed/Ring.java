package com.distributed;

import java.util.Collections;
import java.util.List;

public class Ring {
    private List<Integer> nodeIds;
    private Integer maxIdOnRing;

    public Ring(List<Integer> nodeIds, Integer maxIdOnRing) {

        this.nodeIds = nodeIds;
        this.maxIdOnRing = maxIdOnRing;
    }

    public List<Integer> getNodeIds() {
        return nodeIds;
    }

    public Integer getNextNodeId(Integer nodeId) {
        if (nodeId > Collections.max(nodeIds)) {
            return getNodeIdToTheRightOf(nodeId % maxIdOnRing);
        }
        return getNodeIdToTheRightOf(nodeId);
    }

    public Integer getNodeIdToTheRightOf(Integer nodeId) {
        Integer nextNodeIdToRight = Collections.max(nodeIds);
        for (Integer id : nodeIds) {
            if (id > nodeId && id < nextNodeIdToRight) {
                nextNodeIdToRight = id;
            }
        }
        return nextNodeIdToRight;
    }

    public boolean hasNode(Integer nodeId) {
        return nodeIds.contains(nodeId);
    }

    public Integer getMappingNode(Integer key) {
        return this.getNextNodeId(key);
    }
}
