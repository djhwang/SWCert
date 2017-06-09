public class Permutation {
    static int N = 4;
    static int[] sol = new int[N];
    static int[] perm = new int[N];

    public static void main(String[] args) {
        backtrack(0);
    }

    private static void backtrack(int n) {
        // 솔루션이라면
        if (isSolution(n)) {
            processSolution();
            return;
        }

        for (int i = 0; i < N; i++) {
            // 사용된 숫자가 아니라면
            if (perm[i] == 0) {

                // 솔루션 저장
                sol[n] = i + 1;

                // 사용중임을 표시
                perm[i] = 1;

                // 다음 숫자를 구하기 위하여 재귀호출
                backtrack(n + 1);

                // 사용중임을 해제
                perm[i] = 0;
            }
        }
    }

    private static boolean isSolution(int n) {
        return (n == N);
    }

    private static void processSolution() {
        for (int i = 0; i < N; i++) {
            System.out.printf("%d ", sol[i]);
        }
        System.out.println();
    }
}