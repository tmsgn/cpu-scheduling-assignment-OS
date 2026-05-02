import java.util.*;

public class Bankers {

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

    private static void run(int n, int m, int[][] max, int[][] alloc, int[] avail) {
        System.out.println("\n--- Banker's Algorithm ---");

        int[][] need = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - alloc[i][j];
            }
        }

        boolean[] finish = new boolean[n];
        int[] safeSeq = new int[n];

        int count = 0;

        while (count < n) {
            boolean found = false;

            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > avail[j])
                            break;
                    }

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