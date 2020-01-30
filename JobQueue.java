import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }
    private class Worker implements Comparable{
    	int id;
    	long nextFreeTime;
    	public Worker(int id){
    		this.id = id;
    		this.nextFreeTime = 0;
    	}
    	public int getId(){
    		return this.id;
    	}
    	public long getNextFreeTime(){
    		return this.nextFreeTime;
    	}
    	public void setNextFreeTime(long nextFreeTime){
    		this.nextFreeTime = nextFreeTime;
    	}
    	@Override
    	public int compareTo(Object o){
    		int result = (int)(this.getNextFreeTime() - ((Worker)o).getNextFreeTime());
    		if(result ==0)
    			result = this.getId()-((Worker)o).getId();
    		return result;
    	}
    }
    
    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        long[] nextFreeTime = new long[numWorkers];
        /*for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            int bestWorker = 0;
            for (int j = 0; j < numWorkers; ++j) {
                if (nextFreeTime[j] < nextFreeTime[bestWorker])
                    bestWorker = j;
            }
            assignedWorker[i] = bestWorker;
            startTime[i] = nextFreeTime[bestWorker];
            nextFreeTime[bestWorker] += duration;
        }*/
        PriorityQueue<Worker> pq = new PriorityQueue<>();
        for(int i=0; i<numWorkers; i++){
        	pq.add(new Worker(i));
        }
        for (int i = 0; i < jobs.length; i++) {
        	int duration = jobs[i];
        	Worker temp = pq.poll();
        	assignedWorker[i] = temp.id;
        	startTime[i] = temp.nextFreeTime;
        	temp.nextFreeTime +=duration;
        	nextFreeTime[temp.id] = temp.nextFreeTime;
        	pq.add(temp);
        	
        	
        }
        
    }
    
    

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}
