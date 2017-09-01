

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class ACMICPC2096 {
	static int[][] data;
	static int N;
	static Item[][] dp;

	public static void main(String[] args) throws IOException {
		// FastScanner scanner = new FastScanner(System.in, System.out);
		FastScanner scanner = new FastScanner(new FileInputStream("ACMICPC2096.txt"), System.out);

		N = scanner.nextInt();
		data = new int[N][3];
		dp = new Item[N][3];

		for (int i = 0; i < N; i++) {
			data[i][0] = scanner.nextInt();
			data[i][1] = scanner.nextInt();
			data[i][2] = scanner.nextInt();
		}

		dp[0][0] = new Item(data[0][0], data[0][0]);
		dp[0][1] = new Item(data[0][1], data[0][1]);
		dp[0][2] = new Item(data[0][2], data[0][2]);

		int min = 0;
		int max = 0;

		for (int i = 1; i < N; i++) {
			// 0
			min = Math.min(dp[i - 1][0].min, dp[i - 1][1].min) + data[i][0];
			max = Math.max(dp[i - 1][0].max, dp[i - 1][1].max) + data[i][0];
			dp[i][0] = new Item(min, max);

			// 1
			min = Math.min(Math.min(dp[i - 1][0].min, dp[i - 1][1].min), dp[i - 1][2].min) + data[i][1];
			max = Math.max(Math.max(dp[i - 1][0].max, dp[i - 1][1].max), dp[i - 1][2].max) + data[i][1];
			dp[i][1] = new Item(min, max);

			// 2
			min = Math.min(dp[i - 1][1].min, dp[i - 1][2].min) + data[i][2];
			max = Math.max(dp[i - 1][1].max, dp[i - 1][2].max) + data[i][2];
			dp[i][2] = new Item(min, max);
		}

		// result
		min = Math.min(Math.min(dp[N - 1][0].min, dp[N - 1][1].min), dp[N - 1][2].min);
		max = Math.max(Math.max(dp[N - 1][0].max, dp[N - 1][1].max), dp[N - 1][2].max);

		scanner.write(max + " " + min);

		scanner.close();
	}

	static class Item {
		int min;
		int max;

		Item(int min, int max) {
			this.min = min;
			this.max = max;
		}
	}

	static class FastScanner {
		final BufferedReader reader;
		final BufferedWriter writer;
		private StringTokenizer tokenizzer;

		public FastScanner(InputStream in, OutputStream out) throws IOException {
			this.reader = new BufferedReader(new InputStreamReader(in));
			this.writer = new BufferedWriter(new OutputStreamWriter(out));
			this.tokenizzer = new StringTokenizer(reader.readLine());
		}

		public String nextString() throws IOException {
			if (!tokenizzer.hasMoreTokens()) {
				tokenizzer = new StringTokenizer(reader.readLine());
			}

			return tokenizzer.nextToken();
		}

		public int nextInt() throws NumberFormatException, IOException {
			return Integer.parseInt(nextString());
		}

		public void write(String str) throws IOException {
			writer.write(str);
		}

		public void close() throws IOException {
			writer.close();
			reader.close();
		}
	}
}