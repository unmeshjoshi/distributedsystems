package raft;

interface StateRepository {
    ServerState get();
    ServerState update(ServerState state);
}
