public class NQueen {

    // 체스보드의 크기
    static int N = 8;

    // 배치방법의 수를 세는 변수
    static int solCnt;

    // 솔루션 저장 배열
    static int[] sol = new int[N];

    // 각 열에서 퀸의 배치여부를 저장하는 배열
    static int[] columns = new int[N];

    public static void main(String[] args) {
        solCnt = 0;
        backtrack(0);
        System.out.println(solCnt);
    }

    public static void backtrack(int row) {
        if (isSolution(row)) {
            processSolution();

            return;
        }

        loop: for (int col = 0; col < N; col++) {
            // 현재 퀸이 배치된 열 가지치기
            if (columns[col] == 1) {
                continue;
            }

            // 대각선 방향에 퀸이 존재하면 가지치기
            for (int j = 0; j < row; j++) {
                if (Math.abs(row - j) == Math.abs(col - sol[j])) {
                    continue loop;
                }
            }

            // 퀸을 배치한 열 저장
            sol[row] = col;

            // 열에 퀸이 배치되었음을 표시
            columns[col] = 1;

            // 다음 행으로 재귀 호출
            backtrack(row + 1);

            // 퀸 배치표시 해제
            columns[col] = 0;
        }
    }

    public static boolean isSolution(int row) {
        return (row == N);
    }

    public static void processSolution() {
        // System.out.println(solCnt);
        solCnt++;
    }
}
