import java.util.*;

/**
 * Shortest Remaining Time First (SRTF) Scheduling Algorithm.
 * Preemptive version of SJF. The process with the smallest remaining burst time
 * is selected for execution.
 */
public class SRTF {

    /**
     * Runs the SRTF scheduling algorithm.
     * @param list The list of processes to schedule.
     */
    public static void run(List<Process> list) {
        Utils.println("\n=== SRTF ===");

        int n = list.size();
        int[] rt = new int[n]; // Remaining Time for each process

        // Initialize remaining times with original burst times
        for (int i = 0; i < n; i++)
            rt[i] = list.get(i).bt;

        int time = 0, complete = 0;
        List<String> gantt = new ArrayList<>(); // To store execution sequence

        while (complete < n) {
            int idx = -1;
            int min = Integer.MAX_VALUE;

            // Find process with minimum remaining time among arrived processes
            for (int i = 0; i < n; i++) {
                if (list.get(i).at <= time && rt[i] > 0 && rt[i] < min) {
                    min = rt[i];
                    idx = i;
                }
            }

            // CPU Idle if no process is ready
            if (idx == -1) {
                gantt.add("IDLE");
                time++;
                continue;
            }

            // Execute the selected process for 1 time unit (preemptive)
            gantt.add(list.get(idx).id);
            rt[idx]--;
            time++;

            // Check if process is finished
            if (rt[idx] == 0) {
                complete++;
                Process p = list.get(idx);

                // Calculate metrics upon completion
                p.ct = time;
                p.tat = p.ct - p.at;
                p.wt = p.tat - p.bt;
            }
        }

        // Display results
        Utils.printTable(list);
        Utils.printGantt(gantt);
    }
}