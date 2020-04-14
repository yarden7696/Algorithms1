package Algo;
///////////////// full search iterative ////////////////

public class numberGameFull_Iterative {

	/**
	 * this algorithm return the max difference between the 2 players and the path 
	 * that every player choose in his way. 
	 * in this example place 3(choose player1)--->place 0(choose player2)--->
	 * place 1(choose player1)--->place 2(choose player2)
	 * sum player1=13 -------- sum player2=9 
	 *                13-9=4
	 * @param game
	 * @param start
	 * @param end
	 * @return
	 */
	
	public static void Iterative(int[] input) {// input is the arr of num that we get
		
		Node[] numberNodes = new Node[(int)Math.pow(2, input.length)-1]; // numbers of nodes in the tree
		numberNodes[0] = new Node(0, input.length-1);

		//create tree of indexes
		for (int i = 0; i < numberNodes.length/2; i++) {
			numberNodes[2*i+1] = new Node(numberNodes[i].leftIndex+1, numberNodes[i].rightIndex);
			numberNodes[2*i+2] = new Node(numberNodes[i].leftIndex, numberNodes[i].rightIndex-1);
		}

		//set value and path of leaves
		for (int i = numberNodes.length/2; i < numberNodes.length; i++)
		{
			numberNodes[i].value = input[numberNodes[i].leftIndex]; // game[i].leftIndex == game[i].rightIndex
			numberNodes[i].path =  numberNodes[i].rightIndex + "";
		}

		//set value and path of inner verteces 
		for (int i = numberNodes.length/2 - 1; i >= 0; i--) {
			if (input[numberNodes[i].leftIndex] - numberNodes[2*i+1].value >= input[numberNodes[i].rightIndex] - numberNodes[2*i+2].value)
			{
				numberNodes[i].value = input[numberNodes[i].leftIndex] - numberNodes[2*i+1].value;
				numberNodes[i].path = numberNodes[i].leftIndex + "->" + numberNodes[2*i+1].path;
			}
			else
			{
				numberNodes[i].value = input[numberNodes[i].rightIndex] - numberNodes[2*i+2].value;
				numberNodes[i].path = numberNodes[i].rightIndex + "->" + numberNodes[2*i+2].path;
			}
		}
		
		System.out.println("value = " + numberNodes[0].value);
		System.out.println("path = " + numberNodes[0].path);
	}
	static class Node{
		public int leftIndex;
		public int rightIndex;
		public int value; 
		public String path;
		
		public Node(int left, int right)
		{
			leftIndex = left;
			rightIndex = right;
			value = 0;
			path = "";
		}
	}
	
	public static void main(String[] args) {
		int[] input = {1,5,3,3,5,3};

		Iterative(input);
	}
}
