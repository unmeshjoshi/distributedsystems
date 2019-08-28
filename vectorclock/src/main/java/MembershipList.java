import java.util.ArrayList;
import java.util.List;

public class MembershipList {
    List<Process> processes = new ArrayList<Process>();

    public void register(Process process) {
        processes.add(process);
    }

    public List<Process> getProcesses() {
        return processes;
    }

    public void initializeVectorClockForAll() {
        for (Process process : processes) {
            process.initializeVectorClock(this);
        }
    }
}
