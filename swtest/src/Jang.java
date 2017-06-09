import java.util.Scanner;


public class Jang {
    static int TC;
    static int N; // 부서, 1<= N <= 100
    static int C; // 동선, 1<= N <= 100
    static int T; // Time 1<= T <= 1000
    static double[][] edje;
    static double[][] dp;
    static int no; // T 타임에 위치한 부서 번호
    static double max; // T tick에 특정 부서에 잇응ㄹ max 확률

    public static void main(String[] args) {
        // input
        System.setIn(Jang.class.getResourceAsStream("./jang.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();

        for (int tcIdx = 0; tcIdx < TC; tcIdx++) {
            N = sc.nextInt();
            C = sc.nextInt();
            T = sc.nextInt();

            // 부서 index가 1번 부터 시작
            edje = new double[N + 1][N + 1];
            int from, to;
            for (int i = 0; i < C; i++) {
                from = sc.nextInt();
                to = sc.nextInt();
                edje[from][to] = sc.nextDouble();
                // System.out.printf("i=%d, from,to=[%d,%d]=%f\n", i, from, to, edje[from][to]);
            }

            // logic

            // 이동횟수
            int maxTick = T / 10;

            dp = new double[maxTick + 1][N + 1];

            no = 1;
            max = 1.0f;

            if (maxTick > 0) {
                max = 0.0f;
                // 시작 위치
                dp[0][1] = 1.0f;
                for (int tick = 1; tick < maxTick + 1; tick++) {
                    for (int i = 1; i < N + 1; i++) {// from
                        for (int j = 1; j < N + 1; j++) {// to
                            if (0f < edje[i][j]) {
                                dp[tick][j] += dp[tick - 1][i] * edje[i][j];

                                if (tick == maxTick) {
                                    if (dp[tick][j] > max) {
                                        max = dp[tick][j];
                                        no = j;
                                    }
                                }
                            }
                        }
                    }
                }
            }

            System.out.printf("#%d %d %.8f\n", (tcIdx + 1), no, max);
        }

        sc.close();
    }
}