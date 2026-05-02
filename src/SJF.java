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