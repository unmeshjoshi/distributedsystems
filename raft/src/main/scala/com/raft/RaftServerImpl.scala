package com.raft

import java.util
import java.util.{ArrayList, Collections, List}
import java.util.stream.Collectors

import com.raft.raft._

class RaftServerImpl extends RaftServer {
  private var id: Long = 0L
  private var peers: util.List[Long] = null
  private[raft] var role: ServerRole = ServerRole.Follower
  private[raft] var term: Long = 0
  private[raft] val previousLogIndex: Long = 0L
  private[raft] val previousLogTerm: Long = 0L
  private[raft] val commitIndex: Long = 0L
  private[raft] var stateRepository: StateRepository = null
  private var raftGateway: RaftGateway = null

  def this(id: Long, peers: util.List[Long], stateRepository: StateRepository, raftGateway: RaftGateway) {
    this()
    this.id = id
    this.peers = peers
    this.stateRepository = stateRepository
    this.raftGateway = raftGateway
  }

  //for test
  def this(id: Long, peers: util.List[Long], role: ServerRole, term: Long, stateRepository: StateRepository, raftGateway: RaftGateway) {
    this()
    this.id = id
    this.role = role
    this.term = term
    this.peers = peers
    this.stateRepository = stateRepository
    this.raftGateway = raftGateway
  }

  def heartbitTimeout(): Unit = {
  }

  def electionTimeout(): Unit = {
    startElection()
  }

  def startElection(): Unit = {
    term = term + 1
    role = ServerRole.Candidate
    val votes: util.List[VoteResponse] = requestVotes
    if (mejorityGranted(votes)) {
      this.role = ServerRole.Leader
      notifyPeers()
    }
  }

  private def notifyPeers(): Unit = {
    import scala.collection.JavaConversions._
    for (peer <- peers) {
      raftGateway.appendEntries(peer, new AppendEntry(term, this, previousLogIndex, previousLogTerm, Collections.emptyList, commitIndex))
    }
  }

  private def requestVotes: util.List[VoteResponse] = {
    val selfVote: VoteResponse = selfVote
    val neighboursVotes: util.List[VoteResponse] = requestVoteFromPeers
    val allVotes: util.List[VoteResponse] = new util.ArrayList[VoteResponse](neighboursVotes)
    allVotes.add(selfVote)
    allVotes
  }

  private def mejorityGranted(totalVotes: util.List[VoteResponse]): Boolean = {
    val grantedVotes: util.List[VoteResponse] = totalVotes.stream.filter((vote: VoteResponse) => vote.isGranted).collect(Collectors.toList)
    grantedVotes.size > (totalVotes.size / 2)
  }

  private def requestVoteFromPeers: util.List[VoteResponse] = {
    val neighbours: util.List[Long] = peers
    val responses: util.List[VoteResponse] = new util.ArrayList[VoteResponse]
    import scala.collection.JavaConversions._
    for (neighbour <- neighbours) {
      val voteResponse: VoteResponse = requestVoteFrom(neighbour)
      responses.add(voteResponse)
    }
    responses
  }

  private def selfVote: VoteResponse = { //??selfVote;
    VoteResponse.granted(this.term)
  }

  def requestVoteFrom(otherServer: Long): VoteResponse = {
    val voteRequest: RequestVote = new RequestVote(term, this.id, 0, 0)
    raftGateway.requestVote(otherServer, voteRequest)
  }

  override def requestVote(voteRequest: RequestVote): VoteResponse = VoteResponse.granted(term)

  override def appendEntries(appendEntry: AppendEntry): AppendEntryResponse = {
    if ((this.role eq ServerRole.Candidate) && appendEntry.getTerm > this.term) this.role = ServerRole.Follower
    new AppendEntryResponse
  }
}