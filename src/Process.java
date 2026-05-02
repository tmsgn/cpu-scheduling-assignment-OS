/**
 * Represents a process in the CPU scheduling simulation.
 * Holds information about arrival time, burst time, and calculated metrics.
 */
public class Process {
    String id;      // Unique identifier for the process (e.g., "P1")
    int at;         // Arrival Time: When the process enters the ready queue
    int bt;         // Burst Time: Total CPU time required by the process
    int ct;         // Completion Time: When the process finishes execution
    int wt;         // Waiting Time: Total time the process spent waiting in the ready queue
    int tat;        // Turnaround Time: Total time from arrival to completion (CT - AT)

    /**
     * Constructs a new Process.
     * @param id Process identifier
     * @param at Arrival time
     * @param bt Burst time
     */
    public Process(String id, int at, int bt) {
        this.id = id;
        this.at = at;
        this.bt = bt;
    }
}