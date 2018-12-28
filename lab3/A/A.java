import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class A {
	static PrintWriter out;
	static Reader in;

	public static void main(String[] args) throws Exception {
		out = new PrintWriter(System.out);
		in = new Reader();
		Task solver = new Task();
		for (int t = in.nextInt(); t > 0; t--) { // As same as scanner.nextInt()
			solver.mySolve(in, out);
		}
		out.close(); // Don't forget this line, otherwise you will output
						// nothing. This sentence flush the buffer.
	}

	static class Task {
		public void mySolve(Reader in, PrintWriter out) throws Exception {
			int n = in.nextInt();
			int m = in.nextInt();
			int[] arr1 = new int[n];
			int[] arr2 = new int[m];
			for (n = 0; n < arr1.length; n++) {
				arr1[n] = in.nextInt();
			}
			for (m = 0; m < arr2.length; m++) {
				arr2[m] = in.nextInt();
			}
			for (n = 0, m = 0; n < arr1.length && m < arr2.length;) {
				if (arr1[n] < arr2[m]) {
					out.print(arr1[n++]+" ");
				}else {
					out.print(arr2[m++]+" ");
				}
			}
			while(n<arr1.length){
				out.print(arr1[n++]+" ");
			}
			while(m<arr2.length){
				out.print(arr2[m++]+" ");
			}
			out.println(); //换行
		}
	}

	static class Reader {
		final private int BUFFER_SIZE = 1 << 16;
		private DataInputStream din;
		private byte[] buffer;
		private int bufferPointer, bytesRead;

		public Reader() {
			din = new DataInputStream(System.in);
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public Reader(String file_name) throws IOException {
			din = new DataInputStream(new FileInputStream(file_name));
			buffer = new byte[BUFFER_SIZE];
			bufferPointer = bytesRead = 0;
		}

		public String readLine() throws IOException {
			byte[] buf = new byte[64]; // line length
			int cnt = 0, c;
			while ((c = read()) != -1) {
				if (c == '\n')
					break;
				buf[cnt++] = (byte) c;
			}
			return new String(buf, 0, cnt);
		}

		public int nextInt() throws IOException {
			int ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (neg)
				return -ret;
			return ret;
		}

		public long nextLong() throws IOException {
			long ret = 0;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();
			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');
			if (neg)
				return -ret;
			return ret;
		}

		public double nextDouble() throws IOException {
			double ret = 0, div = 1;
			byte c = read();
			while (c <= ' ')
				c = read();
			boolean neg = (c == '-');
			if (neg)
				c = read();

			do {
				ret = ret * 10 + c - '0';
			} while ((c = read()) >= '0' && c <= '9');

			if (c == '.') {
				while ((c = read()) >= '0' && c <= '9') {
					ret += (c - '0') / (div *= 10);
				}
			}

			if (neg)
				return -ret;
			return ret;
		}

		private void fillBuffer() throws IOException {
			bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
			if (bytesRead == -1)
				buffer[0] = -1;
		}

		private byte read() throws IOException {
			if (bufferPointer == bytesRead)
				fillBuffer();
			return buffer[bufferPointer++];
		}

		public void close() throws IOException {
			if (din == null)
				return;
			din.close();
		}
	}
}
