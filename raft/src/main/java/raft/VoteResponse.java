package raft;

public class VoteResponse {
    long currentTerm;
    boolean voteGranted;

    public VoteResponse(long currentTerm, boolean voteGranted) {
        this.currentTerm = currentTerm;
        this.voteGranted = voteGranted;
    }

    public static VoteResponse granted(long currentTerm) {
        return new VoteResponse(currentTerm, true);
    }

    public static VoteResponse notGranted(long currentTerm) {
        return new VoteResponse(currentTerm, false);
    }

    public boolean isGranted() {
        return voteGranted;
    }

    public long getCurrentTerm() {
        return currentTerm;
    }
}
