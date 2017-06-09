public class PRO0928 {
    static int N; // 1<= N <= 10
    static boolean[] visited;
    static int AnswerN;
    static int[] arr = { -10, -7, 5, -7, 10, 5, -2, 17, -25, 1 };
    static int[] sol;
    static int max;

    public static void main(String[] args) {

        long s, e;
        int ret = 0;

        s = System.currentTimeMillis();
        ret = cal();
        e = System.currentTimeMillis();

        System.out.printf("\n%d, elapsed time=%.5f", ret, (float) ((e - s) / 1000));

        s = System.currentTimeMillis();
        ret = cal2();
        e = System.currentTimeMillis();

        System.out.printf("\n%d, elapsed time=%.5f", ret, (float) ((e - s) / 1000));

        s = System.currentTimeMillis();
        ret = cal3();
        e = System.currentTimeMillis();

        System.out.printf("\n%d, elapsed time=%,.5f", ret, (float) ((e - s) / 1000));
    }

    private static int cal() {
        int n = arr.length;
        int ret = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                }
                ret = Math.max(ret, sum);
            }
        }

        return ret;
    }

    private static int cal2() {
        int n = arr.length;
        int ret = 0;

        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[j];
                ret = Math.max(ret, sum);
                // System.out.printf("%d ~ %d, sum=%d\n", i, j, sum);
            }
        }

        return ret;
    }

    private static int cal3() {
        int n = arr.length;
        int psum = 0;
        int ret = 0;

        for (int i = 0; i < n; i++) {
            psum = Math.max(psum, 0) + arr[i];
            ret = Math.max(ret, psum);
        }

        return ret;
    }

}