import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class DanglingCityAD2 {
    static int V;
    static int E;
    static int AnswerCnt;
    static int[] Answer;
    static int[] visited;
    static List<List<Integer>> adjacent;

    public static void main(String[] args) {
        System.setIn(DanglingCityAD2.class.getResourceAsStream("./danglingCityAD.txt"));
        Scanner sc = new Scanner(System.in);

        for (int testcase = 0; testcase < 10; testcase++) {
            V = sc.nextInt();
            E = sc.nextInt();

            adjacent = new ArrayList<List<Integer>>();
            for (int v = 0; v <= V + 1; v++) {
                adjacent.add(new ArrayList<Integer>());
            }

            for (int i = 0; i < E; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                adjacent.get(start).add(end);
                adjacent.get(end).add(start);
            }

            AnswerCnt = 0;
            Answer = new int[V];

            for (int v = 1; v <= V; v++) {
                if (bfs(adjacent, v) == false) {
                    Answer[AnswerCnt++] = v;
                }
            }

            System.out.printf("#%d %d ", (testcase + 1), AnswerCnt);
            for (int i = 0; i < AnswerCnt; i++) {
                System.out.printf("%d ", Answer[i]);
            }
            System.out.println();

        }
        sc.close();
    }

    public static boolean bfs(List<List<Integer>> adjacent, int except) {
        Queue<Integer> queue = new LinkedList<Integer>();
        int[] visited = new int[V + 1];

        // 시작 정점 선택
        int start;
        if (except == 1)
            start = 2;
        else
            start = 1;

        // 시작 정점 추가
        queue.offer(start);
        visited[start] = 1;

        // 너비 우선 탐색
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            // 모든 인접한 정점들 중에서
            for (int x : adjacent.get(cur)) {
                if (x != except && visited[x] == 0) { // 방문한 정점이 아닐 경우
                    queue.offer(x);
                    visited[x] = 1;
                }
            }
        }

        boolean ret = true;
        // 너비우선 탐색을 했는데도 방문하지 않은 정점이 있는지 검사
        for (int v = 1; v <= V; v++) {
            if (v != except && visited[v] == 0) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public static boolean dfs(int except) {
        visited = new int[V + 1];

        // 시작 정점 선택
        int start;
        if (except == 1)
            start = 2;
        else
            start = 1;

        // 시작 정점을 기준으로 깊이 우선 탐색 수행
        dfs_recursive(start, except);

        boolean ret = true;
        // 너비우선 탐색을 했는데도 방문하지 않은 노드가 있는지 검사
        for (int v = 1; v <= V; v++) {
            if (v != except && visited[v] == 0) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public static void dfs_recursive(int vertex, int except) {
        visited[vertex] = 1;
        // 모든 인접한 노드들 중에서
        for (int x : adjacent.get(vertex)) {
            if (x != except && visited[x] == 0) { // 방문한 정점이 아닐 경우
                dfs_recursive(x, except);
            }
        }
    }
}
