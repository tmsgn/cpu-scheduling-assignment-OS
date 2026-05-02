import java.util.*;

public class FCFS {

    public static void run(List<Process> list) {
        Utils.println("\n=== FCFS ===");

        list.sort(Comparator.comparingInt(p -> p.at));

        int time = 0;
        List<String> gantt = new ArrayList<>();

        for (Process p : list) {

            while (time < p.at) {
                gantt.add("IDLE");
                time++;
            }

            for (int i = 0; i < p.bt; i++) {
                gantt.add(p.id);
                time++;
            }

            p.ct = time;
            p.tat = p.ct - p.at;
            p.wt = p.tat - p.bt;
        }

        Utils.printTable(list);
        Utils.printGantt(gantt);
    }
}