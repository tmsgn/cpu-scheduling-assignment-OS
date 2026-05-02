import java.util.*;

/**
 * Main entry point for the CPU Scheduling and Banker's Algorithm simulation.
 * Group 7 - OS Assignment
 */
public class Main {

    private static final int ROUND_ROBIN_QUANTUM = 2;

    /**
     * Provides sample process data for testing.
     * @return A list of hardcoded Process objects.
     */
    public static List<Process> sampleProcesses() {
        return Arrays.asList(
                new Process("P1", 0, 7),
                new Process("P2", 2, 4),
                new Process("P3", 4, 1),
                new Process("P4", 5, 4)
        );
    }

    /**
     * Collects process information from the user via the console.
     * @param sc Scanner object for input.
     * @return A list of user-defined Process objects.
     */
    public static List<Process> inputProcesses(Scanner sc) {
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        List<Process> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("\n--- Process " + (i + 1) + " ---");
            System.out.print("Arrival Time: ");
            int at = sc.nextInt();

            System.out.print("Burst Time: ");
            int bt = sc.nextInt();

            list.add(new Process("P" + (i + 1), at, bt));
        }
        return list;
    }

    /**
     * Displays the menu for selecting scheduling algorithms.
     */
    private static void printAlgorithmMenu() {
        System.out.println("\n============================");
        System.out.println("  CHOOSE AN ALGORITHM");
        System.out.println("============================");
        System.out.println("1. FCFS (First-Come First-Served)");
        System.out.println("2. SJF (Shortest Job First - Non-preemptive)");
        System.out.println("3. SRTF (Shortest Remaining Time First - Preemptive)");
        System.out.println("4. Round Robin (Preemptive)");
        System.out.println("5. Banker's Algorithm (Deadlock Avoidance)");
        System.out.println("6. Run All Scheduling Algorithms");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    /**
     * Executes the chosen scheduling algorithm.
     * @param choice The menu choice.
     * @param processes The list of processes to schedule.
     */
    private static void runSelectedAlgorithm(int choice, List<Process> processes) {
        switch (choice) {
            case 1:
                FCFS.run(new ArrayList<>(processes));
                break;
            case 2:
                SJF.run(new ArrayList<>(processes));
                break;
            case 3:
                SRTF.run(new ArrayList<>(processes));
                break;
            case 4:
                RoundRobin.run(new ArrayList<>(processes), ROUND_ROBIN_QUANTUM);
                break;
            case 6:
                FCFS.run(new ArrayList<>(processes));
                SJF.run(new ArrayList<>(processes));
                SRTF.run(new ArrayList<>(processes));
                RoundRobin.run(new ArrayList<>(processes), ROUND_ROBIN_QUANTUM);
                break;
            default:
                System.out.println("Invalid algorithm choice.");
        }
    }

    /**
     * Main method.
     * @param args Command line arguments. args[0] can be a path to an input file.
     */
    public static void main(String[] args) {
        Scanner sc;
        boolean fromFile = false;

        // Check if a file path was provided as an argument
        if (args.length > 0) {
            try {
                sc = new Scanner(new java.io.File(args[0]));
                fromFile = true;
                System.out.println("[INFO] Reading input from file: " + args[0]);
            } catch (java.io.FileNotFoundException e) {
                System.out.println("[ERROR] File not found: " + args[0] + ". Switching to manual input.");
                sc = new Scanner(System.in);
            }
        } else {
            sc = new Scanner(System.in);
        }

        System.out.println("==========================================");
        System.out.println("   CPU SCHEDULING & BANKER'S SIMULATOR");
        System.out.println("==========================================");
        
        int dataChoice;
        if (fromFile) {
            dataChoice = sc.nextInt(); // Read choice from file
            System.out.println("Data source choice (from file): " + dataChoice);
        } else {
            System.out.println("1. Use Sample Data");
            System.out.println("2. Enter User Data");
            System.out.print("Choice: ");
            dataChoice = sc.nextInt();
        }

        List<Process> processes = (dataChoice == 1) ? sampleProcesses() : inputProcesses(sc);

        int algorithmChoice;
        if (fromFile) {
            algorithmChoice = sc.nextInt(); // Read choice from file
            System.out.println("Algorithm choice (from file): " + algorithmChoice);
        } else {
            printAlgorithmMenu();
            algorithmChoice = sc.nextInt();
        }

        if (algorithmChoice == 0) {
            System.out.println("Exiting...");
            return;
        }

        if (algorithmChoice == 5) {
            if (dataChoice == 1) {
                Bankers.runSample();
            } else {
                Bankers.run(sc);
            }
        } else {
            runSelectedAlgorithm(algorithmChoice, processes);
        }

        // Save all output to file before ending
        Utils.saveToFile();
        
        System.out.println("\nSimulation completed. Thank you!");
        sc.close();
    }
}
