import java.util.Scanner;


/*
 * 이친수
 */
public class Solution4 {
    static int[] v = new int[20];

    public static void main(String[] args) {
        // input
        System.setIn(Brick.class.getResourceAsStream("./sample_input4.txt"));
        Scanner sc = new Scanner(System.in);

        v[0] = 1;
        for (int i = 1; i < 20; i++) {
            v[i] = v[i - 1] * 2;
        }

        int test_case = sc.nextInt();

        String n1 = null;
        String n2 = null;

        for (int i = 0; i < test_case; i++) {
            n1 = sc.next();
            n2 = sc.next();

            int tmp = to10(n1) + to10(n2);
            System.out.println("#" + (i + 1) + " " + to2(tmp) + " " + tmp);
        }

        sc.close();
    }

    private static int to10(String s) {
        int result = 0;
        int len = s.length();

        for (int i = 0; i < len; i++) {
            switch (s.charAt(i)) {
                case '+':
                    result = result + v[len - 1 - i];
                    break;
                case '-':
                    result = result - v[len - 1 - i];
                    break;
                default:
                    break;
            }
        }

        return result;
    }

    private static String to2(int n) {
        StringBuilder sb = new StringBuilder();
        while (true) {
            if (n / 2 == 0) {
                sb.append("0");
                break;
            } else if (n / 2 == 1) {
                sb.insert(0, encode(n % 2));
                sb.insert(0, '+');
                break;
            } else if (n / 2 == -1) {
                sb.insert(0, encode(n % 2));
                sb.insert(0, '-');
                break;
            } else {
                sb.insert(0, encode(n % 2));
                n = n / 2;
            }
        }

        return sb.toString();
    }

    private static char encode(int i) {
        char c = '0';

        if (i == 1) {
            c = '+';
        } else if (i == -1) {
            c = '-';
        }

        return c;
    }
}
