

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ACMICPC1937 {
	static long[] data;
	static int N;	// 1 ~ 1000000
	static int M;	// 1 ~ 10000
	static int K;	// 1 ~ 10000
	static int A;
	static int B;
	static int C;
	static long D;
	static long[] tree;	// segment tree data structure
	static long[] lazy;	// lazy

	public static void main(String[] args) throws IOException {
//		FastScanner sc = new FastScanner(System.in, System.out);
		FastScanner sc = new FastScanner(new FileInputStream("ACMICPC10999.txt"), System.out);

		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		data = new long[N+1];
		tree = new long[N*4];
		lazy = new long[N*4];
		
		for (int i = 1; i < N+1; i++) {
			data[i] = sc.nextInt();
		}
		
		init(1, 1, N);
		
		for (int i = 0; i < M+K; i++) {
			A = sc.nextInt();
			B = sc.nextInt();
			C = sc.nextInt();

			if (B > C) {
				int tmp = C;
				C = B;
				B = tmp;
			}
			
			if (A == 1) {
				D = sc.nextLong();
				updateRange(B, C, D, 1, 1, N);
			}
			else if (A == 2) {
				long sum = query(B, C, 1, 1, N);
				System.out.println(sum);
			}
		}
		
		sc.close();
	}

	public static long init(int node, int start, int end) {
		if (start == end) { // leaf
			tree[node] = data[end];
		} else {
			int mid = (start + end) / 2;
			tree[node] = init(node * 2, start, mid) + init(node * 2 + 1, mid + 1, end);
		}

		return tree[node];
	}

	// 합의 구하는 범위 left, right, node가 담당하는 구간 start, end
	public static long query(int left, int right, int node, int start, int end) {
		// check lazy
		updateLazy(node, start, end);

		if (right < start || left > end) { // 겹치지 않는 경우
			return 0;
		} else if (left <= start && right >= end) { // 구하는 범위가 하나의 node 에 포함되는 경우
			return tree[node];
		} else {
			int mid = (start + end) / 2;
			return query(left, right, node * 2, start, mid) + query(left, right, node * 2 + 1, mid + 1, end);
		}
	}

	public static void updateRange(int left, int right, long value, int node, int start, int end) {
		updateLazy(node, start, end);

		if (right < start || left > end) {
			return;
		}
		
		if (left <= start && right >= end) {
			tree[node] += (end - start + 1) * value;
			if (start != end) {
				lazy[node * 2] += value;
				lazy[node * 2 + 1] += value;
			}
			return;
		} else {
			int mid = (start + end) / 2;
			updateRange(left, right, value, node * 2, start, mid);
			updateRange(left, right, value, node * 2 + 1, mid + 1, end);
			tree[node] = tree[node * 2] + tree[node * 2 + 1];
		}
	}

	public static void updateLazy(int node, int start, int end) {
		if (lazy[node] != 0) {
			tree[node] += (end - start + 1) * lazy[node];
			
			// leaf가 아니면 아래로 lazy 값을 전파한다.
			if (start != end) {
				lazy[node * 2] += lazy[node];
				lazy[node * 2 + 1] += lazy[node];
			}
			lazy[node] = 0;
		}
	}

	static class FastScanner {
		private final BufferedReader reader;
		private final BufferedWriter writer;
		StringTokenizer tokenizer;

		FastScanner(InputStream in, OutputStream out) throws IOException {
			this.reader = new BufferedReader(new InputStreamReader(in));
			this.writer = new BufferedWriter(new OutputStreamWriter(out));
			this.tokenizer = new StringTokenizer(reader.readLine());
		}

		public String nextString() throws IOException {
			if (!tokenizer.hasMoreTokens()) {
				tokenizer = new StringTokenizer(reader.readLine());
			}

			return tokenizer.nextToken();
		}

		public int nextInt() throws NumberFormatException, IOException {
			return Integer.parseInt(nextString());
		}
		
		public long nextLong() throws NumberFormatException, IOException {
			return Long.parseLong(nextString());
		}

		public void write(String s) throws IOException {
			writer.write(s);
		}

		public void write(int n) throws IOException {
			writer.write(n);
		}
		
		public void close() throws IOException {
			writer.close();
			reader.close();
		}
	}

}
