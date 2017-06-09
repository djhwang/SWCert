import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;


public class Pro0527 {
    static int N;
    static int M;
    static int[] visited;
    static int[] totalLength;
    static List<Edge> edgeList;

    public static void main(String[] args) throws IOException {
        // FastScanner sc = new FastScanner(System.in, System.out);
        FastScanner sc = new FastScanner(new FileInputStream(new File("Pro0527.txt")), System.out);
        int testcases = sc.nextInt();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < testcases; i++) {
            N = sc.nextInt();
            M = sc.nextInt();

            visited = new int[N + 1];
            totalLength = new int[N + 1];
            edgeList = new LinkedList<Edge>();

            for (int j = 0; j < M; j++) {
                edgeList.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
            }

            Collections.sort(edgeList);

            // 출발점 세팅
            visited[1] = 1;
            totalLength[1] = 0;

            for (Edge e : edgeList) {
                int tmpTotalLength = totalLength[e.to];
                int tmpVisited = visited[e.to];

                if (visited[e.from] == 1) { // 방문한 정점에서 출발한 경우
                    totalLength[e.to] = Math.max(totalLength[e.to], totalLength[e.from] + e.length);
                    visited[e.to] = 1; // 방문 마킹
                }

                if (tmpVisited == 1) { // 역방향 체크
                    totalLength[e.from] = Math.max(totalLength[e.from], tmpTotalLength + e.length);
                    visited[e.from] = 1; // 방문 마킹
                }
            }

            long sum = 0;
            for (int j = 1; j <= N; j++) {
                if (visited[j] == 1) {
                    sum += totalLength[j];
                } else {
                    sum -= 1;
                }
            }

            System.out.println("#" + (i + 1) + " " + sum);
        }
        long elapsed = System.currentTimeMillis() - startTime;
        System.out.println("Elapsed:" + elapsed / 1000.0);
    }
}


class Edge implements Comparable<Edge> {
    int from;
    int to;
    int length;

    public Edge(int from, int to, int length) {
        this.from = from;
        this.to = to;
        this.length = length;
    }

    @Override
    public int compareTo(Edge o) {
        return this.length - o.length;

    }
}


class FastScanner {
    private final BufferedReader reader;
    private final BufferedWriter writer;
    StringTokenizer tokenizer;

    FastScanner(InputStream in, OutputStream out) throws IOException {
        reader = new BufferedReader(new InputStreamReader(in));
        writer = new BufferedWriter(new OutputStreamWriter(out));
        tokenizer = new StringTokenizer(reader.readLine());
    }

    public String nextLine() throws IOException {
        if (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }

        return tokenizer.nextToken();
    }

    public int nextInt() throws NumberFormatException, IOException {
        return Integer.parseInt(nextLine());
    }

    public void close() throws IOException {
        writer.close();
        reader.close();
    }
}