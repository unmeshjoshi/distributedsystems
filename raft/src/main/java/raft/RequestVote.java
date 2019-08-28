package raft;

public class RequestVote {
    long term;
    long raftServerId;
    //possibly club as logentry
    long lastLogIndex;
    long lastLogTerm;

    public RequestVote(long term, long raftServerId, int lastLogIndex, int lastLogTerm) {
        this.term = term;
        this.raftServerId = raftServerId;
        this.lastLogIndex = lastLogIndex;
        this.lastLogTerm = lastLogTerm;
    }
}
