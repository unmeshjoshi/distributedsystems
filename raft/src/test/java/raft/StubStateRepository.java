package raft;

import com.actoreai.ServerState;
import com.actoreai.StateRepository;

public class StubStateRepository implements StateRepository {
    ServerState serverState = new ServerState();

    public StubStateRepository() {
        this.serverState = serverState;
    }

    @Override
    public ServerState get() {
        return serverState;
    }

    @Override
    public ServerState update(ServerState state) {
        return serverState;
    }
}
