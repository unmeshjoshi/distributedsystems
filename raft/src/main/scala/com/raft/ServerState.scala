package com.raft

import java.util
import java.util.{HashMap, Map}

import com.raft.LogEntry

class ServerState {
  private[raft] val log: util.Map[Integer, LogEntry] = new util.HashMap[Integer, LogEntry]
  private[raft] val votedForCandidateId: Long = 0L
  private[raft] val currentTerm: Long = 0L
}
