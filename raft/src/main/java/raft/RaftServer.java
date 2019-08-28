package raft;

public interface RaftServer {
    VoteResponse requestVote(RequestVote voteRequest);
    AppendEntryResponse appendEntries(AppendEntry appendEntry);
}
