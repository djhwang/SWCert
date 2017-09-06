import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ACMICPC1774 {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	static class FastScanner {
		private final BufferedReader reader;
		private final BufferedWriter writer;
		private StringTokenizer tokenizer;
		
		public FastScanner(InputStream in, OutputStream out) throws IOException {
			this.reader = new BufferedReader(new InputStreamReader(in));
			this.writer = new BufferedWriter(new OutputStreamWriter(out));
			tokenizer = new StringTokenizer(reader.readLine());
		}
		
		public String nextString() throws IOException{
			if (!tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}
			
			return tokenizer.nextToken();
		}
		
		public int nextInt() throws NumberFormatException, IOException {
			return Integer.parseInt(nextString());
		}
		
		
		public void close() throws IOException{
			writer.close();
			reader.close();
		}
		
	}
	
	static class DisjointSet {
		private int[] parent;
		private int[] rank;
		
		public DisjointSet(int n) {
			parent = new int[n];
			rank = new int[n];
			
			for (int i = 1; i <= n; i++) {
				parent[i] = i;
				rank[i] = 1;
			}
		}

		
		public int find(int u) {
			if (u == parent[u]) {
				return u;
			}
			
			return parent[u] = find(parent[u]);
		}
		
		// merge a to b
		public void merge(int a, int b) {
			a = find(a);
			b = find(b);
			
			if (a == b) return;
			
			if (rank[a] > rank[b]) {
				int tmp = a;
				a = b;
				b = tmp;
			}
			
			parent[a] = b;
			
			if (rank[a] == rank[b]) {
				++rank[b];
			}
		}
	}
}
