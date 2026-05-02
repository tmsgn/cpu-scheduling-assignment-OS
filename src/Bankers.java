<<<<<<< HEAD
import java.util.*;

// Banker's Algorithm implementation utilities
// Provides interactive and sample-run entry points to check system safety
public class Bankers {

    // Sample data runner for quick demonstration/testing
    public static void runSample() {
        int[][] max = {
                {7, 5, 3},
                {3, 2, 2},
            {7, 0, 2}
        };

        int[][] alloc = {
                {0, 1, 0},
                {2, 0, 0},
            {3, 0, 2}
        };

        int[] avail = {3, 4, 2};

        run(max.length, max[0].length, max, alloc, avail);
    }

    // Interactive runner: reads matrices from provided Scanner
    public static void run(Scanner sc) {
        System.out.println("\n--- Banker's Algorithm ---");

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        System.out.print("Enter number of resource types: ");
        int m = sc.nextInt();

        int[][] max = new int[n][m];
        int[][] alloc = new int[n][m];
        int[][] need = new int[n][m];
        int[] avail = new int[m];

        System.out.println("Enter MAX matrix:");
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

    // Core algorithm: computes if the system is in a safe state and prints
    // a safe sequence if one exists. `max` is the maximum demand matrix,
    // `alloc` is the currently allocated resources, and `avail` is the
    // currently available resources vector.
    private static void run(int n, int m, int[][] max, int[][] alloc, int[] avail) {
        System.out.println("\n--- Banker's Algorithm ---");

        int[][] need = new int[n][m];

        // need = max - alloc
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        boolean[] finish = new boolean[n];
        int[] safeSeq = new int[n];

        int count = 0;

        // Try to find a process whose needs can be satisfied
        while (count < n) {
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > avail[j])
                            break;
                    }

                    // if all needs <= avail, mark finished and release resources
                    if (j == m) {
                        for (int k = 0; k < m; k++) {
                            avail[k] += alloc[i][k];
                        }

                        safeSeq[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("System is NOT safe");
                return;
            }
        }

        System.out.print("Safe sequence: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + (safeSeq[i] + 1) + " ");
        }
        System.out.println();
    }
}
=======
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
>>>>>>> a81a79e573d81af6fbd778213ab9536fe82c63b6
