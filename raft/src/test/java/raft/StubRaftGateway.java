package raft;

import java.util.HashMap;
import java.util.Map;

class StubRaftGateway implements RaftGateway {

    private VoteResponse voteResponse;
    private AppendEntry appendEntryRequest;
    private RequestVote voteRequest;
    Map<Long, AppendEntry> appendEntryMap = new HashMap<>();

    public StubRaftGateway() {
    }

    public StubRaftGateway(VoteResponse voteResponse) {
        this.voteResponse = voteResponse;
    }

    public AppendEntry getAppendEntryRequest() {
        return appendEntryRequest;
    }

    public RequestVote getVoteRequest() {
        return voteRequest;
    }

    @Override
    public VoteResponse requestVote(long serverId, RequestVote voteRequest) {
        this.voteRequest = voteRequest;
        return voteResponse;
    }

    @Override
    public AppendEntryResponse appendEntries(long serverId, AppendEntry appendEntry) {
        appendEntryMap.put(serverId, appendEntry);
        return new AppendEntryResponse();
    }
}
