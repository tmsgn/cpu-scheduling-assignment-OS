// Simple data holder for a process used by scheduling algorithms
public class Process {
    // Process identifier (e.g., "P1")
    String id;
    // arrival time, burst time, completion time, waiting time, turnaround time
    int at, bt, ct, wt, tat;

    // Construct a process with id, arrival time and burst time
    public Process(String id, int at, int bt) {
        this.id = id;
        this.at = at;
        this.bt = bt;
    }
}