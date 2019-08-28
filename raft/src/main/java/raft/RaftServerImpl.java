package raft;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RaftServerImpl implements RaftServer {
    private long id;
    private List<Long> peers;

    ServerRole role = ServerRole.Follower;
    long term = 0;
    long previousLogIndex;
    long previousLogTerm;
    long commitIndex;

    StateRepository stateRepository;
    private RaftGateway raftGateway;

    public RaftServerImpl(long id, List<Long> peers, StateRepository stateRepository, RaftGateway raftGateway) {
        this.id = id;
        this.peers = peers;
        this.stateRepository = stateRepository;
        this.raftGateway = raftGateway;
    }

    //for test
    RaftServerImpl(long id, List<Long> peers, ServerRole role, long term, StateRepository stateRepository, RaftGateway raftGateway) {
        this.id = id;
        this.role = role;
        this.term = term;
        this.peers = peers;
        this.stateRepository = stateRepository;
        this.raftGateway = raftGateway;
    }

    public void heartbitTimeout() {

    }

    public void electionTimeout() {
        startElection();
    }

    public void startElection() {
        term = term + 1;
        role = ServerRole.Candidate;
        List<VoteResponse> votes = requestVotes();
        if(mejorityGranted(votes)) {
            this.role = ServerRole.Leader;
            notifyPeers();
        }
    }

    private void notifyPeers() {
        for (Long peer : peers) {
            raftGateway.appendEntries(peer, new AppendEntry(term, this, previousLogIndex, previousLogTerm, Collections.emptyList(), commitIndex));
        }
    }
    private List<VoteResponse> requestVotes() {
        VoteResponse selfVote = selfVote();
        List<VoteResponse> neighboursVotes = requestVoteFromPeers();
        List<VoteResponse> allVotes = new ArrayList<>(neighboursVotes);
        allVotes.add(selfVote);
        return allVotes;
    }

    private boolean mejorityGranted(List<VoteResponse> totalVotes) {
        List<VoteResponse> grantedVotes = totalVotes.stream().filter(vote -> vote.isGranted()).collect(Collectors.toList());
        return grantedVotes.size() > (totalVotes.size() / 2);
    }

    private List<VoteResponse> requestVoteFromPeers() {
        List<Long> neighbours = peers;
        List<VoteResponse> responses = new ArrayList<>();
        for (Long neighbour : neighbours) {
            VoteResponse voteResponse = requestVoteFrom(neighbour);
            responses.add(voteResponse);
        }
        return responses;
    }

    private VoteResponse selfVote() {
        //??selfVote;
        return VoteResponse.granted(this.term);
    }

    public VoteResponse requestVoteFrom(Long otherServer) {
        RequestVote voteRequest = new RequestVote(term, this.id, 0, 0);
        return raftGateway.requestVote(otherServer, voteRequest);
    }



    @Override
    public VoteResponse requestVote(RequestVote voteRequest) {
        return VoteResponse.granted(term);
    }

    @Override
    public AppendEntryResponse appendEntries(AppendEntry appendEntry) {
        if (this.role == ServerRole.Candidate && appendEntry.getTerm() > this.term) {
            this.role = ServerRole.Follower;
        }
        return new AppendEntryResponse();
    }
}
