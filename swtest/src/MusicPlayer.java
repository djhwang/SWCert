import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MusicPlayer {
    static int N; // # of applicant
    static int M; // # of instrument
    static boolean[] visited;
    static int AnswerN;
    static int[] salary;
    static int[][] arr;
    static int[] sol;
    static int min;
    static List<Integer> player;

    public static void main(String[] args) {
        // input
        System.setIn(MusicPlayer.class.getResourceAsStream("./MusicPlayer.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        TC = 1;

        for (int tcIdx = 0; tcIdx < TC; tcIdx++) {
            N = sc.nextInt();
            M = sc.nextInt();
            salary = new int[N];
            arr = new int[M][N];
            sol = new int[M];
            visited = new boolean[M];
            min = Integer.MAX_VALUE;
            player = new ArrayList<Integer>();

            // salary
            for (int i = 0; i < N; i++) {
                salary[i] = sc.nextInt();
            }

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }

            backtrack(0);
            System.out.printf("#%d %d\n", tcIdx + 1, min);
        }

        sc.close();
    }

    private static void backtrack(int n) {
        // 모든 연주자를 체크했거나, 악기가 모두 체크된 경우면 solution 이다.
        if (n == M) {
            int sum = 0;
            System.out.print("[Solution] ");

            for (int i = 0; i < M; i++) {
                if (sol[i] > 0) {
                    System.out.printf("%d ", sol[i]);
                }
            }

            for (int i = 0; i < player.size(); i++) {
                sum += salary[player.get(i)];
                // System.out.printf("instrument=%d player=%d\n", i, sol[i] - 1);
            }

            System.out.printf("sum=%d\n", sum);
            if (sum < min) {
                min = sum;
                // System.out.printf("min=%d\n", min);
            }

            return;
        }

        for (int i = 0; i < M; i++) {
            if (!visited[i]) {

                for (int j = 0; j < N; j++) {
                    visited[i] = true;
                    if (arr[i][j] == 1) {
                        sol[n] = j + 1;// marking
                        if (!player.contains(j)) {
                            player.add(j);
                        }

                        backtrack(n + 1);
                        player.remove(Integer.valueOf(j));
                    }
                    visited[i] = false;
                }

            }
        }
    }
}