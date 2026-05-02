import java.util.*;

/**
 * First-Come First-Served (FCFS) Scheduling Algorithm.
 * Non-preemptive algorithm that executes processes in the order of their arrival.
 */
public class FCFS {

    /**
     * Runs the FCFS scheduling algorithm.
     * @param list The list of processes to schedule.
     */
    public static void run(List<Process> list) {
        Utils.println("\n=== FCFS ===");

        // Sort processes by arrival time (AT) to ensure they are processed in order
        list.sort(Comparator.comparingInt(p -> p.at));

        int time = 0; // Current simulation time
        List<String> gantt = new ArrayList<>(); // To store execution sequence for Gantt chart

        for (Process p : list) {
            // Handle CPU idle time if process arrives after current time
            while (time < p.at) {
                gantt.add("IDLE");
                time++;
            }

            // Execute the process for its entire burst time (non-preemptive)
            for (int i = 0; i < p.bt; i++) {
                gantt.add(p.id);
                time++;
            }

            // Calculate metrics for the completed process
            p.ct = time;               // Completion Time
            p.tat = p.ct - p.at;       // Turnaround Time = Completion - Arrival
            p.wt = p.tat - p.bt;        // Waiting Time = Turnaround - Burst
        }

        // Display results
        Utils.printTable(list);
        Utils.printGantt(gantt);
    }
}