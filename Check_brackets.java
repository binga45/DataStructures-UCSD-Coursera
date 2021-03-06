import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

public class Check_brackets {
    public static void main(String[] args) throws IOException {
        InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        String text = reader.readLine();
        boolean found = false;
        int error_pos=0;

        Stack<Bracket> opening_brackets_stack = new Stack<Bracket>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);

            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
            	opening_brackets_stack.push(new Bracket(next,position));
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
            	
            	
            	if(opening_brackets_stack.isEmpty()){
            		System.out.println(position+1);
            		found = true;
            		break;
            	}
            	Bracket top = opening_brackets_stack.pop();
            	if(top.type == '(' || top.type == '[' || top.type == '{' ){
            		if(top.Match(next))
            		continue;
            		else{
            			System.out.println(position+1);
            			found = true;
            			break;
            		}
            	    
            	}
            	
            }
        }
        Bracket top = null;
        // Printing answer, write your code here
        if(opening_brackets_stack.isEmpty() && !found)
        System.out.println("Success");
        else{
        	if(!opening_brackets_stack.isEmpty())
        	top = opening_brackets_stack.pop();
        	if(found != true)
        	System.out.println(top.position+1);
        }
        
    }
}
