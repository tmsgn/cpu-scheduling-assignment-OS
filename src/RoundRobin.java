<<<<<<< HEAD
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
=======
import java.util.*;

/**
 * Round Robin (RR) Scheduling Algorithm.
 * Preemptive algorithm that assigns a fixed time unit (quantum) to each process
 * in a cyclic order.
 */
public class RoundRobin {

    /**
     * Runs the Round Robin scheduling algorithm.
     * @param list The list of processes to schedule.
     * @param q The time quantum.
     */
    public static void run(List<Process> list, int q) {
        Utils.println("\n=== Round Robin (q=" + q + ") ===");

        int n = list.size();
        int[] rt = new int[n]; // Remaining Time for each process

        // Initialize remaining times
        for (int i = 0; i < n; i++)
            rt[i] = list.get(i).bt;

        int time = 0;
        Queue<Integer> queue = new LinkedList<>(); // Ready queue
        boolean[] visited = new boolean[n]; // Tracks if process has entered the queue

        List<String> gantt = new ArrayList<>(); // To store execution sequence

        // Add the first process that arrives
        queue.add(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int i = queue.poll();
            Process p = list.get(i);

            // Execute for quantum or remaining time, whichever is smaller
            int exec = Math.min(q, rt[i]);

            for (int t = 0; t < exec; t++) {
                gantt.add(p.id);
                time++;
                
                // While executing, check if any new processes have arrived
                for (int j = 0; j < n; j++) {
                    if (!visited[j] && list.get(j).at <= time) {
                        queue.add(j);
                        visited[j] = true;
                    }
                }
            }

            rt[i] -= exec;

            // If process still has remaining time, add it back to the end of the queue
            if (rt[i] > 0) {
                queue.add(i);
            } else {
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
>>>>>>> a81a79e573d81af6fbd778213ab9536fe82c63b6
