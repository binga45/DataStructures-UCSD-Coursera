import java.util.*;
import java.io.*;

public class tree_orders {
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

	public class TreeOrders {
		int n;
		int[] key, left, right;
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			key = new int[n];
			left = new int[n];
			right = new int[n];
			for (int i = 0; i < n; i++) { 
				key[i] = in.nextInt();
				left[i] = in.nextInt();
				right[i] = in.nextInt();
			}
		}

		List<Integer> inOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
			 inOrderTraversal(result, 0);
                        
			return result;
		}
		
		void inOrderTraversal(List<Integer> result, int key1){	
			if(key1<0)
				return;
			else{
				inOrderTraversal(result, left[key1]);
				result.add(key[key1]);
				inOrderTraversal(result,right[key1]);
			}
			
		}

		List<Integer> preOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
			preOrderTraversal(result,0);
                        
			return result;
		}
		
		void preOrderTraversal(List<Integer> result, int key1){	
			if(key1<0)
				return;
			else{
				result.add(key[key1]);
				preOrderTraversal(result, left[key1]);
				preOrderTraversal(result,right[key1]);
			}
			
		}

		List<Integer> postOrder() {
			ArrayList<Integer> result = new ArrayList<Integer>();
                        // Finish the implementation
                        // You may need to add a new recursive method to do that
			postOrderTraversal(result,0);           
			return result;
		}
		
		void postOrderTraversal(List<Integer> result, int key1){	
			if(key1<0)
				return;
			else{
				
				postOrderTraversal(result, left[key1]);
				postOrderTraversal(result,right[key1]);
				result.add(key[key1]);
			}
			
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}

	public void print(List<Integer> x) {
		for (Integer a : x) {
			System.out.print(a + " ");
		}
		System.out.println();
	}

	public void run() throws IOException {
		TreeOrders tree = new TreeOrders();
		tree.read();
		print(tree.inOrder());
		print(tree.preOrder());
		print(tree.postOrder());
	}
}
