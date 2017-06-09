import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Bishop {
    static int N; // 1<= N <= 50
    static int AnswerN;
    static int[][] map;
    static int[][] map2;
    static int[][] bishop;

    static List<Point> pointList;

    public static void main(String[] args) {
        // input
        System.setIn(Brick.class.getResourceAsStream("./bishop.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = 1;

        for (int tcIdx = 0; tcIdx < TC; tcIdx++) {
            AnswerN = 0;
            N = sc.nextInt();
            map = new int[N][N];
            map2 = new int[N][N];
            bishop = new int[N][N];
            pointList = new ArrayList<Point>(N * N);

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    map[i][j] = sc.nextInt();
                    if (map[i][j] == 1) {
                        pointList.add(new Point(i, j, i * j));
                    }
                }
            }

            Collections.sort(pointList);
            for (int i = 0; i < pointList.size(); i++) {
                System.out.printf("x,y=%d,%d %d\n", pointList.get(i).x, pointList.get(i).y, pointList.get(i).numberOfBishop);
            }

            /*
             * for (int i = 0; i < N; i++) { for (int j = 0; j < N; j++) { int cnt = bt(pointList.get(i).x, pointList.get(i).y); System.out.printf("i, j, cnt = %d, %d, %d\n", pointList.get(i).x,
             * pointList.get(i).y, cnt); if (AnswerN < cnt) { AnswerN = cnt; } } }
             */

            for (int i = 0; i < pointList.size(); i++) {
                for (int x = 0; x < N; x++) {
                    for (int y = 0; y < N; y++) {
                        map2[x][y] = map[x][y];
                    }
                }

                int cnt = bt(pointList.get(i).x, pointList.get(i).y);
                System.out.printf("i, j, cnt = %d, %d, %d\n", pointList.get(i).x, pointList.get(i).y, cnt);
                if (AnswerN < cnt) {
                    AnswerN = cnt;
                }
            }

            System.out.println(AnswerN);
        }

        sc.close();
    }

    public static int bt(int row, int col) {
        int cnt = 0;

        for (int i = row; i < N; i++) {
            for (int j = col; j < N; j++) {
                settleBishop(i, j);
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                settleBishop(i, j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.printf("%d ", map2[i][j]);
                if (map2[i][j] == -1) {
                    cnt++;
                }
            }
            System.out.println();
        }

        return cnt;
    }

    public static int count(int i, int j) {
        int cnt = 0;

        int x = i;
        int y = j;

        // north-east
        while (true) {
            x++;
            y--;
            if (x < N && y > -1) {
                if (map[x][y] == 1) {
                    cnt++;
                }
            } else {
                break;
            }
        }

        // north-west
        x = i;
        y = j;
        while (true) {
            x--;
            y--;
            if (x > -1 && y > -1) {
                if (map[x][y] == 1) {
                    cnt++;
                }
            } else {
                break;
            }
        }

        // south-east
        x = i;
        y = j;
        while (true) {
            x++;
            y++;
            if (x < N && y < N) {
                if (map[x][y] == 1) {
                    cnt++;
                }
            } else {
                break;
            }
        }

        // south-west
        x = i;
        y = j;
        while (true) {
            x--;
            y++;
            if (x > -1 && y < N) {
                if (map[x][y] == 1) {
                    cnt++;
                }
            } else {
                break;
            }
        }

        return cnt;
    }

    public static void settleBishop(int i, int j) {
        int x = i;
        int y = j;

        if (map2[x][y] == 1) {
            map2[x][y] = -1;
        } else {
            return;
        }

        // north-east
        while (true) {
            x++;
            y--;
            if (x < N && y > -1) {
                if (map2[x][y] == 1) {
                    map2[x][y] = 0;
                }
            } else {
                break;
            }
        }

        // north-west
        x = i;
        y = j;
        while (true) {
            x--;
            y--;
            if (x > -1 && y > -1) {
                if (map2[x][y] == 1) {
                    map2[x][y] = 0;
                }
            } else {
                break;
            }
        }

        // south-east
        x = i;
        y = j;
        while (true) {
            x++;
            y++;
            if (x < N && y < N) {
                if (map2[x][y] == 1) {
                    map2[x][y] = 0;
                }
            } else {
                break;
            }
        }

        // south-west
        x = i;
        y = j;
        while (true) {
            x--;
            y++;
            if (x > -1 && y < N) {
                if (map2[x][y] == 1) {
                    map2[x][y] = 0;
                }
            } else {
                break;
            }
        }
    }

    private static class Point implements Comparable<Point> {
        private int x;
        private int y;
        private int numberOfBishop;

        Point(int x, int y, int numberOfBishop) {
            this.x = x;
            this.y = y;
            this.numberOfBishop = numberOfBishop;
        }

        @Override
        public int compareTo(Point target) {
            if (this.numberOfBishop < target.numberOfBishop) {
                return -1;
            } else {
                return 1;
            }
        }
    }
}