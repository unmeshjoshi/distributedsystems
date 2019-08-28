package com.raft

import com.raft.raft.{AppendEntry, AppendEntryResponse, RequestVote, VoteResponse}

trait RaftGateway {
  def requestVote(serverId: Long, voteRequest: RequestVote): VoteResponse

  def appendEntries(serverId: Long, appendEntry: AppendEntry): AppendEntryResponse
}

