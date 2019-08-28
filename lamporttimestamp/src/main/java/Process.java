public class Process {
    int id;
    int logicalTime;
    public Process(int id) {
        this.id = id;
        this.logicalTime = 0;
    }

    public void action() {
        this.logicalTime++;
    }

    public void sendMessage(Process other) {
        this.logicalTime++;
        other.receive(this, logicalTime);
    }

    private void receive(Process from, int timeAtSender) {
        int maxTime = Math.max(logicalTime, timeAtSender);
        this.logicalTime = maxTime + 1;
    }

    public int getLogicalTime() {
        return logicalTime;
    }
}
