import java.util.*;

public class Main {

    private static final int ROUND_ROBIN_QUANTUM = 2;

    public static List<Process> sampleProcesses() {
        return Arrays.asList(
                new Process("P1", 0, 7),
                new Process("P2", 2, 4),
                new Process("P3", 4, 1),
                new Process("P4", 5, 4)
        );
    }

    public static List<Process> inputProcesses(Scanner sc) {
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        List<Process> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            System.out.println("\nProcess " + (i + 1));
            System.out.print("Arrival Time: ");
            int at = sc.nextInt();

            System.out.print("Burst Time: ");
            int bt = sc.nextInt();

            list.add(new Process("P" + (i + 1), at, bt));
        }
        return list;
    }

    private static void printAlgorithmMenu() {
        System.out.println("\nChoose an algorithm:");
        System.out.println("1. FCFS");
        System.out.println("2. SJF");
        System.out.println("3. SRTF");
        System.out.println("4. Round Robin");
        System.out.println("5. Banker's Algorithm");
        System.out.println("6. Run All Scheduling Algorithms");
    }

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Sample Data");
        System.out.println("2. User Input");
        int dataChoice = sc.nextInt();

        List<Process> processes = (dataChoice == 1) ? sampleProcesses() : inputProcesses(sc);

        printAlgorithmMenu();
        int algorithmChoice = sc.nextInt();

        if (algorithmChoice == 5) {
            if (dataChoice == 1) {
                Bankers.runSample();
            } else {
                Bankers.run(sc);
            }
            return;
        }

        runSelectedAlgorithm(algorithmChoice, processes);
    }
}