import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Utility class for common operations like printing tables, 
 * Gantt charts, and saving output to a file.
 */
public class Utils {

    // StringBuilder to store all output for saving to a file later
    static StringBuilder output = new StringBuilder();

    /**
     * Prints a string to the console and appends it to the output buffer.
     * @param s The string to print.
     */
    public static void print(String s) {
        System.out.print(s);
        output.append(s);
    }

    /**
     * Prints a string with a newline and appends it to the output buffer.
     * @param s The string to print.
     */
    public static void println(String s) {
        System.out.println(s);
        output.append(s).append("\n");
    }

    /**
     * Saves all captured output to "output/sample_output.txt".
     */
    public static void saveToFile() {
        try {
            // Ensure the output directory exists (handled by system in this context)
            FileWriter fw = new FileWriter("output/sample_output.txt");
            fw.write(output.toString());
            fw.close();
            System.out.println("\n[INFO] Output successfully saved to output/sample_output.txt");
        } catch (IOException e) {
            System.out.println("[ERROR] Failed to save output to file.");
        }
    }

    /**
     * Prints the process performance metrics in a formatted table.
     * Calculates and displays average Waiting Time (WT) and Turnaround Time (TAT).
     * @param list The list of processed processes.
     */
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

    /**
     * Prints a visual Gantt chart representation of the CPU execution.
     * @param gantt A list where each element represents the process executing at that time unit.
     */
    public static void printGantt(List<String> gantt) {
        println("\nGantt Chart:");
        
        // Print the top border
        for (int i = 0; i < gantt.size(); i++) print("-----");
        print("-");
        println("");

        // Print the process IDs
        print("|");
        for (String g : gantt) {
            print(String.format(" %-3s|", g));
        }
        println("");

        // Print the bottom border
        for (int i = 0; i < gantt.size(); i++) print("-----");
        print("-");
        println("");

        // Print the time markers
        for (int i = 0; i <= gantt.size(); i++) {
            print(String.format("%-5d", i));
        }
        println("\n");
    }
}