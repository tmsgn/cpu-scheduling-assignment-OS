import java.util.*;

public class Main {

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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. Sample Data");
        System.out.println("2. User Input");
        int choice = sc.nextInt();

        List<Process> processes = (choice == 1) ? sampleProcesses() : inputProcesses(sc);

        FCFS.run(new ArrayList<>(processes));
        SJF.run(new ArrayList<>(processes));
        SRTF.run(new ArrayList<>(processes));
        RoundRobin.run(new ArrayList<>(processes), 2);

        if (choice == 1) {
            Bankers.runSample();
        } else {
            Bankers.run(sc);
        }
    }
}