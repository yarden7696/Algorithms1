package Algo;

import java.util.Arrays;

public class parkingProblem_array {

	
	public static int parkingProblem (int [] arr, int start) {
		
		int ind, count=0, backward=0, size= arr.length;
		int w=-1, v=0;
		ind= start;
		boolean flag= true;
		while(flag)
		{
			count++;
			ind= (ind+1)%size;
			if(arr[ind]== v) 
			{
				arr[ind]=w;
				backward= ind-count;
				if(backward<0) backward= size+backward;
			    if(arr[ind]==w) flag=false;	
			}
	       }
	    
		       return count;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		int size = 10;
		int arr[] = new int[size];
		for (int i=0; i<size; i++){
			arr[i] = (int)(Math.random()*size);
		}
		System.out.println(Arrays.toString(arr));
		int start=(int)(Math.random()*size); 
		System.out.println("start: "+start);
		int count = parkingProblem(arr, start);
		System.out.println("count = "+count);
		System.out.println(Arrays.toString(arr));
	}

}
