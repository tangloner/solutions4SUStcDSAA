import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.io.*;
import java.lang.reflect.Array;
import java.math.*;
import java.util.StringTokenizer;


public class C {
    public static void main(String[] args){
        InputStream inputStream = System.in;
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
        		int n = in.nextInt();
        		if (n==0) {
					break;
				}
        		String str = in.next();
        		mySolve(n,str,out);
            }
        }
        
        private static void mySolve(int n, String str, PrintWriter out) {
        	char[] charArr = str.toCharArray(); 
        	int[] arr = new int[26];
        	int res = 0;
        	for (int i=0; i < charArr.length; i++) {
        		int j = charArr[i]-'A';
        		if (arr[j]==-1) { // 来的时候没书，走的时候直接走
        			arr[j]++;  
				}else if (arr[j]==1) { // 来的时候借书，走的时候还书走
					n++; 
					arr[j]--; 
				}else if (n>0) { // 第一次来，有书就借，arr标记为1
        			arr[j]++;
        			n--;
				}else{ // 第一次来，没书，冲突+1，arr标记为-1
					arr[j]--;
					res++;
				}
			}
        	out.println(res);
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
            } catch(IOException e) {
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

