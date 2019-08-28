package raft;

public interface RaftGateway {
    VoteResponse requestVote(long serverId, RequestVote voteRequest);
    AppendEntryResponse appendEntries(long serverId, AppendEntry appendEntry);
}
