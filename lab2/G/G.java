import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class G {
	static PrintWriter out;
	static Reader in;

	public static void main(String[] args) throws Exception {
		out = new PrintWriter(System.out);
		in = new Reader();
		Task solver = new Task();
		for (int t = 1; t > 0; t--) { // As same as scanner.nextInt()
			solver.mySolve(in, out);
		}
		out.close(); // Don't forget this line, otherwise you will output
						// nothing. This sentence flush the buffer.
	}

	static class Task {
		public void mySolve(Reader in, PrintWriter out) throws Exception {
			int n = in.nextInt();
			int k = in.nextInt();
			int[] s = new int[n];
			int[] c = new int[n];
			int i =0;
			for (i = 0; i < n; i++) {
				s[i]=in.nextInt();
			}
			for (i = 0; i < n; i++) {
				c[i]=in.nextInt();
			}
			double l = 0;
			double r = 1000;
			while(r-l>1e-6){
				double m = l+(r-l)/2;
				if (check(m,k,s,c)>=0) {
					l=m;
				}else{
					r=m;
				}
			}
			out.println(String.format("%.3f", r));
		}

		public static double check(double mid,int k, int[] s, int[] c) {
			double[] tmp = new double[s.length];
			int i = 0;
			for (; i < s.length; i++) {
				tmp[i]=s[i]*(c[i]-mid);
			}
			Arrays.sort(tmp);
			double res = 0;
			for(i=s.length-1;i>=k;i--){
				res += tmp[i];
			}
			return res;
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
