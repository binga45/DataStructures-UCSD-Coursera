import java.util.*;
import java.util.stream.Collectors;
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
	public static void max_sliding_window_pq(int[] arr, int m){
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		List<Integer> r = new ArrayList<>();
		for(int i=0; i<arr.length; i++){
			if(i>=m) pq.remove(arr[i-m]);
			pq.add(arr[i]);
			if(i>=m-1) r.add(pq.peek());
		}
		for(int i: r.stream().mapToInt(x -> x).toArray())
			System.out.print(i + " ");
		
	}
	public static void max_sliding_window_q(int[] arr, int m){
		Deque<Integer> deque = new LinkedList<Integer>();
		for(int i=0; i<arr.length; i++){
			
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
		max_sliding_window_pq(arr,m);
		
	}
	/**
	 * Involves suffix,prefix based approach to find maximum in each of the windows.
	 * @param arr
	 * @param m
	 */
	public static void max_sliding_window(int[] arr, int m){
		
		Map<Integer,List<List<Integer>>> window_prefix= new HashMap<>();
		Map<Integer,List<List<Integer>>> window_suffix= new HashMap<>();
		List<List<Integer>> prefixes = new ArrayList<>();
		List<List<Integer>> suffixes = new ArrayList<>();
		List<Integer> temp = new ArrayList<Integer>();
		int windowcounter=0,j=0;
		for(int i=0; i<arr.length; i = i+m){
			int[] window_elements = new int[m];
			prefixes.clear();
			suffixes.clear();
			prefixes.add(new ArrayList<Integer>());
			suffixes.add(new ArrayList<Integer>());
			for(j=i; (j<i+m && j<arr.length);j++)
				window_elements[j-i] = arr[j];
			for(int s=m-1; s>=0; s--){
				int l = i+s;
				temp.clear();
				for(int k = i ;( k<=l && k<arr.length && l<=j-1); k++){
					temp.add(arr[k]);
				}
				if(l<=j-1)
				prefixes.add(new ArrayList<Integer>(temp));
			}
			for(int s = m-1; s>=0; s-- ){
				int in = j-1-s;
				int k=0;
				temp.clear();
				for(k=in;( in >= i && k<=j-1);k++)
					temp.add(arr[k]);
				if(in>=i)
				suffixes.add(new ArrayList<Integer>(temp));
			}
			
			window_prefix.put(windowcounter, new ArrayList<List<Integer>>(prefixes));
			window_suffix.put(windowcounter, new ArrayList<List<Integer>>(suffixes));
			windowcounter++;
		}
		//System.out.println(windowcounter);
		/*for(int i=0; i< window_prefix.size(); i++)
			System.out.print(window_prefix.get(i));
		System.out.println("End");
		for(int i=0; i< window_prefix.size(); i++)
			System.out.print(window_suffix.get(i));*/
		int current_window=0,window_manager=0;
		for(int i=0; i<arr.length-m+1; i++){
			int k=0,l=0;
			List<List<Integer>> a = window_suffix.get(current_window);
			List<List<Integer>> b = window_prefix.get(current_window+1);
			
			for(; k<a.size();k++){
				if(a.get(k).size() != 0 && a.get(k).get(0)==arr[i] && a.get(k).size() == m-window_manager)
					break;
			}
			if(b != null){
				for(; l<b.size();l++){
					if(b.get(l).size()+a.get(k).size() == m)
						break;
				}
			}
			int bMax = (b == null || b.get(l).size() == 0) ? -28539 : Collections.max(b.get(l));
			System.out.print(Math.max(Collections.max(a.get(k)),bMax) + " ");
			window_manager++;
			if(window_manager == m && current_window <= windowcounter){
				window_manager=0;
				current_window++;
			}
		}
		
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
