
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

            // init
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        // u 가 속한 트리의 루트의 번호를 반환한다.
        int find(int u) {
            // root
        	if (u == parent[u]) {
                return u;
            }

            // return find(parent[u]);
            return parent[u] = find(parent[u]); // 寃쎈줈�븬異�
        }

        // a 가 속한 tree 를 b 가 속한 tree 에 합친다.
        void union(int a, int b) {
            a = find(a);
            b = find(b);

            if (a == b) {
                return;
            }

            if (rank[a] > rank[b]) {
                int temp = a;
                a = b;
                b = temp;
            }

            // rank 媛� �넂�� tree 濡� �빀移쒕떎.
            parent[a] = b;

            // tree �쓽 �넂�씠媛� 媛숇떎硫� �넂�씠瑜� 1 �넂�엺�떎.
            if (rank[a] == rank[b]) {
                ++rank[b];
            }
        }
    }
}
