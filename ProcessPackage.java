import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

 class Request {
    public Request(int arrival_time, int process_time) {
        this.arrival_time = arrival_time;
        this.process_time = process_time;
    }

    public int arrival_time;
    public int process_time;
}

class Response {
    public Response(boolean dropped, int start_time) {
        this.dropped = dropped;
        this.start_time = start_time;
    }

    public boolean dropped;
    public int start_time;
}

class Buffer {
    public Buffer(int size) {
        this.size_ = size;
        this.finish_time_ = new ArrayList<Integer>(size);
    }
    
    public ArrayList<Integer> getFinishTime(){
    	return finish_time_;
    }

    public Response Process(Request request) {
        // write your code here
    	//List<Integer> ModFinishTime = new ArrayList<Integer>(finish_time_);
    	//Collections.sort(ModFinishTime);
    	//Deque<Integer> dq = new LinkedList<Integer>(ModFinishTime);
    	Deque<Integer> dq1 = new LinkedList<Integer>(finish_time_);
    	List<Integer> removal = new ArrayList<Integer>();
    	Iterator itr = finish_time_.iterator();
    	//removal.clear();
    	while(itr.hasNext()){
    		int a = (Integer)itr.next();
    		if( a <= request.arrival_time){
    			dq1.remove(a);
    			removal.add(a);
    		}
    		
    		    //finish_time_.remove(a);
    	}
    	for(int id=0;id<removal.size();id++){
    		finish_time_.remove(new Integer(removal.get(id)));
    	}
    	removal.clear();
    	if(dq1.size() < size_){
    		//int starttime = 0;
    		if(dq1.size()==0){
    			finish_time_.add(request.arrival_time+request.process_time);
    			return new Response(false,request.arrival_time);
    		}
    		else{
    			finish_time_.add(dq1.getLast()+request.process_time);
    			return new Response(false,dq1.getLast());
    		}
//    		dq.add(request.arrival_time+request.process_time);
//    		Collections.sort(finish_time_);
//    		return new Response(false,request.arrival_time+request.process_time);
    	}
    	else
    		return new Response(true, -1);
    }

    private int size_;
    private ArrayList<Integer> finish_time_;
}

public class ProcessPackage {
    private static ArrayList<Request> ReadQueries(Scanner scanner) throws IOException {
        int requests_count = scanner.nextInt();
        ArrayList<Request> requests = new ArrayList<Request>();
        for (int i = 0; i < requests_count; ++i) {
            int arrival_time = scanner.nextInt();
            int process_time = scanner.nextInt();
            requests.add(new Request(arrival_time, process_time));
        }
        return requests;
    }

    private static ArrayList<Response> ProcessRequests(ArrayList<Request> requests, Buffer buffer) {
        ArrayList<Response> responses = new ArrayList<Response>();
        for (int i = 0; i < requests.size(); ++i) {
            responses.add(buffer.Process(requests.get(i)));
//            for(int j=0; j <buffer.getFinishTime().size();j++)
//            	System.out.print(buffer.getFinishTime().get(j));
//            System.out.println(" Done ");
        }
        return responses;
    }

    private static void PrintResponses(ArrayList<Response> responses) {
        for (int i = 0; i < responses.size(); ++i) {
            Response response = responses.get(i);
            if (response.dropped) {
                System.out.println(-1);
            } else {
                System.out.println(response.start_time);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        int buffer_max_size = scanner.nextInt();
        Buffer buffer = new Buffer(buffer_max_size);

        ArrayList<Request> requests = ReadQueries(scanner);
        ArrayList<Response> responses = ProcessRequests(requests, buffer);
        PrintResponses(responses);
    }
}
