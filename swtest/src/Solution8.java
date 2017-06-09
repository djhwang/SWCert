import java.util.Scanner;


/*
 * 지도
 *    
 */
class Solution8 {
    static int N; // 0 <= N <= 100
    static int M; // 1 <= N <= 100

    static String[] answer;
    static String[] input;

    public static void main(String args[]) throws Exception {
        System.setIn(Brick.class.getResourceAsStream("./sample_input8.txt"));

        Scanner sc = new Scanner(System.in);

        int index = 0;
        while (true) {
            index++;
            N = sc.nextInt();

            if (N == 0) {
                break;
            }

            sc.nextLine();

            answer = new String[N];
            for (int i = 0; i < N; i++) {
                answer[i] = sc.nextLine();
            }

            M = sc.nextInt();
            if (M == 0) {
                break;
            }
            sc.nextLine();
            input = new String[M];

            for (int i = 0; i < M; i++) {
                input[i] = sc.nextLine();
            }

            System.out.printf("Run #%d: %s\n", index, process());
        }

        sc.close();
    }

    public static String process() {
        int eval = 0;

        if (N != M) {
            return "Wrong Answer";
        }

        for (int j = 0; j < N; j++) {
            if (!answer[j].equals(input[j])) {
                String a = answer[j].replace(" ", "");
                String b = input[j].replace(" ", "");
                if (a.equals(b)) {
                    if (eval == 0) {
                        eval = 1;
                    }
                } else {
                    eval = 2;
                    break;
                }
            }
        }

        if (eval == 0) {
            return "Accepted";
        } else if (eval == 1) {
            return "Presentation Error";
        } else {
            return "Wrong Answer";
        }
    }
}