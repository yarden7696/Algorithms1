package Algo;

public class numbersGame_dynamicAlgorithem {

	/**
	 * Game Numbers - dynamic algorithm
	 * Complexity: O(n^2)
	 */
	public static class GameNumbersDynamic {
		private int[][] mat;
		private int n;
		
		public GameNumbersDynamic(int[] arr)
		{
			buildMatrix(arr);
		}
		// build matrix and initialize the max value in every ריבוע
		public void buildMatrix(int[] arr) { // a is the array of the numbers
			n = arr.length;
			mat = new int[n][n];
			for (int i = 0; i < n; i++) { 
				mat[i][i] = arr[i]; // put the numbers of the array at diagonal matrix
			}

			for (int i = n-2; i >=0 ; i--) {  // n-2 its the line before the end and its runs from bottom to up.  
				for (int j = i+1; j < n; j++) {
					mat[i][j] = Math.max(arr[i] - mat[i+1][j], arr[j] - mat[i][j-1]);
				}// הסבר לשורה מעל- כל פעם נעשה הפרש בין האיבר הi במטריצה arr לבין הi+1,j במטריצה mat
				// כך נעשה גם לj ובעצם נקבל את ההפרש בין שני האדומים במטריצה שבעמוד 15. כל פעם נעשה הפרש בין שניהם בצורה הפוכה
			}
		}
		
		/**
		 * Returns the difference between the first player and the second player
		 * if both playing optimal
		 */
		public int getDifference() {
			return mat[0][mat[0].length-1];
		}
		
		public String getOptimalPathRec() {
			return getOptimalPath(0,n-1);
		}

		/**
		 * Returns the game's optimal path for both players
		 */
		private String getOptimalPath(int i, int j) {
			if(i == j) return "("+i+")"+ mat[i][i];
			if(mat[i][i] - mat[i+1][j] > mat[j][j] - mat[i][j-1]) {
				return "("+i+")"+mat[i][i] + "->" + getOptimalPath(i+1,j);
			}
			else {
				return "("+j+")"+mat[j][j] + "->" + getOptimalPath(i,j-1);
			}
		}


	public static void main(String[] args) {
	int [] arr= {1,3,6,1,3,6};



		System.out.println(new GameNumbersDynamic(new int[] {1,5,3,3,5,3}).getOptimalPathRec());
		System.out.println(new GameNumbersDynamic(new int[] {1,5,3,3,5,3}).getDifference());
		}
}

}





