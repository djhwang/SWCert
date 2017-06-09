import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class SegmentTree {
    private long[] data;
    private int size;
    private long[] tree; // segment tree
    private long[] lazy; // for lazy propagation

    SegmentTree(long[] data, int size) {
        this.data = data;
        this.size = size;
        this.tree = new long[size * 2];
        this.lazy = new long[size * 2];
        init(1, 1, size);
    }

    public long init(int node, int left, int right) {
        int mid = (left + right) / 2;

        if (left == right) { // leaf node
            tree[node] = data[left];
        } else {
            tree[node] = init(node * 2, left, mid) + init(node * 2 + 1, mid + 1, right);
        }

        return tree[node];
    }

    public long query(int start, int end) {
        return query(start, end, 1, 1, size);
    }

    private long query(int start, int end, int node, int left, int right) {
        if (left > end || right < start) { // 범위 바깥
            return 0;
        } else if (left <= start && right >= end) { // node 범위 안에 포함되는 경우
            return tree[node];
        } else {
            int mid = (left + right) / 2;
            return query(start, end, node * 2, left, mid) + query(start, end, node * 2 + 1, mid + 1, right);
        }
    }

    public void update(int index, long value) {
        update(index, value, 1, 1, size);
        data[index] = value;
    }

    private void update(int index, long value, int node, int left, int right) {
        if (index < left || index > right) { // 범위 바깥
            return;
        }

        tree[node] += value;
        if (left != right) { // has a child
            int mid = (left + right) / 2;
            update(index, value, node * 2, left, mid);
            update(index, value, node * 2 + 1, mid + 1, right);
        }
    }

    public void updateRange(int start, int end, long value, int node, int left, int right) {

        if (left > end || right < start) { // 범위 바깥
            return;
        }

        if (start == end) { // leaf node
            tree[node] += value;
            return;
        }

        int mid = (left + right) / 2;

        updateRange(start, end, value, node * 2, left, mid);
        updateRange(start, end, value, node * 2 + 1, mid + 1, right);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    public static void main(String[] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
        FastScanner sc = new FastScanner(System.in, System.out);
        // FastScanner sc = new FastScanner(new FileInputStream(""), System.out);

        int n, m, k;
        long[] data;

        n = m = k = 0;

        try {
            n = sc.nextInt(); // 수의 갯수
            m = sc.nextInt(); // 변경 횟수
            k = sc.nextInt(); // 구간합을 구하는 횟수

            data = new long[n];
            for (int i = 1; i <= n; i++) {
                data[i] = sc.nextInt();
            }

            m += k;

            SegmentTree segmentTree = new SegmentTree(data, n);

            int a, b, c;
            while (m-- > 0) {
                a = sc.nextInt();
                b = sc.nextInt();
                c = sc.nextInt();

                if (a == 1) {
                    segmentTree.update(b, c);
                } else if (a == 2) {
                    System.out.println(segmentTree.query(b, c));
                }
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Elapsed time:" + (endTime - startTime) / 1000.0);
        return;
    }

    public static class FastScanner {
        final BufferedReader reader;
        final BufferedWriter writer;
        StringTokenizer tokenizer;

        FastScanner(InputStream in, OutputStream out) throws IOException {
            reader = new BufferedReader(new InputStreamReader(in));
            writer = new BufferedWriter(new OutputStreamWriter(out));
            tokenizer = new StringTokenizer(reader.readLine());
        }

        String nextLine() throws IOException {
            if (!tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }

            return tokenizer.nextToken();
        }

        int nextInt() throws NumberFormatException, IOException {
            return Integer.parseInt(nextLine());
        }

        void write(String s) throws IOException {
            writer.write(s);
        }

        void write(int s) throws IOException {
            writer.write(s);
        }

        void close() throws IOException {
            reader.close();
            writer.close();
        }
    }
}
