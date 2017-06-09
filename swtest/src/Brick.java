import java.util.Arrays;
import java.util.Scanner;


/*
 * 별도쌓기
 *
 * 1cm 를 올리는데 필요한 연료량이 많은 것 부터 쌓아야 함
 */
class Brick {
    static int TC;
    static int N; // 1 <= N <= 1000

    static int height;
    static double fuel;
    static Stone[] stones;

    public static void main(String args[]) throws Exception {
        System.setIn(Solution.class.getResourceAsStream("./sample_input7.txt"));

        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();
        for (int test_case = 0; test_case < TC; test_case++) {
            N = sc.nextInt();
            stones = new Stone[N];
            for (int i = 0; i < N; i++) {
                stones[i] = new Stone(sc.nextInt(), sc.nextInt());
            }

            /**
             * fuel/height 역순으로 정열
             */
            Arrays.sort(stones);

            height = 0;
            fuel = 0f;

            for (int i = 0; i < N; i++) {
                fuel += height * stones[i].fuel;
                height += stones[i].height;
            }

            System.out.printf("#%d %.0f\n", test_case + 1, fuel / 10f);
        }

        sc.close();
    }

    private static class Stone implements Comparable<Stone> {
        private int height;
        private int fuel;
        private double ratio;

        public Stone(int height, int fuel) {
            this.height = height;
            this.fuel = fuel;
            this.ratio = (double) this.fuel / (double) this.height;
        }

        @Override
        public int compareTo(Stone that) {
            if (this.ratio > that.ratio) {
                return -1;
            } else if (this.ratio < that.ratio) {
                return 1;
            }

            return 0;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("height:").append(this.height).append(",fuel:").append(this.fuel).append(",ratio:").append(this.ratio).toString();
        }
    }
}