<<<<<<< HEAD
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
=======
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
>>>>>>> a81a79e573d81af6fbd778213ab9536fe82c63b6
