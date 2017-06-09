import java.io.FileInputStream;
import java.util.Scanner;


public class Dunibal {
    static int c; // 테스트케이스
    static int n; // 마을 수
    static int d; // 지난 일수
    static int p; // 교도소가 있는 마을 번호
    static int t; // 확율을 계산할 마을 수
    static int q; // 확률을 계산할 마을 번호
    static int[][] edge;
    static double[][] dp;
    static int[] degree;

    public static void main(String args[]) throws Exception {
        System.setIn(new FileInputStream("sample_input1.txt"));

        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();

        for (int test_case = 0; test_case < c; test_case++) {
            n = sc.nextInt();
            d = sc.nextInt();
            p = sc.nextInt();

            edge = new int[n][n];
            dp = new double[d + 1][n];
            degree = new int[n];

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    edge[i][j] = sc.nextInt();
                    degree[i] += edge[i][j];
                }
            }

            dp[0][p] = 1.0;
            for (int day = 1; day < d + 1; day++) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {
                        if (edge[i][j] == 0) {
                            continue;
                        }

                        dp[day][i] += (dp[day - 1][j] / degree[j]);
                    }
                }
            }

            t = sc.nextInt();
            for (int i = 0; i < t; i++) {
                q = sc.nextInt();
                System.out.printf("%.8f ", dp[d][q]);
            }
            System.out.println();
        }

        sc.close();
    }
}