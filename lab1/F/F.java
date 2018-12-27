import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class F {
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
				int t = in.nextInt();
				for (int i = 0; i < t; i++) {
					mySolve(in, out);
				}
				break;
			}
		}
		private static void mySolve(InputReader in, PrintWriter out) {
			String s = in.next();
			if (s.length() <= 1) { // 洗掉无效参数
				out.println(0);
				return;
			}
			int i = 0;
			int j = 0;
			// set进行去重
			// map规定一个int->字母的对应对应关系
			HashSet<Character> set = new HashSet<>();
			HashMap<Integer,Character> map = new HashMap<>();
			for (i=0; i < s.length(); i++) {
				Character c = s.charAt(i);
				if (c != 'a' 
						&& c != 'e' 
						&& c != 'i' 
						&& c != 'o' 
						&& c != 'u' 
						&& c != 'w' 
						&& c != 'y') {
					if (!set.contains(c)) {
						map.put(j++, c);
					}
					set.add(c);
				}
			}
			int[][] matrix = new int[26][26];
			// 初始化字母矩阵matrix，统计相邻辅音
			// i,j是str的指针
			for (i = 0,j=1; j < s.length(); i++,j++) {
				char first = s.charAt(i);
				char next = s.charAt(j);
				if (set.contains(first)&&set.contains(next)) {
					matrix[first - 'a'][next - 'a'] += 1;
				}
			}
			// max是最终输出结果
			int max = 0;
			int size = set.size();
			for (i = 0; i < (1 << size); i++) {
				int tmp = 0;
				// flags布尔数组遍历所有情况
				boolean[] flags = new boolean[size];
				for (j = 0; j < size; j++) {
					if (((i >> j) & 1) == 1) {
						flags[j] = true;
					}
				}
				for (j = 0; j < size; j++) {
					for (int k = 0; k < size; k++) {
						if (flags[j]^flags[k]) {
							tmp += matrix[map.get(j)-'a'][map.get(k)-'a'];
						}
					}
				}
				max = Math.max(max, tmp);
			}
			out.println(max);
		}
	}

	// for test
	public static void printMatrix(int[][] matrix) {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void printFlags(boolean[] flags) {
		for (int i = 0; i < flags.length; i++) {
			System.out.print(flags[i] ? "T" : "F");
			System.out.print(" ");
		}
		System.out.println();
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
