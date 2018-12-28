import java.util.Set;
import java.util.TreeMap;
import java.io.*;
import java.math.*;
import java.util.StringTokenizer;


public class B {
    public static void main(String[] args){
        InputStream inputStream = System.in;//new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
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
        			mySolve(in,out);
        		}
        		break;
            }
        }
        
        private static void mySolve(InputReader in,PrintWriter out) {
    		TreeMap<Integer, Integer> resultMap = new TreeMap<>();
    		int n = in.nextInt();
    		for (int i = 0; i < n; i++) {
    			int coef = in.nextInt(); // value
    			int expo = in.nextInt(); // key
    			resultMap.put(expo, coef);
    		}
    		int m = in.nextInt();
    		for (int i = 0; i < m; i++) {
    			int coef = in.nextInt(); // value
    			int expo = in.nextInt(); // key
    			int value;
    			try {
    				value = resultMap.get(expo);
				} catch (Exception e) {
					value = 0;
				}
				resultMap.put(expo, value+coef);
    		}
    		print(resultMap,out);
    	}

    	private static void print(TreeMap<Integer, Integer> resultMap,PrintWriter out) {
    		String rawStr = "";
    		String tmp = "";
    		Set<Integer> expoSet = resultMap.keySet();
    		for (Integer expo : expoSet) {
    			int coef = resultMap.get(expo);
    			if (coef != 0) {
    				tmp += (coef>0? "+"+coef : coef)+"x^"+expo;
    				tmp = tmp.replace("x^0","");
    				tmp = tmp.replace("1x","x");
    				tmp = tmp.replace("-1x","-x");
        			tmp = tmp.replace("x^1","x");
        			rawStr += tmp;
        			tmp = "";
    			}
    		}
    		if (rawStr.isEmpty()) {
    			rawStr = "0";
    		}
    		if (rawStr.startsWith("+")) {
    			rawStr = rawStr.substring(1);
    		}
    		out.println(rawStr);
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

