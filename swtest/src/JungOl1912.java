import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;


public class JungOl1912 {
    static int N; // 2<= N <= 100000
    static int M; // 1<= M <= 500000
    static Room[] rooms;
    static boolean[] visited;
    static int AnswerN;
    static Queue<Integer> queue;
    static ArrayDeque<Integer> dq;

    public static void main(String[] args) {
        // input
        System.setIn(Brick.class.getResourceAsStream("./JungOl1912.txt"));
        Scanner sc = new Scanner(System.in);

        // int TC = sc.nextInt();
        int TC = 1;
        int from, to;

        for (int tcIdx = 0; tcIdx < TC; tcIdx++) {
            AnswerN = 0;
            N = sc.nextInt();
            M = sc.nextInt();

            rooms = new Room[N + 1];
            visited = new boolean[N + 1];

            for (int i = 0; i < M; i++) {
                from = sc.nextInt();
                to = sc.nextInt();

                if (rooms[from] == null) {
                    rooms[from] = new Room(from);
                }
                rooms[from].add(new Room(to));

                if (rooms[to] == null) {
                    rooms[to] = new Room(to);
                }
                rooms[to].add(new Room(from));
            }

            // sorting
            for (int i = 1; i < N + 1; i++) {
                if (rooms[i] != null) {
                    rooms[i].sort();
                }
            }

            // logic
            dfs(1);
        }

        sc.close();
    }

    private static void dfs(int start) {
        visited[start] = true;

        System.out.printf("%d ", start);
        if (++AnswerN >= N) {
            return;
        }

        for (Room next : rooms[start].nextList) {
            if (!visited[next.no]) {
                dfs(next.no);
            }
        }
    }

    private static class Room implements Comparable<Room> {
        int no;
        List<Room> nextList = new ArrayList<Room>();

        public Room(int no) {
            this.no = no;
        }

        public void add(Room room) {
            this.nextList.add(room);
        }

        public void sort() {
            Collections.sort(nextList);
        }

        @Override
        public int compareTo(Room o) {
            return this.no - o.no;
        }
    }
}