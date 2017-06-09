import java.util.Scanner;


public class AD0827 {
    static int N; // 1<= N <= 10
    static boolean[] visited;
    static int AnswerN;
    static int[][] arr;
    static int[] sol;
    static int max;

    public static void main(String[] args) {
        // input
        System.setIn(AD0827.class.getResourceAsStream("./AD0827.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        // int TC = 1;

        for (int tcIdx = 0; tcIdx < TC; tcIdx++) {
            N = sc.nextInt();
            arr = new int[N][N];
            sol = new int[N];
            visited = new boolean[N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            backtrack(0);
            System.out.printf("#%d %d\n", tcIdx + 1, max);
        }

        sc.close();
    }

    private static void backtrack(int n) {
        if (n == N) {
            int sum = 0;
            for (int i = 0; i < N; i++) {
                sum += arr[i][sol[i]];
                // System.out.printf("%d ", sol[i]);
            }
            if (sum > max) {
                max = sum;
            }
            // System.out.println();
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                sol[n] = i;
                visited[i] = true;
                backtrack(n + 1);
                visited[i] = false;
            }
        }
    }
}