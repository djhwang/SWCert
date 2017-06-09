
public class DisjointsetTest {

    public static void main(String[] args) {
        DisjointSet disjointSet = new DisjointSet(10);
        disjointSet.find(1);
    }

    static class DisjointSet {
        int[] parent;
        int[] rank;

        public DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];

            // 초기화
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        // u 가 속한 트리의 루트 번호를 리턴한다.
        int find(int u) {
            if (u == parent[u]) {
                return u;
            }

            // return find(parent[u]);
            return parent[u] = find(parent[u]); // 경로압축
        }

        // a 가 속한 트리와 b 가 속한 트리를 합친다.
        void union(int a, int b) {
            // root 찾기
            a = find(a);
            b = find(b);

            // 같은 트리에 속하는 경우
            if (a == b) {
                return;
            }

            // 항상 rank[b] 가 크도록 한다.
            if (rank[a] > rank[b]) {
                int temp = a;
                a = b;
                b = temp;
            }

            // rank 가 높은 tree 로 합친다.
            parent[a] = b;

            // tree 의 높이가 같다면 높이를 1 높힌다.
            if (rank[a] == rank[b]) {
                ++rank[b];
            }
        }
    }
}
