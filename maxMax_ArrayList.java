package Algo;

import java.util.ArrayList;


public class 	maxMax_ArrayList {
	
    public static void maxMax_List (int [] arr) // *********induction method*********
    {
    	ArrayList<Node> nodes= new ArrayList<Node>();
    	for( int i=0; i<arr.length; i++)  // initialize the ArrayList with the elements. each element has data and empty stack(at begin)
    	{
    		nodes.add(new Node(arr[i]));
    	}
		int j=0;
    	while(nodes.size()>1)
    	{
    		int size= nodes.size();
    		int x= nodes.get(j%size).data;
    		int y= nodes.get((j+1)%size).data;
    		if(x>y)
    		{
    			nodes.get(j%size).st.push(y); // y enter to x's stack
    			nodes.remove((j+1)%size); // remove y from the ArrayList. because we will add 1 to j (we wont j to be the element after y
    		}
    		else // y>x
    		{
    			nodes.get((j+1)%size).st.push(x);
    			nodes.remove(j%size);
    		}
    		j++;
    	}
	    
    	// we found the bigger element, now we wont to find the second element from the stack's big max
    	
    	int max1= nodes.get(0).data; // this is the biggest element
    	int max2= nodes.get(0).st.pop(); // we take the first element from max1's stack and we compare it to max1 
    	
    	while(!nodes.get(0).st.isEmpty()) // while the stack is full and not empty
    	{
    		int max3= nodes.get(0).st.pop(); // "second" element from max1's stack  
    		if( max3> max2)
    		{
    			max2=max3;
    		}
    	}
	   System.out.println(" max1 is : " + max1 + "\n" + " max2 is :" + max2);
	
    
    }


             public static void main(String[] args) {
	          // TODO Auto-generated method stub
            	 
            	 maxMax_List( new int [] {7,4,2,1,12,5,17,3});
            	 
            	 
            	 
             } 
}
            	 
	
