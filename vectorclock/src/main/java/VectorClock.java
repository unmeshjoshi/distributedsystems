import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VectorClock {
    Map<Integer, Integer> timeValues = new HashMap<Integer, Integer>();

    public VectorClock(MembershipList membershipList) {
        List<Process> processes = membershipList.getProcesses();
        for (Process process : processes) {
            timeValues.put(process.getId(), 0);
        }
    }

    public void incrementTimeFor(int processId) {
        Integer time = timeValues.get(processId);
        if (time == null) {
            time = 0;
        }
        time = time + 1;
        timeValues.put(processId, time);
    }

    public void merge(VectorClock other) {
        for (Integer processId : timeValues.keySet()) {
            Integer time = timeValues.get(processId);
            Integer timeAtOther = other.timeValues.get(processId);
            if (timeAtOther > time) {
                this.timeValues.put(processId, timeAtOther);
            }

        }
    }

    @Override
    public String toString() {
        return "VectorClock{" +
                "timeValues=" + timeValues +
                '}';
    }
}
