import java.util.Arrays;
import java.util.Scanner;


public class Solution9 {
    static Point[] points;

    static Equation lineAB;
    static Equation lineAC;
    static Equation lineBC;

    public static void main(String[] args) {
        // input
        System.setIn(Brick.class.getResourceAsStream("./sample_input9.txt"));
        Scanner sc = new Scanner(System.in);

        int test_case = sc.nextInt();
        long AnswerN = 0;
        double min, max;

        for (int tc = 0; tc < test_case; tc++) {
            AnswerN = 0;
            points = new Point[] {
                    new Point(sc.nextInt(), sc.nextInt()),
                    new Point(sc.nextInt(), sc.nextInt()),
                    new Point(sc.nextInt(), sc.nextInt()),
            };

            // logic
            Arrays.sort(points);

            lineAB = new Equation(points[0], points[1]);
            lineAC = new Equation(points[0], points[2]);
            lineBC = new Equation(points[1], points[2]);

            // check triangle
            if (isTriangle()) {
                System.out.printf("#%d %d\n", (tc + 1), AnswerN);
                continue;
            }

            for (int x = points[0].x + 1; x < points[1].x + 1; x++) {
                max = lineAB.getY(x);
                min = lineAC.getY(x);
                AnswerN += calculateNumberOfPoints(min, max);
            }

            for (int x = points[1].x + 1; x < points[2].x; x++) {
                max = lineBC.getY(x);
                min = lineAC.getY(x);
                AnswerN += calculateNumberOfPoints(min, max);
            }

            System.out.printf("#%d %d\n", (tc + 1), AnswerN);
        }

        sc.close();
    }

    private static double calculateNumberOfPoints(double min, double max) {
        if (min > max) {
            double tmp = max;
            max = min;
            min = tmp;
        }
        return Math.ceil(max) - Math.floor(min) - 1;
    }

    private static boolean isTriangle() {
        if (lineAB.m == lineAC.m) {
            return true;
        }

        return false;
    }

    private static class Equation {
        float m, n;

        public Equation(Point p1, Point p2) {
            if (p1.x == p2.x) {
                m = -1;
            } else if (p1.y == p2.y) {
                m = 0;
            } else {
                m = ((float) p2.y - (float) p1.y) / ((float) p2.x - (float) p1.x);
                n = p1.y - (p1.x * m);
            }
        }

        private float getY(float x) {
            return (m * x + n);
        }
    }

    private static class Point implements Comparable<Point> {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x > o.x) {
                return 1;
            } else {
                return -1;
            }
        }
    }

}