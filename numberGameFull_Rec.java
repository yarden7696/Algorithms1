package Algo;
///////////////// full search recursion ////////////////

public class numberGameFull_Rec {

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
	
		static Node recursive(Node[] game, int start, int end) {
		
			if (start != end)
			{
				Node left = recursive(game, start, end-1);
				Node right = recursive(game, start+1, end);
				if (game[start].num-right.num >= game[end].num-left.num){
					return new Node((game[start].num-right.num), (start+"->"+right.path));
				}
				else 
					
					return new Node(game[end].num-left.num, end + "->" + left.path);
				
			}
			game[start].path = start+"";
			return game[start];
		}
		
		static class Node
		{
			public int num;
			public String path;
			
			public Node(int num, String path)
			{
				this.num = num;
				this.path="";
				if (path != "")
					this.path = path;
			}
		}
	
	public static void main(String[] args) {
	
		int[] input = {5,9,1,6};
		//int[] input = {2,3,1,2,1,3};

		Node[] Game = new Node[input.length];
		for (int i = 0; i < Game.length; i++) {
			Game[i] = new Node(input[i],"");
		}
		Node ans = recursive(Game, 0, Game.length-1);
		System.out.println("value = " +  ans.num+"\npath = "+ans.path);

	}

}
