import java.util.*;



import java.io.*;
public class MaxSlidingWindow {
	
	public static void max_sliding_window_naive(int[] arr, int m){
		for(int i=0; i< arr.length-m+1; i++){
			int window_max = arr[i];
			for(int j=i+1; j<i+m;j++){
				window_max = Math.max(arr[j], window_max);
			}
			System.out.print(window_max + " ");
		}
	}
	
	public static void max_sliding_window(int[] arr, int m){
		Queue q = new Queue();
		int j=0;
		while(j!= m){
			q.enqueue(q,arr[j]);
			j++;
		}
		System.out.print(Math.max((int)q.stack1.Max(),(int)q.stack2.Max()) + " ");
		for(int i=1; i<arr.length-m+1; i++){
				q.dequeue(q);
				q.enqueue(q, arr[j]);
				System.out.print(Math.max((int)q.stack1.Max(),(int)q.stack2.Max()) + " ");
				j++;
		}
	}
	
	public static void main(String[] args) throws IOException {
		FastScanner scanner = new FastScanner();
		int size = scanner.nextInt();
		int[] arr = new int[size];
		for(int i=0; i<size;i++)
			arr[i] = scanner.nextInt();
		int m = scanner.nextInt();
		//max_sliding_window_naive(arr,m);
		max_sliding_window(arr,m);
		
	}

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
		if(super.size()==0)
			return null;
		maxstack.pop();
		return super.pop();
		
	}
	
	public Object Max(){
		if(maxstack.size() == 0)
			return -289748;
		return maxstack.peek();
		
	}
}

class Queue{
	SpecialStack stack1 = new SpecialStack();
	SpecialStack stack2 = new SpecialStack();
	
	void enqueue(Queue q, int a){
		q.stack1.push(a);
	}
	
	int dequeue(Queue q){
		int x;
		if(q.stack1.isEmpty() && q.stack2.isEmpty())
			System.exit(0);
		if(q.stack2.isEmpty()){
			while(!q.stack1.isEmpty()){
				q.stack2.push((int)q.stack1.pop());
			}
		}
		x = (int) q.stack2.pop();
		return x;
	}
	
}
