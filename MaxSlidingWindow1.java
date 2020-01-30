import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
public class MaxSlidingWindow1 {
	//Deque<Integer> dq = new ArrayDeque<Integer>();
	//int[] arr;
	public static void max_sliding_window_naive(int[] arr, int m){
		for(int i=0; i< arr.length-m+1; i++){
			int window_max = arr[i];
			for(int j=i+1; j<i+m;j++){
				window_max = Math.max(arr[j], window_max);
			}
			System.out.print(window_max + " ");
		}
	}
	public static void clean_deque(int i, int m, Deque<Integer> dq,int[] arr){
		//Removing indexes of element not from sliding window
		if(!dq.isEmpty() && dq.getFirst() == i-m)
			dq.removeFirst();
		//Remove from deque indexes of all elements which are smaller than arr[i]
		while(!dq.isEmpty() && arr[dq.getLast()]< arr[i])
		           dq.removeLast();
	}
	public static void max_sliding_window(int[] arr, int m){
		Deque<Integer> dq = new ArrayDeque<Integer>();
		int n = arr.length;
		int[] output = new int[n-m+1];
		
		if(n*m ==0){
			System.out.println(0);
			return;
		}
		if(n==1){
			System.out.println(arr[0]);
			return;
		}
		int max_idx=0;
		for(int i=0; i<m; i++){
			clean_deque(i,m,dq,arr);
			dq.addLast(i);
			if(arr[i] > arr[max_idx])max_idx = i;
		}
		
		output[0] = arr[max_idx];
		for(int i=m; i<arr.length; i++){
			clean_deque(i,m,dq,arr);
			dq.addLast(i);
			output[i-m+1] = arr[dq.getFirst()];
		}
		
		for(int i=0; i<output.length;i++)
			System.out.print(output[i] + " ");
	}
	
	public static void max_sliding_window_dp(int[] arr,int m){
		int n =arr.length;
		int[] left = new int[n];
		int[] right = new int[n];
		if(m*n==0)
			return;
		if(n==1){
			System.out.println(arr[0]);
			return;
		}
		left[0] = arr[0];
		right[n-1] = arr[n-1];
		for(int i=1; i<n ; i++){
			if(i%m==0){
				left[i] = arr[i];
			}
			else
				left[i] = Math.max(left[i-1], arr[i]);
			int j = n-i-1; 
			if((j+1)%m==0) right[j] = arr[j];
			else{
				right[j] = Math.max(arr[j], right[j+1]);
			}
		}
		int[] output = new int[n-m+1];
		for(int i=0; i<output.length; i++){
			output[i] = Math.max(right[i],left[i+m-1] );
		}
		for(int i=0; i<output.length;i++)
			System.out.print(output[i] + " ");
	}
		
	
	public static void main(String[] args) throws IOException {
		FastScanner scanner = new FastScanner();
		int size = scanner.nextInt();
		int[] arr = new int[size];
		for(int i=0; i<size;i++)
			arr[i] = scanner.nextInt();
		int m = scanner.nextInt();
		//max_sliding_window_naive(arr,m);
		max_sliding_window_dp(arr,m);
		
	}
	

}


