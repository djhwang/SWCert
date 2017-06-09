import java.util.ArrayList;
import java.util.Scanner;


public class Solution10 {
    static int V;

    static int[] visited;

    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) {
        // input
        System.setIn(Brick.class.getResourceAsStream("./sample_input10.txt"));
        Scanner sc = new Scanner(System.in);

        int test_case = sc.nextInt();
        long AnswerN = 0;
        int tmp = 0;

        for (int tc = 0; tc < test_case; tc++) {
            AnswerN = 0;

            V = sc.nextInt();
            visited = new int[V];
            adjList = new ArrayList[V];

            for (int i = 0; i < V; i++) {
                adjList[i] = new ArrayList<Integer>();
                for (int j = 0; j < V; i++) {
                    tmp = sc.nextInt();
                    if (tmp == 1) {
                        adjList[i].add(tmp);
                    }
                }
            }

            // logic

            System.out.printf("#%d %d\n", (tc + 1), AnswerN);
        }

        sc.close();
    }
}