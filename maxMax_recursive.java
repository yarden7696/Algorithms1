package Algo;

public class maxMax_recursive {

public static void maxMax_recur ( int [] arr) //********** recursive method ***********
{
	Node[] nodes= new Node[arr.length];
	for(int i=0; i<nodes.length; i++) // initialize the array(of nodes) with the elements. every node has data and empty stack(at begin)
	{
		nodes[i]= new Node(arr[i]);
	}
	
	Node max1= maxMax (nodes, 0, nodes.length-1); // this is the bigger element at array
	// now we check who is the second element from max1's stack
	int max2= max1.st.pop(); // we take the first element from max1's stack
	while(!max1.st.isEmpty())
	{
		int max3= max1.st.pop(); // we take the "second" element from max'1 stack
		if(max3> max2)
		{
			max2=max3;
		}
	}
	System.out.println(" max1 is: " + max1.data + " \n " + "max2 is : " + max2);
}


	private static Node maxMax (Node[]nodes, int low, int higth)
	{
		if( low==higth ) return nodes[low];
		int mid= (low+higth)/2;
		Node maxL= maxMax(nodes, low, mid);
		Node maxR= maxMax (nodes, mid+1, higth);
		if (maxL.data > maxR.data)
		{
			maxL.st.push(maxR.data);
			return maxL;
		}
		else // maxR.data>= maxL.data
		{
			maxR.st.push(maxL.data);
			return maxR;
		}
	}
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		maxMax_recur (new int [] {8,9,2,1,4,3});
		
	}
}
