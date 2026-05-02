import java.util.*;

/**
 * Banker's Algorithm for Deadlock Avoidance.
 * Ensures the system stays in a safe state by checking if resource requests 
 * can be fulfilled without leading to a deadlock.
 */
public class Bankers {

    /**
     * Runs the algorithm using predefined sample data.
     */
    public static void runSample() {
        // Sample Maximum resources needed by each process
        int[][] max = {
                {7, 5, 3},
                {3, 2, 2},
                {7, 0, 2}
        };

        // Sample resources currently allocated to each process
        int[][] alloc = {
                {0, 1, 0},
                {2, 0, 0},
                {3, 0, 2}
        };

        // Sample available resources in the system
        int[] avail = {3, 4, 2};

        run(max.length, max[0].length, max, alloc, avail);
    }

    /**
     * Runs the algorithm using user input from the console.
     * @param sc Scanner object for input.
     */
    public static void run(Scanner sc) {
        System.out.println("\n--- Banker's Algorithm ---");

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of resource types: ");
        int m = sc.nextInt();

        int[][] max = new int[n][m];
        int[][] alloc = new int[n][m];
        int[] avail = new int[m];

        System.out.println("Enter MAX matrix (Need + Allocation):");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter ALLOCATION matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                alloc[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter AVAILABLE resources:");
        for (int i = 0; i < m; i++) {
            avail[i] = sc.nextInt();
        }

        run(n, m, max, alloc, avail);
    }

    /**
     * Core implementation of Banker's Algorithm.
     * @param n Number of processes.
     * @param m Number of resource types.
     * @param max Max matrix.
     * @param alloc Allocation matrix.
     * @param avail Available vector.
     */
    private static void run(int n, int m, int[][] max, int[][] alloc, int[] avail) {
        System.out.println("\n--- Banker's Algorithm Execution ---");

        int[][] need = new int[n][m]; // Matrix to store remaining resource needs

        // Calculate NEED matrix: NEED = MAX - ALLOCATION
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        boolean[] finish = new boolean[n]; // Tracks if a process can be completed
        int[] safeSeq = new int[n]; // To store the safe sequence of execution
        int count = 0;

        // Try to find a safe sequence
        while (count < n) {
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    // Check if all needs of process 'i' can be met by currently available resources
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > avail[j])
                            break;
                    }

                    // If all needs are met
                    if (j == m) {
                        // Simulate resource release: add allocated resources back to available
                        for (int k = 0; k < m; k++) {
                            avail[k] += alloc[i][k];
                        }

                        safeSeq[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            // If no process could be completed in this round, the system is in an unsafe state
            if (!found) {
                System.out.println("Result: System is NOT in a safe state (Potential Deadlock)");
                return;
            }
        }

        // Output the safe sequence
        System.out.println("Result: System is in a SAFE state.");
        System.out.print("Safe sequence: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (safeSeq[i] + 1) + (i == n - 1 ? "" : " -> "));
        }
        System.out.println();
    }
}
