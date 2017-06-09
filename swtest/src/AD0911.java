public class AD0911 {
    static int N; // 1<= N <= 10
    static boolean[] visited;
    static int AnswerN;
    static int[][] arr;
    static int[] sol;
    static int max;

    public static void main(String[] args) {
        /*
         * // input System.setIn(AD0911.class.getResourceAsStream("./AD0911.txt")); Scanner sc = new Scanner(System.in);
         * 
         * int TC = sc.nextInt(); // int TC = 1;
         * 
         * for (int tcIdx = 0; tcIdx < TC; tcIdx++) { N = sc.nextInt(); arr = new int[N][N]; sol = new int[N]; visited = new boolean[N];
         * 
         * for (int i = 0; i < N; i++) { for (int j = 0; j < N; j++) { arr[i][j] = sc.nextInt(); } }
         * 
         * backtrack(0); System.out.printf("#%d %d\n", tcIdx + 1, max); }
         * 
         * sc.close();
         */

        long i = 47;

        System.out.println(bitcount(i));

    }

    private static int bitcount(long i) {
        System.out.println(i);
        i = i - ((i >>> 1) & 0x5555555555555555L);
        System.out.println(i);
        i = (i & 0x3333333333333333L) + ((i >>> 2) & 0x3333333333333333L);
        System.out.println(i);
        i = (i + (i >>> 4)) & 0x0f0f0f0f0f0f0f0fL;
        System.out.println(i);
        i = i + (i >>> 8);
        System.out.println(i);
        i = i + (i >>> 16);
        System.out.println(i);
        i = i + (i >>> 32);
        System.out.println(i);
        return (int) i & 0x7f;
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