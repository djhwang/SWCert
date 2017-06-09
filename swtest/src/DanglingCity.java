import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class DanglingCity {
    static int V; // Vertext
    static int E; // Edge
    static int idx;
    static List<String> list;
    static List<Integer> vertexList;

    public static void main(String[] args) {
        // input
        System.setIn(Brick.class.getResourceAsStream("./danglingCity.txt"));
        Scanner sc = new Scanner(System.in);

        V = sc.nextInt();
        E = sc.nextInt();

        vertexList = new ArrayList<Integer>(V);
        list = new ArrayList<String>(E);

        System.out.printf("%d %d\n", V, E);

        int start, end;
        while (E > 0) {
            E--;
            start = (int) Math.ceil(Math.random() * V);
            if (!vertexList.contains(start)) {
                vertexList.add(start);
            }
            end = (int) Math.ceil(Math.random() * V);

            while (start == end && !list.contains(start + "," + end)) {
                end = (int) Math.ceil(Math.random() * V);
            }
            

            // System.out.printf("[%d]start=%d, end=%d\n", ++idx, start, end);
            System.out.printf("%d %d ", start, end);

        }

        sc.close();
    }
}