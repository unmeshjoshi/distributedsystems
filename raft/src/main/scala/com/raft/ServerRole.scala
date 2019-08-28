package com.raft

object ServerRole extends Enumeration {
  type ServerRole = Value
  val Candidate, Leader, Follower = Value
}
