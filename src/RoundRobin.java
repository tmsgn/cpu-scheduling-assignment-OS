import java.util.*;

// Round Robin scheduling (preemptive) with a fixed time quantum q
public class RoundRobin {

    // Runs round-robin on `list` using quantum `q`. Uses a queue of indices
    // and tracks remaining time (`rt`) for each process.
    public static void run(List<Process> list, int q) {
        Utils.println("\n=== Round Robin (q=2) ===");

        int n = list.size();
        int[] rt = new int[n];

        for (int i = 0; i < n; i++)
            rt[i] = list.get(i).bt;

        int time = 0;
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        List<String> gantt = new ArrayList<>();

        // start with first process (assumes list[0] arrives at or before time 0)
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {

            int i = queue.poll();
            Process p = list.get(i);

            int exec = Math.min(q, rt[i]);

            // execute for up to quantum or remaining time
            for (int t = 0; t < exec; t++) {
                gantt.add(p.id);
                time++;
            }

            rt[i] -= exec;

            // enqueue newly arrived processes
            for (int j = 0; j < n; j++) {
                if (!visited[j] && list.get(j).at <= time) {
                    queue.add(j);
                    visited[j] = true;
                }
            }

            // if process still has remaining time, requeue it;
            // otherwise record completion metrics
            if (rt[i] > 0) {
                queue.add(i);
            } else {
                p.ct = time;
                p.tat = p.ct - p.at;
                p.wt = p.tat - p.bt;
            }
        }

        Utils.printTable(list);
        Utils.printGantt(gantt);
    }
}