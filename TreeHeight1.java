import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class TreeHeight1 {
	
	static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
	
	static class Node{
		List<Node>children = new ArrayList<Node>();
		int index;
		public Node(int index){
		   this.index = index;
		}
		public List<Node> getChildren(){
			return this.children;
		}
		public void addChild(Node s){
			if(s != null)
				this.children.add(s);
		}		
	}
	
	static int computeHeight(int[] parent, int n) {
        
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
			//Time to add tempList to a and start a new tempList for computing another level of childs
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
	
	public static void main(String[] args){
		reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
		int n;
		int parent[];
		n = nextInt();
		parent = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = nextInt();
		}
		System.out.println(computeHeight(parent,n));
	}

}
