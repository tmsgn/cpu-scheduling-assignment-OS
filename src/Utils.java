import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

// Utility helpers used across scheduling demos: printing, formatting,
// accumulating output, and saving results to a file.
public class Utils {

    // Accumulate program output so it can be optionally saved to file
    static StringBuilder output = new StringBuilder();

    public static void print(String s) {
        System.out.print(s);
        output.append(s);
    }

    public static void println(String s) {
        System.out.println(s);
        output.append(s).append("\n");
    }

    // Save accumulated output to `output/sample_output.txt`.
    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter("output/sample_output.txt");
            fw.write(output.toString());
            fw.close();
            System.out.println("\nOutput saved to output/sample_output.txt");
        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    // Pretty-print the process table with derived metrics
    public static void printTable(List<Process> list) {
        double totalWT = 0, totalTAT = 0;

        println("\nProcess  AT  BT  CT  TAT  WT");

        for (Process p : list) {
            println(String.format("%-7s %-3d %-3d %-3d %-4d %-3d",
                    p.id, p.at, p.bt, p.ct, p.tat, p.wt));

            totalWT += p.wt;
            totalTAT += p.tat;
        }

        println("\nAverage WT = " + (totalWT / list.size()));
        println("Average TAT = " + (totalTAT / list.size()));
    }

    // Render a simple Gantt chart (one label per time unit)
    public static void printGantt(List<String> gantt) {
        print("\nGantt Chart:\n|");
        for (String g : gantt) {
            print(" " + g + " |");
        }
        println("");
    }
}