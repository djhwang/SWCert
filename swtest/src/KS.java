
public class KS {
    // 물건의 갯수
    static int N = 5;

    // 배낭의 용량
    static int W = 15;

    // 물건의 가치 배열
    static int[] ci = { 2, 1, 10, 2, 4 };

    // 물건의 무게 배열
    static int[] wi = { 2, 1, 4, 1, 12 };

    // 메모제이션을 위한 배열
    static int[][] M = new int[N + 1][W + 1];

    // 선택한 물건을 위한 배열
    static int[][] S = new int[N + 1][W + 1];

    public static void main(String[] args) {
        // 초기화.. 왜 +1 를 해줬을 까?
        for (int i = 0; i < N + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                M[i][j] = -1;
            }
        }

        // 최대 가치 합 출력
        System.out.println(ks(N, W));
        printSolution(N, W);
    }

    private static int ks(int i, int w) {
        // 담을 물건이 없으므로 0을 리턴
        if (i == 0) {
            return 0;
        }

        // 배열에 이전에 계산한 값이 존재
        if (M[i][w] != -1) {
            return M[i][w];
        }

        // 부분문제의 관계로 부터 갑을 계산
        // i번째를 담지 않는 경우
        M[i][w] = ks(i - 1, w);

        // i번째는 담는 경우
        if (w - wi[i - 1] >= 0) {
            M[i][w] = Math.max(M[i][w], ks(i - 1, w - wi[i - 1]) + ci[i - 1]);

            // 배낭에 담은 1번째 물건을 표시
            if (ks(i - 1, w) < M[i][w]) {
                S[i][w] = 1;
            }
        }

        return M[i][w];
    }

    public static void printSolution(int i, int w) {
        if (i == 0) {
            return;
        }

        if (S[i][w] == 1) {
            printSolution(i - 1, w - wi[i - 1]);
            System.out.println(i + " ");
        } else {
            printSolution(i - 1, w);

        }
    }
}
