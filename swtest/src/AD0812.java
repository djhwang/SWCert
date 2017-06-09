import java.util.Scanner;


public class AD0812 {
    static int N; // 1<= N <= 10
    static int AnswerN;
    static int[][] matrix;
    static int ROW = 12;
    static int COL = 9;

    public static void main(String[] args) {
        // input
        System.setIn(AD0812.class.getResourceAsStream("./AD0812.txt"));
        Scanner sc = new Scanner(System.in);

        matrix = new int[][] {
                { 0, 0, 0, 1, 2, 3, 0, 0, 0 },
                { 0, 0, 0, 4, 5, 6, 0, 0, 0 },
                { 0, 0, 0, 7, 8, 9, 0, 0, 0 },
                { 37, 38, 39, 10, 11, 12, 46, 47, 48 },
                { 40, 41, 42, 13, 14, 15, 49, 50, 51 },
                { 43, 44, 45, 16, 17, 18, 52, 53, 54 },
                { 0, 0, 0, 19, 20, 21, 0, 0, 0 },
                { 0, 0, 0, 22, 23, 24, 0, 0, 0 },
                { 0, 0, 0, 25, 26, 27, 0, 0, 0 },
                { 0, 0, 0, 28, 29, 30, 0, 0, 0 },
                { 0, 0, 0, 31, 32, 33, 0, 0, 0 },
                { 0, 0, 0, 34, 35, 36, 0, 0, 0 }
        };

        int TC = sc.nextInt();
        // int TC = 1;

        for (int tcIdx = 0; tcIdx < TC; tcIdx++) {
            N = sc.nextInt();

            while (N > 0) {
                move(sc.nextInt());
                N--;
            }

            printSolution(sc.nextInt(), tcIdx + 1);
        }

        sc.close();
    }

    private static void move(int command) {
        int row, col;

        if (command < 4) {
            col = 2 + command;
            int tmp = matrix[0][col];

            for (int i = 0; i < ROW - 1; i++) {
                matrix[i][col] = matrix[i + 1][col];
            }

            matrix[ROW - 1][col] = tmp;

        } else if (command < 7) {
            col = -1 + command;
            int tmp = matrix[ROW - 1][col];

            for (int i = ROW - 1; i > 0; i--) {
                matrix[i][col] = matrix[i - 1][col];
            }

            matrix[0][col] = tmp;

        } else if (command < 10) {
            row = -4 + command;
            int tmp = matrix[row][0];

            for (int i = 0; i < COL - 1; i++) {
                matrix[row][i] = matrix[row][i + 1];
            }

            matrix[row][COL - 1] = tmp;

        } else if (command < 13) {
            row = -7 + command;
            int tmp = matrix[row][COL - 1];

            for (int i = COL - 1; i > 0; i--) {
                matrix[row][i] = matrix[row][i - 1];
            }

            matrix[row][0] = tmp;
        }
    }

    private static void printSolution(int which, int tcIdx) {
        int rowIdx = 0;
        int colIdx = 0;

        if (which == 1) {
            rowIdx = 0;
            colIdx = 3;
        } else if (which == 2) {
            rowIdx = 3;
            colIdx = 3;
        } else if (which == 3) {
            rowIdx = 6;
            colIdx = 3;
        } else if (which == 4) {
            rowIdx = 9;
            colIdx = 3;
        } else if (which == 5) {
            rowIdx = 3;
            colIdx = 0;
        } else if (which == 6) {
            rowIdx = 3;
            colIdx = 6;
        }

        System.out.printf("#%d", tcIdx);
        for (int i = rowIdx; i < rowIdx + 3; i++) {
            for (int j = colIdx; j < colIdx + 3; j++) {
                System.out.printf(" %d", matrix[i][j]);
            }
        }
    }
}