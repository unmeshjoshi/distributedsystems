package raft;

import java.util.List;

public class AppendEntry {
    long term;
    RaftServer leader;
    //possibly club together as logentry
    long previousLogIndex;
    long previousLogTerm;
    List<LogEntry> entries;
    long leaderCommitIndex;

    public AppendEntry(long term, RaftServer leader, long previousLogIndex, long previousLogTerm, List<LogEntry> entries, long leaderCommitIndex) {
        this.term = term;
        this.leader = leader;
        this.previousLogIndex = previousLogIndex;
        this.previousLogTerm = previousLogTerm;
        this.entries = entries;
        this.leaderCommitIndex = leaderCommitIndex;
    }

    public long getTerm() {
        return term;
    }

    public RaftServer getLeader() {
        return leader;
    }

    public long getPreviousLogIndex() {
        return previousLogIndex;
    }

    public long getPreviousLogTerm() {
        return previousLogTerm;
    }

    public List<LogEntry> getEntries() {
        return entries;
    }

    public long getLeaderCommitIndex() {
        return leaderCommitIndex;
    }
}
