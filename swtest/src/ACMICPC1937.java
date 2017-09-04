

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
	static long[][] data;
	static long[][] dp;
	static int N;	// 1 ~ 500

	public static void main(String[] args) throws IOException {
//		FastScanner sc = new FastScanner(System.in, System.out);
		FastScanner sc = new FastScanner(new FileInputStream("ACMICPC1937.txt"), System.out);

		N = sc.nextInt();
		data = new long[N][N];
		dp = new long[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; i < N; i++) {
				data[i][j] = sc.nextInt();
			}
		}
		
		
		sc.close();
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
