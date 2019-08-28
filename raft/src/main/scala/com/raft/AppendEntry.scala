package com.raft

class
package raft

import java.util
import java.util.List

import raft.{LogEntry, RaftServer}


class AppendEntry(var term: Long, var leader: RaftServer, //possibly club together as logentry
                  var previousLogIndex: Long, var previousLogTerm: Long, var entries: util.List[LogEntry], var leaderCommitIndex: Long) {
  def getTerm: Long = term

  def getLeader: RaftServer = leader

  def getPreviousLogIndex: Long = previousLogIndex

  def getPreviousLogTerm: Long = previousLogTerm

  def getEntries: util.List[LogEntry] = entries

  def getLeaderCommitIndex: Long = leaderCommitIndex
}

