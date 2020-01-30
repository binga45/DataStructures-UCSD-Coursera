import java.util.*;
import java.io.*;

public class StackWithMax {
	
	
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
    
    class SpecialStack extends Stack{
    	Stack<Integer> maxstack = new Stack<>();
    	void push(int x){
    		if(super.isEmpty()){
    			super.push(x);
    			maxstack.push(x);
    		}
    		else{
    			if(maxstack.peek() < x)
    				maxstack.push(x);
    			else
    				maxstack.push(maxstack.peek());
    			super.push(x);
    		}
    	}
    	
    	public Object pop(){
    		maxstack.pop();
    		return super.pop();
    		
    	}
    	
    	public Object Max(){
    		
    		return maxstack.peek();
    		
    	}
    }

    public void solve() throws IOException {
        FastScanner scanner = new FastScanner();
        int queries = scanner.nextInt();
        //Stack<Integer> stack = new Stack<Integer>();
        SpecialStack s = new SpecialStack();

        for (int qi = 0; qi < queries; ++qi) {
            String operation = scanner.next();
            if ("push".equals(operation)) {
                int value = scanner.nextInt();
                s.push(value);
            } else if ("pop".equals(operation)) {
                s.pop();
            } else if ("max".equals(operation)) {
                System.out.println(s.Max());
            }
        }
    }
    
    

    static public void main(String[] args) throws IOException {
        new StackWithMax().solve();
    }
}
