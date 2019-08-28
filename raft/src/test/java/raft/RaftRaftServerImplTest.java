package raft;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class RaftRaftServerImplTest {
    @Test
    public void shouldInitializeTerm() throws Exception {
        RaftServerImpl server = new RaftServerImpl(0, Collections.emptyList(), new StubStateRepository(), new StubRaftGateway());
        assertEquals(server.term, 0);
    }

    @Test
    public void shouldStartInFollowerMode() {
        RaftServerImpl server = new RaftServerImpl(0, Collections.emptyList(), new StubStateRepository(), new StubRaftGateway());
        assertEquals(server.role, ServerRole.Follower);
    }

    @Test
    public void shouldElectItselfIfSingleServer() {
        RaftServerImpl server = new RaftServerImpl(0, Collections.emptyList(), new StubStateRepository(), new StubRaftGateway());
        server.startElection();
        assertEquals(server.role, ServerRole.Leader);
        assertEquals(1, server.term);
    }

    @Test
    public void shouldBecomeLeaderIfGetsMajority() {
        RaftGateway server2 = new StubRaftGateway(VoteResponse.granted(1));
        RaftServerImpl server1 = new RaftServerImpl(0, Arrays.asList(new Long(2)), new StubStateRepository(), server2);

        server1.startElection();

        assertEquals(server1.role, ServerRole.Leader);
    }

    @Test
    public void shouldSendAppendEntryToNeighboursOnElection() {
        StubRaftGateway raftGateway = new StubRaftGateway(new VoteResponse(0, true));

        RaftServerImpl server1 = new RaftServerImpl(0, Arrays.asList(2L), new StubStateRepository(), raftGateway);

        server1.startElection();

        AppendEntry appendEntryRequestFromLeader = raftGateway.appendEntryMap.get(2L);
        assertEquals(server1, appendEntryRequestFromLeader.getLeader());
        assertEquals(server1.term, appendEntryRequestFromLeader.getTerm());
        assertEquals(Collections.emptyList(), appendEntryRequestFromLeader.getEntries());
    }

    @Test
    public void candidateShouldConvertToFollowerIfAppendEntriesReceivedFromLeader() {
        RaftServerImpl leader = new RaftServerImpl(0, Arrays.asList(2L), new StubStateRepository(), new StubRaftGateway());
        RaftServerImpl candidate = new RaftServerImpl(1, Arrays.asList(2L), new StubStateRepository(), new StubRaftGateway());
        AppendEntry appendEntry = new AppendEntry(2, leader, 0, 0, Collections.emptyList(), 0);
        candidate.appendEntries(appendEntry);
        assertEquals(candidate.role, ServerRole.Follower);
    }

}