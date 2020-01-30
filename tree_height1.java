import java.util.*;
import java.io.*;

 class tree_height1 {
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

	public class TreeHeight {
		int n;
		int parent[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner();
			n = in.nextInt();
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		int computeHeight() {
           int maxHeight = 0;
           Map<Integer,ArrayList<Integer>> nodes = new HashMap<>();
           int root = -23456,vertex=0;
           
           for(; vertex<parent.length;vertex++){  
	            if(parent[vertex] == -1)
	            	root = vertex;
	            else if(nodes.containsKey(parent[vertex])){
	        		nodes.get(parent[vertex]).add(vertex);
	        	   }
	        	else
	        		nodes.put(parent[vertex], new ArrayList<Integer>(Arrays.asList(vertex)));
           }
           Queue q = new LinkedList<Integer>();
           q.add(root);
           while(true){
	        	int nodelength = q.size();
	        	if(nodelength == 0)
	        		return maxHeight;
	        	else{
	        		while(nodelength>0){
	        			int currentVertex = (int) q.poll();
	        			if(currentVertex >=0){
	        			    ArrayList<Integer> temp = nodes.get(currentVertex);
	        			    if(temp != null){
		        				for(int j=0; j < temp.size();j++)
		        					q.add(temp.get(j));
	        			    }
	        			}
	        			nodelength--;
	        		}
	        		maxHeight++;
	        	}	   
           }
           
		}
	}
	
	
	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height1().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
