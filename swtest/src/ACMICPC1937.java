

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
	static int[][] data;
	static int[][] dp;
	static int N;	// 1 ~ 500
	static int max_days = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
//		FastScanner sc = new FastScanner(System.in, System.out);
		FastScanner sc = new FastScanner(new FileInputStream("ACMICPC1937.txt"), System.out);

		N = sc.nextInt();
		
		data = new int[N][N];
		dp = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				data[i][j] = sc.nextInt();
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				traverse(i,j);
			}
		}
		
		System.out.println(max_days);
		sc.close();
	}
	
	private static int traverse(int i, int j) {
		// check dp table
		if (dp[i][j] > 0) {
			return dp[i][j];
		}
		
		dp[i][j] = 1;

		if (j < N-1) {
			// right
			if (data[i][j] < data[i][j+1]) {
				dp[i][j] = Math.max(dp[i][j], traverse(i,j+1)+1);
			}
		}
		
		if (j > 0) {
			// left
			if (data[i][j] < data[i][j-1]) {
				dp[i][j] = Math.max(dp[i][j], traverse(i,j-1)+1);
			}
		}
		
		if (i < N-1) {
			// down
			if (data[i][j] < data[i+1][j]) {
				dp[i][j] = Math.max(dp[i][j], traverse(i+1,j)+1);
			}
		}
		
		if (i > 0) {
			// up
			if (data[i][j] < data[i-1][j]) {
				dp[i][j] = Math.max(dp[i][j], traverse(i-1,j)+1);
			}
		}
		
		max_days = Math.max(max_days, dp[i][j]);
		
		return dp[i][j];
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
