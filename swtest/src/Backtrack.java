
public class Backtrack {
    static int N = 4;
    static int[] sol = new int[N];

    // 현재 사용중인 숫자를 표시하는 벡터
    static int[] perm = new int[N];

    public static void main(String[] args) {
        backtrack(0);
    }

    public static void backtrack(int n) {
        if (isSolution(n)) {
            processSolution();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (perm[i] == 0) {

                // 솔루션 저장
                sol[n] = i + 1;

                // 사용 중 임을 표시
                perm[i] = 1;

                // 재귀호출
                backtrack(n + 1);

                // 사중 중 표시 해제
                perm[i] = 0;
            }
        }

    }

    public static boolean isSolution(int n) {
        return (n == N);
    }

    public static void processSolution() {
        for (int i = 0; i < N; i++) {
            System.out.printf("%d |", sol[i]);
        }
        System.out.println();
    }
}
