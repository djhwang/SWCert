public class NQueen2 {
    static int N = 8;
    static int solCnt = 0;
    static int[] sol = new int[N];
    static int[] columns = new int[N];

    public static void main(String[] args) {
        backtrack(0);

        System.out.println(solCnt);
    }

    public static void backtrack(int row) {
        if (isSolution(row)) {
            solCnt++;
            return;
        }

        loop: for (int col = 0; col < N; col++) {
            // column에 Queen이 배치되어 있는 체크
            if (columns[col] == 1) {
                continue;
            }

            // 대각선에 Queen이 있는지 체크
            for (int j = 0; j < row; j++) {
                if (Math.abs(row - j) == Math.abs(col - sol[j])) {
                    continue loop;
                }
            }

            // column에 Queen 배치
            sol[row] = col;

            // column에 Queen 배치 여부 마킹
            columns[col] = 1;

            // 다음 row 진행
            backtrack(row + 1);

            // column 에 Queen 배치 여부 해제
            columns[col] = 0;
        }
    }

    private static boolean isSolution(int n) {
        return (n == N);
    }
}