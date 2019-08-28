package com.raft

import com.raft.raft.{AppendEntry, AppendEntryResponse, RequestVote, VoteResponse}

trait RaftServer {
  def requestVote(voteRequest: RequestVote): VoteResponse

  def appendEntries(appendEntry: AppendEntry): AppendEntryResponse
}

