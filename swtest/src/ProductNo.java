import java.util.Scanner;


/**
 * 
 * 사용 가능한 문자수 * 자리수!
 *
 */
public class ProductNo {
    static int T;
    static int LENGTH;
    static String first;
    static String last;
    static long[] factorial;

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private static long count(String input) {
        long count = 0;
        long[] usedChar = new long[LENGTH];

        for (int i = 0; i < LENGTH; i++) {
            char c = input.charAt(i);
            int index = ALPHABET.indexOf(c);

            for (int j = 0; j < i + 1; j++) {
                if ((0 < usedChar[j]) && (usedChar[j] < c)) {
                    index--;
                }
            }

            long tmp = index * f(LENGTH - i - 1);
            System.out.printf("%c, %d, %d\n", c, index, tmp);
            count += tmp;
            usedChar[i] = c;
        }

        return count + 1;
    }

    private static long f(int n) {
        return factorial[n];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(Jang.class.getResourceAsStream("./productNo.txt"));
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        // init factorial
        factorial = new long[27];
        factorial[0] = 1;

        for (int i = 1; i < 27; i++) {
            factorial[i] = factorial[i - 1] * i;
            // System.out.printf("%d factorial = %d\n", i, factorial[i]);
        }

        for (int test_case = 1; test_case <= T; test_case++) {
            LENGTH = sc.nextInt();
            first = sc.next();
            last = sc.next();

            System.out.println("#" + test_case + " " + (Math.abs(count(first) - count(last)) - 1));
        }
        sc.close();
    }
}