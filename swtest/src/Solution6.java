import java.util.Scanner;


/*
 * 지도
 *    
 */
class Solution6 {
    static int N; // 지도 크기 <= 50

    static int[][] map;
    static int[][] cache;

    public static void main(String args[]) throws Exception {
        System.setIn(Brick.class.getResourceAsStream("./sample_input6.txt"));

        Scanner sc = new Scanner(System.in);

        int tc = sc.nextInt();
        for (int test_case = 0; test_case < tc; test_case++) {
            N = sc.nextInt();
            map = new int[N][N];
            cache = new int[N][N];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                }
            }

            System.out.printf("#%d %d\n", test_case + 1, travel());
        }

        sc.close();
    }

    public static int travel() {
        int step = 0;
        int row = 0;
        int col = 0;

        while (true) {
            if (cache[row][col] == 1) { // already visited
                step = 0;
                break;
            } else {
                cache[row][col] = 1;
            }

            if (map[row][col] == 1) { // go right
                col++;
            } else if (map[row][col] == 2) { // go down
                row++;
            } else if (map[row][col] == 3) { // go left
                col--;
            } else if (map[row][col] == 4) { // go up
                row--;
            } else if (map[row][col] == 0) { // 도착 return 0;
                break;
            }
            if (row > N - 1 || col > N - 1 || 0 > row || 0 > col) {
                return 0;
            }

            step++;
        }

        return step;
    }
}