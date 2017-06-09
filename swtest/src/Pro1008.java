import java.util.Scanner;


public class Pro1008 {
    static int N_MAX = 1001;
    static int N; // 1<= N <= 10
    static int K;
    static long X;
    static long[][] bino;

    public static void main(String[] args) {
        // input
        System.setIn(Pro1008.class.getResourceAsStream("./Pro1008.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        // int TC = 1;

        makeBino();

        for (int tcIdx = 0; tcIdx < TC; tcIdx++) {
            N = sc.nextInt();
            K = sc.nextInt();
            X = sc.nextLong();

            int len = N;
            int zeroLen = N - K;

            StringBuilder sb = new StringBuilder();

            for (int i = N; i > 0; i--) {
                if (zeroLen <= 0) {
                    sb.append("1");
                } else {
                    if (bino[len - 1][zeroLen - 1] < X) {
                        X -= bino[len - 1][zeroLen - 1];
                        sb.append("1");
                        len--;
                    } else {
                        sb.append("0");
                        len--;
                        zeroLen--;
                    }
                }
            }

            System.out.printf("# %d %s\n", (tcIdx + 1), sb.toString());
        }

        sc.close();
    }

    private static void makeBino() {
        bino = new long[N_MAX][N_MAX];

        for (int i = 0; i < N_MAX; i++) {
            bino[i][0] = bino[i][i] = 1;
            for (int j = 1; j < i; j++) {
                if (bino[i - 1][j - 1] == Long.MAX_VALUE
                        || bino[i - 1][j] == Long.MAX_VALUE
                        || (bino[i - 1][j] + bino[i - 1][j - 1]) > Long.MAX_VALUE) {
                    bino[i][j] = Long.MAX_VALUE;
                } else {
                    bino[i][j] = bino[i - 1][j - 1] + bino[i - 1][j];
                }
            }
        }
    }
}