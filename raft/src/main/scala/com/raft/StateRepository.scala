package com.raft

import com.raft.raft.ServerState

trait StateRepository {
  def get: ServerState

  def update(state: ServerState): ServerState
}
