import java.util.*;

import java.io.*;

public class substring_equality {
	private static int prime1 = 1000000007;
	private static int prime2 = 1000000009;
    private static int x = 3;
    long [] h11 ,h22,h21,h12;
	public class Solver {
		private String s;
		public Solver(String s) {
			this.s = s;
		}
		public boolean ask(int a, int b, int l,long y) {
			String s1 = s.substring(a, a + l);
			String s2 = s.substring(b, b + l);
			//long y = 1,y1=1;
//			for(int i=0; i<l;i++)
//				y=y*x;
//			System.out.println(y);
			int l1 = l,x1=x;
			while (l1 > 0) {
	            if ((l1 & 1) == 1) {
	                y *= x1;        
	            }
	            l1 >>= 1;
	            x1 *= x1; 
	        }
			//System.out.println("l is " + l + ";y is " + y);
			//long y = (long)Math.pow(x,l);
			long Hash11 =  ((h11[a+l]-y*h11[a])%prime1 + prime1)%prime1;
			long Hash12 = ((h12[b+l]-y*h12[b])%prime1 + prime1)%prime1;
			long Hash21 = ((h21[a+l]-y*h21[a])%prime2 + prime2)%prime2;
			long Hash22 = ((h22[b+l]-y*h22[b])%prime2 + prime2)%prime2;
			if(Hash11 == Hash12 && Hash21 == Hash22){
				return s1.equals(s2);
			}
			else{
				return false;
			}
				
		}
	}
	private static long[] precomputeHashes(String s1, int prime){
    	int m = s1.length();
    	long[] h = new long[m+1];
    	h[0] = 0;
    	for(int i=1; i<=m; i++){
    		h[i]=((x*h[i-1]+s1.charAt(i-1))%prime+prime)%prime;
    	} 	
    	return h;
	}

	public void run() throws IOException {
		FastScanner in = new FastScanner();
		PrintWriter out = new PrintWriter(System.out);
		String s = in.next();
		int q = in.nextInt();
		Solver solver = new Solver(s);
		h11 = precomputeHashes(s,prime1); 
		h12 = precomputeHashes(s,prime1);
		h21 = precomputeHashes(s,prime2);
		h22 = precomputeHashes(s,prime2);
		for (int i = 0; i < q; i++) {
			int a = in.nextInt();
			int b = in.nextInt();
			int l = in.nextInt();
			long l1 = 1;
			out.println(solver.ask(a, b, l,l1) ? "Yes" : "No");
		}
		out.close();
	}

	static public void main(String[] args) throws IOException {
	    new substring_equality().run();
	}

	class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}
}
