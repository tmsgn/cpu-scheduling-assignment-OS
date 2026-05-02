import java.util.*;

// First-Come First-Serve (non-preemptive) scheduling implementation
public class FCFS {

    // Runs FCFS on a list of processes. The list is sorted by arrival time,
    // then each process executes to completion in arrival order.
    public static void run(List<Process> list) {
        Utils.println("\n=== FCFS ===");

        // sort by arrival time
        list.sort(Comparator.comparingInt(p -> p.at));

        int time = 0;
        List<String> gantt = new ArrayList<>();

        for (Process p : list) {

            // if CPU is idle until process arrival, mark IDLE slots
            while (time < p.at) {
                gantt.add("IDLE");
                time++;
            }

            // execute process for its full burst time
            for (int i = 0; i < p.bt; i++) {
                gantt.add(p.id);
                time++;
            }

            // compute completion and derived metrics
            p.ct = time;
            p.tat = p.ct - p.at;
            p.wt = p.tat - p.bt;
        }

        Utils.printTable(list);
        Utils.printGantt(gantt);
    }
}