import java.util.*;
import java.io.*;

 class tree_height {
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
            // Replace this code with a faster implementation
			/*int maxHeight = 0;
			for (int vertex = 0; vertex < n; vertex++) {
				int height = 0;
				for (int i = vertex; i != -1; i = parent[i])
					height++;
				maxHeight = Math.max(maxHeight, height);
			}
			return maxHeight;
			*/
			Node[] treeNodes = new Node[n];
			int root = -2391;
			for(int i=0; i < treeNodes.length; i++)
				treeNodes[i] = new Node(i);
			//construct a tree
			for(int child_index = 0; child_index < n ; child_index++){
				int parent_index = parent[child_index];
				if(parent_index == -1)
					root = child_index;
				else{
					treeNodes[parent_index].addChild(treeNodes[child_index]);
				}
			}
			//Do BFS using a queue
			Queue<Node> q = new LinkedList<>();
			List<List<Integer>> a = new ArrayList<>();
			List<Integer> b;
			List<Integer> tempList = new ArrayList<>();
			//List<Node>children;
			q.add(treeNodes[root]);
			int size=0;
			while(!q.isEmpty() ){
				//poll current head
				Node head = q.poll();
				//Check the size of the level-wise elements list of list
				if(!a.isEmpty()){
					b= a.get(a.size()-1);
					if(!b.contains(head.index)){
						if(tempList.size()>0){
							a.add(new ArrayList<Integer>(tempList));
							tempList.clear();
						}
					}
				}
				for(int i=0; i<head.getChildren().size(); i++){
					Node temp = head.getChildren().get(i); 
					q.add(temp);
					tempList.add(temp.index);
				}
				if(a.isEmpty()){
					a.add(new ArrayList<Integer>(tempList));
					tempList.clear();
				}
			}		
		    return 1+a.size();
		}		
	}
	
	static class Node{
		//Node lChild;
		//Node rChild;
		List<Node>children = new ArrayList<Node>();
		int index;
		public Node(int index){
		   this.index = index;
		}
		public List<Node> getChildren(){
			return this.children;
		}
		public void addChild(Node s){
//			if(this.lChild != null && this.lChild.index > s.index){
//				this.rChild = this.lChild;
//				this.lChild = s;
//			}
//			else if(this.lChild == null){
//				this.lChild = s;
//			}
//			else if(this.lChild.index < s.index){
//				this.rChild = s;
//			}
			if(s != null)
				this.children.add(s);
		}		
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
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
