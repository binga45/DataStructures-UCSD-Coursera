import java.util.*;
import java.io.*;

public class Partition3 {
    private static int partition3(int[] A) {
        //write your code here
    	int sum = 0;
    	for (int i = 0; i < A.length; i++) 
            sum += A[i]; 
  
        if (sum%3 != 0) 
            return 0; 
        
        int weight[][] = new int[sum/3+1][A.length+1];
        
     // initialize top row as true 
        for (int i = 0; i <= A.length; i++) 
        	weight[0][i] = 0; 
  
        // initialize leftmost column, except part[0][0], as 0 
        for (int i = 1; i <= sum/3; i++) 
        	weight[i][0] = 0; 
        //The logic of Discrete Knapsack with no repetitions
        int count = 0;
        for(int i=1; i<=sum/3; i++){
        	for(int j=1;j<=A.length;j++){
        		weight[i][j] = weight[i][j-1];
        		int weight1 = 0;
        		if(A[j-1]<=i)
        			weight1 = weight[i-A[j-1]][j] + A[j-1];
        		weight[i][j] = Math.max(weight1, weight[i][j]);
        		if(weight[i][j] == sum/3)
        			count++;
        		
        	}
        }
        if(count < 3)
        	return 0;
        else
        	return 1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = scanner.nextInt();
        }
        System.out.println(partition3(A));
    }
}

