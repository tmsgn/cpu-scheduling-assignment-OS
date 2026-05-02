<<<<<<< HEAD
import java.util.*;

// Shortest Job First (non-preemptive)
public class SJF {

    // Selects the shortest available job at each decision point and runs it
    // to completion (non-preemptive). Builds a Gantt chart for visualization.
    public static void run(List<Process> list) {
        Utils.println("\n=== SJF ===");

        int n = list.size();
        boolean[] done = new boolean[n];
        int time = 0, completed = 0;

        List<String> gantt = new ArrayList<>();

        while (completed < n) {

            int idx = -1, min = Integer.MAX_VALUE;

            // find the not-done process with smallest burst that has arrived
            for (int i = 0; i < n; i++) {
                if (!done[i] && list.get(i).at <= time && list.get(i).bt < min) {
                    min = list.get(i).bt;
                    idx = i;
                }
            }

            // if none available yet, advance time and mark IDLE
            if (idx == -1) {
                gantt.add("IDLE");
                time++;
                continue;
            }

            Process p = list.get(idx);

            // execute chosen process fully
            for (int i = 0; i < p.bt; i++) {
                gantt.add(p.id);
                time++;
            }

            // compute metrics
            p.ct = time;
            p.tat = p.ct - p.at;
            p.wt = p.tat - p.bt;

            done[idx] = true;
            completed++;
        }

        Utils.printTable(list);
        Utils.printGantt(gantt);
    }
}
=======
import java.util.*;

/**
 * Shortest Job First (SJF) Scheduling Algorithm.
 * Non-preemptive algorithm that selects the process with the smallest burst time 
 * among the processes available in the ready queue.
 */
public class SJF {

    /**
     * Runs the SJF scheduling algorithm.
     * @param list The list of processes to schedule.
     */
    public static void run(List<Process> list) {
        Utils.println("\n=== SJF ===");

        int n = list.size();
        boolean[] done = new boolean[n]; // Tracks completed processes
        int time = 0, completed = 0;

        List<String> gantt = new ArrayList<>(); // To store execution sequence

        while (completed < n) {
            int idx = -1;
            int min = Integer.MAX_VALUE;

            // Find the process with the shortest burst time among arrived processes
            for (int i = 0; i < n; i++) {
                if (!done[i] && list.get(i).at <= time && list.get(i).bt < min) {
                    min = list.get(i).bt;
                    idx = i;
                }
            }

            // If no process has arrived, the CPU is idle
            if (idx == -1) {
                gantt.add("IDLE");
                time++;
                continue;
            }

            Process p = list.get(idx);

            // Execute the process (non-preemptive: runs until completion)
            for (int i = 0; i < p.bt; i++) {
                gantt.add(p.id);
                time++;
            }

            // Calculate metrics
            p.ct = time;
            p.tat = p.ct - p.at;
            p.wt = p.tat - p.bt;

            done[idx] = true;
            completed++;
        }

        // Display results
        Utils.printTable(list);
        Utils.printGantt(gantt);
    }
}
>>>>>>> a81a79e573d81af6fbd778213ab9536fe82c63b6
