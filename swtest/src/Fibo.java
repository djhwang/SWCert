
public class Fibo {

    static int[] cache;

    public static void main(String[] args) {
        int n = 12;
        cache = new int[n];

        System.out.println(fibo(n));
    }

    static int fibo(int n) {
        cache[0] = 1;
        cache[1] = 1;

        for (int i = 2; i < n; i++) {
            cache[i] = cache[i - 2] + cache[i - 1];
        }

        return cache[n - 1];
    }

}
