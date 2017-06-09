import java.util.Scanner;


/*
 * 장그레 배회기
 *    
 */
class Solution5 {
    static int n; // 부서 수
    static int c; // 동선 수
    static int t; // 시간

    static double[][] edge;
    static double[][] dp;
    static int[] degree;

    public static void main(String args[]) throws Exception {
        /*
         * 아래의 메소드 호출은 앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다. 여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후, 이 코드를 프로그램의 처음 부분에 추가하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다. 따라서
         * 테스트를 수행할 때에는 아래 주석을 지우고 이 메소드를 사용하셔도 좋습니다. 단, 채점을 위해 코드를 제출하실 때에는 반드시 이 메소드를 지우거나 주석 처리 하셔야 합니다.
         */
        System.setIn(Brick.class.getResourceAsStream("./sample_input5.txt"));

        /*
         * 표준입력 System.in 으로부터 스캐너를 만들어 데이터를 읽어옵니다.
         */
        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for (int test_case = 0; test_case < tc; test_case++) {

            n = sc.nextInt();
            c = sc.nextInt();
            t = sc.nextInt();

            edge = new double[n][n];

            for (int i = 0; i < c; i++) {
                edge[sc.nextInt() - 1][sc.nextInt() - 1] = sc.nextDouble();
            }

            // 이동 횟수 계산
            int d = t / 10;
            dp = new double[d + 1][n + 1];

            // 1번 부서 확율 세팅
            dp[0][1] = 1.0;

            for (int turn = 1; turn < d + 1; turn++) {
                for (int i = 1; i < n + 1; i++) {
                    // if (cache[turn - 1][i] != 0.0) {
                    for (int j = 1; j < n + 1; j++) {
                        if (edge[i - 1][j - 1] > 0) {
                            // 이동할 부서의 확율 += 이전부서의 전날 확율확율*이동확율
                            dp[turn][j] += dp[turn - 1][i] * edge[i - 1][j - 1];
                        }
                    }
                    // }
                }
            }

            int no = 0;
            double tmp = 0;

            for (int i = 0; i < n; i++) {
                if (dp[d][i] > tmp) {
                    tmp = dp[d][i];
                    no = i;
                }
            }
            System.out.printf("#%d %d %.8f\n", test_case + 1, no, tmp);
        }

        sc.close();
    }
}
