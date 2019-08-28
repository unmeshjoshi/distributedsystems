package raft;

import java.util.HashMap;
import java.util.Map;

class ServerState {
    Map<Integer, LogEntry> log = new HashMap<>();
    long votedForCandidateId;
    long currentTerm;
}
