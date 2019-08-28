public class Process {

    private VectorClock vectorClock;
    private final int id;

    public Process(int id, MembershipList membershipList) {
        this.id = id;
        membershipList.register(this);
    }

    public void initializeVectorClock(MembershipList membershipList) {
        this.vectorClock = new VectorClock(membershipList);
    }

    public void action() {
        vectorClock.incrementTimeFor(id);
    }

    public void sendMessage(Process other) {
        vectorClock.incrementTimeFor(this.id);
        other.receive(this, vectorClock);
    }

    private void receive(Process fromProcess, VectorClock otherVectorClock) {
        this.vectorClock.incrementTimeFor(this.id);
        this.vectorClock.merge(otherVectorClock);
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Process{" +
                "vectorClock=" + vectorClock +
                ", id=" + id +
                '}';
    }
}
