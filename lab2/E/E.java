import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class E {
	public static void main(String[] args) {
		InputStream inputStream = System.in;// new
											// FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
		OutputStream outputStream = System.out;
		InputReader in = new InputReader(inputStream);
		PrintWriter out = new PrintWriter(outputStream);
		Task solver = new Task();
		solver.solve(in, out);
		out.close();
	}

	static class Task {
		public void solve(InputReader in, PrintWriter out) {
			while (in.hasNext()) {
				int length = in.nextInt();
				int n = in.nextInt();
				int m = in.nextInt();
				int[] arr = new int[n+2];
				arr[0]=0;
				arr[n+1]=length;
				for (int i = 1; i <= n; i++) {
					arr[i] = in.nextInt();
				}
				Arrays.sort(arr);
				int l = 0;
				int r = length;
				int mid = 0; // mid二分完后，是最终输出的结果
				while (r >= l) {
					mid = l + ((r - l) >> 1);
					if (greedy(mid, arr) > m) { // 注意是否取等，扣边界
						l = mid + 1;
					} else {
						r = mid - 1;
					}
				}
				while (greedy(mid, arr)<m) { //针对r已经小于l，但是贪心得到的人数还是少于m的情况
					mid++;
				}
				out.println(mid);
			}
		}

		private static int greedy(int d, int[] arr) {
			// 洗一下无效的情况
			// 如果arr里有任何一个区间超过了d，则不可能在d内跑完,需要让d更大，才能至少跑完。
			int i = 0;
			for (; i < arr.length-1; i++) {
				if (arr[i+1]-arr[i]>d) {
					return Integer.MAX_VALUE;
				}
			}
			// 进行贪心：通过pre和cur两个指针完成
			int pre = 0;
			int cur = 0;
			int res = 0;
			for (i = 1; i < arr.length; i++) {
				if (arr[i]-pre>d) { // 扣边界，是否取等
					pre=cur;
					cur=arr[i];
					res++;
				}else{
					cur=arr[i];
				}
			}
			return cur-pre<=d? res+1:res; // 算上最后一个人的情况
		}
	}

	static class InputReader {
		public BufferedReader reader;
		public StringTokenizer tokenizer;

		public InputReader(InputStream stream) {
			reader = new BufferedReader(new InputStreamReader(stream), 32768);
			tokenizer = null;
		}

		public String next() {
			while (tokenizer == null || !tokenizer.hasMoreTokens()) {
				try {
					tokenizer = new StringTokenizer(reader.readLine());
				} catch (IOException e) {
					throw new RuntimeException(e);
				}
			}
			return tokenizer.nextToken();
		}

		public int nextInt() {
			return Integer.parseInt(next());
		}

		public long nextLong() {
			return Long.parseLong(next());
		}

		public double nextDouble() {
			return Double.parseDouble(next());
		}

		public char[] nextCharArray() {
			return next().toCharArray();
		}

		public boolean hasNext() {
			try {
				String string = reader.readLine();
				if (string == null) {
					return false;
				}
				tokenizer = new StringTokenizer(string);
				return tokenizer.hasMoreTokens();
			} catch (IOException e) {
				return false;
			}
		}

		public BigInteger nextBigInteger() {
			return new BigInteger(next());
		}

		public BigDecimal nextBigDecinal() {
			return new BigDecimal(next());
		}
	}
}
