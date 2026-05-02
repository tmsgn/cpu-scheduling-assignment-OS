import java.util.*;

public class SRTF {

    public static void run(List<Process> list) {
        Utils.println("\n=== SRTF ===");

        int n = list.size();
        int[] rt = new int[n];

        for (int i = 0; i < n; i++)
            rt[i] = list.get(i).bt;

        int time = 0, complete = 0;
        List<String> gantt = new ArrayList<>();

        while (complete < n) {

            int idx = -1, min = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (list.get(i).at <= time && rt[i] > 0 && rt[i] < min) {
                    min = rt[i];
                    idx = i;
                }
            }

            if (idx == -1) {
                gantt.add("IDLE");
                time++;
                continue;
            }

            gantt.add(list.get(idx).id);
            rt[idx]--;
            time++;

            if (rt[idx] == 0) {
                complete++;
                Process p = list.get(idx);

                p.ct = time;
                p.tat = p.ct - p.at;
                p.wt = p.tat - p.bt;
            }
        }

        Utils.printTable(list);
        Utils.printGantt(gantt);
    }
}