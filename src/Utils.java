import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Utility class for common operations like printing tables,
 * rendering Gantt charts, and saving program output to a file.
 */
public class Utils {

    // Buffer that accumulates program output for optional saving
    static StringBuilder output = new StringBuilder();

    public static void print(String s) {
        System.out.print(s);
        output.append(s);
    }

    public static void println(String s) {
        System.out.println(s);
        output.append(s).append("\n");
    }

    public static void saveToFile() {
        try {
            FileWriter fw = new FileWriter("output/sample_output.txt");
            fw.write(output.toString());
            fw.close();
            System.out.println("\n[INFO] Output saved to output/sample_output.txt");
        } catch (IOException e) {
            System.out.println("[ERROR] Could not save output to file.");
        }
    }

    public static void printTable(List<Process> list) {
        double totalWT = 0, totalTAT = 0;

        println("\n+---------+----+----+----+-----+----+");
        println("| Process | AT | BT | CT | TAT | WT |");
        println("+---------+----+----+----+-----+----+");

        for (Process p : list) {
            println(String.format("| %-7s | %-2d | %-2d | %-2d | %-3d | %-2d |",
                    p.id, p.at, p.bt, p.ct, p.tat, p.wt));

            totalWT += p.wt;
            totalTAT += p.tat;
        }

        println("+---------+----+----+----+-----+----+");

        println(String.format("\nAverage Waiting Time (WT)    : %.2f", (totalWT / list.size())));
        println(String.format("Average Turnaround Time (TAT): %.2f", (totalTAT / list.size())));
    }

    public static void printGantt(List<String> gantt) {
        println("\nGantt Chart:");

        // Top border
        for (int i = 0; i < gantt.size(); i++) print("-----");
        print("-");
        println("");

        // Process IDs row
        print("|");
        for (String g : gantt) {
            print(String.format(" %-3s|", g));
        }
        println("");

        // Bottom border
        for (int i = 0; i < gantt.size(); i++) print("-----");
        print("-");
        println("");

        // Time markers
        for (int i = 0; i <= gantt.size(); i++) {
            print(String.format("%-5d", i));
        }
        println("\n");
    }
}
