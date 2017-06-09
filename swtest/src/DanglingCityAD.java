import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DanglingCityAD {
    static int V;
    static int E;
    static int[] visited;
    static int AnswerN;
    static int[] Answer;
    static List<List<Integer>> edge;
    static int count;

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        // input
        System.setIn(DanglingCityAD.class.getResourceAsStream("./danglingCityAD.txt"));
        Scanner sc = new Scanner(System.in);

        int from, to;

        for (int tcIdx = 0; tcIdx < 10; tcIdx++) {
            V = sc.nextInt();
            E = sc.nextInt();

            AnswerN = 0;
            Answer = new int[V];
            edge = new ArrayList<>();

            for (int i = 0; i < V + 1; i++) {
                edge.add(new ArrayList<Integer>());
            }

            while (E > 0) {
                E--;
                from = sc.nextInt();
                to = sc.nextInt();
                edge.get(from).add(to);
                edge.get(to).add(from);
            }

            // logic
            // 모든 도시에 연결되어 있는지 체크
            count = 0;
            visited = new int[V + 1];
            int startVertex = 0;
            for (int i = 1; i < V + 1; i++) {
                // System.out.printf("#%d children=%d\n", i, edge.get(i).size());
                if (edge.get(i).size() > 0) {
                    startVertex = edge.get(i).get(0);
                    break;
                }
            }

            dfs2(startVertex);
            if (count < V - 1) {
                System.out.printf("#%d %d ", (tcIdx + 1), V);
                for (int j = 1; j < V + 1; j++) {
                    System.out.printf("%d ", j);
                }
                System.out.println();

                continue;
            }

            // 핵심도시 체크
            for (int i = 1; i < V + 1; i++) {
                count = 0;
                visited = new int[V + 1];
                startVertex = edge.get(i).get(0);

                dfs(startVertex, i);

                if (count < V - 1) {
                    Answer[AnswerN] = i;
                    AnswerN++;
                }
            }

            System.out.printf("#%d %d ", (tcIdx + 1), AnswerN);
            for (int i = 0; i < AnswerN; i++) {
                System.out.printf("%d ", Answer[i]);
            }
            System.out.println();
        }

        sc.close();
        long endTime = System.currentTimeMillis();
        System.out.println((endTime - startTime) / 1000f);
    }

    private static void dfs(int startVertex, int removeVertex) {
        // System.out.printf("removeVertex=%d, start=%d, count=%d\n", removeVertex, startVertex, count);
        visited[startVertex] = 1;
        count++;

        int targetVertex;
        for (int v = 0; v < edge.get(startVertex).size(); v++) {
            targetVertex = edge.get(startVertex).get(v);
            // System.out.printf("start=%d, target=%d, count=%d\n", startVertex, targetVertex, count);
            if (targetVertex != removeVertex && visited[targetVertex] == 0) {
                dfs(edge.get(startVertex).get(v), removeVertex);
            }
        }
    }

    private static void dfs2(int startVertex) {
        visited[startVertex] = 1;
        count++;
        // System.out.printf("start=%d,count=%d\n", startVertex, count);

        int targetVertex;
        for (int v = 0; v < edge.get(startVertex).size(); v++) {
            targetVertex = edge.get(startVertex).get(v);
            if (visited[targetVertex] == 0) {
                dfs2(edge.get(startVertex).get(v));
            }
        }
    }
}