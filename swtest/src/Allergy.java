import java.util.Scanner;


public class Allergy {
    static int N; // 1<= N <= 50
    static int M; // 1<= M <= 50
    static int AnswerN;

    public static void main(String[] args) {
        // input
        System.setIn(Brick.class.getResourceAsStream("./allegy.txt"));
        Scanner sc = new Scanner(System.in);

        int TC = sc.nextInt();

        for (int tcIdx = 0; tcIdx < TC; tcIdx++) {
            AnswerN = 0;
            N = sc.nextInt();
            M = sc.nextInt();

            for (int i = 0; i < N; i++) {
            }

            // logic

            System.out.printf("#%d %d\n", (tcIdx + 1), AnswerN);
        }

        sc.close();
    }
}