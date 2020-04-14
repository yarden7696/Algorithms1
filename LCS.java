package Algo;

import java.util.HashSet;
import java.util.Set;

public class LCS {
//
//	////////////////////////greedyLCS/////////////////////
//
//	public static String greedyLCS(String x, String y) // o(lengthX*lengthY)
//	{
//		String ans = "";
//		int lengthX = x.length();
//		int lengthY = y.length();
//		int i = 0, index = 0, start = 0;
//		while (i < lengthX && index < lengthY) {
//			index = y.indexOf(x.charAt(i), start); //Search throughout the String y the character found at the i
//			//position in x. The search in y starts from start. If found returns the index, if it does not return -1
//
//			if (index != -1) {
//				ans = ans + x.charAt(i);
//				start = index + 1;
//			}
//			i++;
//		}
//		return ans;
//	}
//
//	////////////////////////improveGreedyLCS/////////////////////
//
//	public static String improveGreedyLCS(String x, String y)//o(lengthX+lengthY)+
//	//o(min(lengthX,lengthY))
//	{
//		String ans = "";
//		int lengthX = x.length();
//		int lengthY = y.length();
//		int i, index = 0, start = 0, place;
//		int help[] = new int[26];
//		if (lengthX < lengthY) {
//			for (i = 0; i < lengthX; i++) // in this array there is the count off each char in x
//			{
//				place = x.charAt(i) - 'a';
//				help[place]++;
//			}
//			while (i < lengthY && start < lengthX)//Run on a string of y because the goal is
//			//to look for the letters of y that do not appear in x
//
//			{
//				place = y.charAt(i) - 'a';
//				if (help[place] > 0) // if its true its mean that there is common letter
//				{
//					index = x.indexOf(y.charAt(i), start);
//					ans = ans + y.charAt(i);
//					start = index + 1;
//					help[place]--;
//				} else {
//					help[place] = 0;
//				}
//				i++;
//			}
//			return ans;
//		} else {
//
//			for (i = 0; i < lengthY; i++) // in this array there is the count off each char in x
//			{
//				place = y.charAt(i) - 'a';
//				help[place]++;
//			}
//			while (i < lengthX && start < lengthY)//Run on a string of y because the goal is
//			//to look for the letters of y that do not appear in x
//
//			{
//				place = x.charAt(i) - 'a';
//				if (help[place] > 0) // if its true its mean that there is common letter
//				{
//					index = y.indexOf(x.charAt(i), start);
//					ans = ans + x.charAt(i);
//					start = index + 1;
//					help[place]--;
//				} else {
//					help[place] = 0;
//				}
//				i++;
//			}
//			return ans;
//
//		}
//	}
//
//	////////////////////////LCS_dynamic///////////////////// o(lengthX*lengthY)
//
//	// this function returns the length of the common longest String
//	public static int[][] LCS_dynamic(String x, String y) {
//		int lengthX = x.length() + 1;
//		int lengthY = y.length() + 1;
//		int mat[][] = new int[lengthX][lengthY];
//		for (int i = 1; i < lengthX; i++) //fill the mat
//		{
//			for (int j = 1; j < lengthY; j++) {
//				if (x.charAt(i - 1) == y.charAt(j - 1)) {
//					mat[i][j] = mat[i - 1][j - 1] + 1; //Add 1 to the diagonal
//				} else // if the chars are different
//				{
//					mat[i][j] = Math.max(mat[i][j - 1], mat[i - 1][j]);//take the maximum between
//					// the two diagonals (pinks)
//				}
//			}
//		}
//		return mat;
//	}
//
//	// this function returns the String of the common longest String
//	public static String LCS_string_dynamic(String x, String y) {
//		int[][] mat = LCS_dynamic(x, y);
//		int i = x.length();
//		int j = y.length();
//		String ans = "";
//		while (i > 0 && j > 0) {
//			if (x.charAt(i - 1) == y.charAt(j - 1)) {//If the chars are equal we take the equal char and enter it to the answer
//				//x.charAt(lengthX-1) ---> its the last letter,caz we start from 0
//				ans = x.charAt(i - 1) + ans;
//				i--; //Go from bottom to top in the matrix
//				j--; //Go from bottom to top in the matrix
//			} else //if the chars are different take the maximum between the two diagonals(pinks)
//			{
//				if (mat[i - 1][j] > mat[i][j - 1]) {
//					i--;
//				} else {
//					j--;
//				}
//			}
//		}
//		return ans;
//	}
//
//
//	public static void main(String[] args) {
//
//		System.out.println(" Regular greedy - " + greedyLCS("cbab", "ababcb"));
//		System.out.println(" improve greedy - " + improveGreedyLCS("cbab", "ababcb"));
//		System.out.println(" Dynamic algo - " + LCS_string_dynamic("cbab", "ababcb"));
//		System.out.println(" Dynamic algo - " + LCS_string_dynamic("cdji", "abcdefghij"));
//		int mat[][]= LCS_dynamic("cbab", "ababcb");
//	    int lx= mat.length;
//	    int ly= mat[0].length;
//	    int length = mat[lx-1][ly-1];
//		System.out.println(" the length of ( cbab , ababcb ) : " + length );
//
//	}
//}


	public static void main(String[] args) {
		String X = "ardyar";
		String Y = "yarden";

		//another example: x = abcabcaa, y = acbacba

		//calculate matrix
		int[][] Mat = LCS(X, Y);

		//print matrix after calculate
		Print(Mat);

		//print the LCS
		System.out.println("Longest Commom Subsequence = " + Mat[X.length()][Y.length()]);

		//print example of subsequence
		String subsequence = GetSubsequence(X, Y, Mat);
		System.out.println("Example of Common Subsequence = " + subsequence);

		//Get all subsequences
		int startI = X.length();
		int startJ = Y.length();
		subsequence = "";
		Set<String> allSubSequences = new HashSet<String>();//there can be duplicated so Set is chosen

		//calculate all subsequences
		GetAllSubSequences(X, Y, Mat, startI, startJ, subsequence, allSubSequences);

		//print all subsequences
		System.out.println(allSubSequences);
	}


	private static int[][] LCS(String x, String y) {
		int[][] Mat = new int[x.length() + 1][y.length() + 1];
		for (int i = 1; i <= x.length(); i++) {
			for (int j = 1; j <= y.length(); j++) {
				if (x.charAt(i - 1) == y.charAt(j - 1)) {
					Mat[i][j] = Mat[i - 1][j - 1] + 1;
				} else
					Mat[i][j] = Math.max(Mat[i][j - 1], Mat[i - 1][j]);
			}
		}
		return Mat;
	}

	private static String GetSubsequence(String x, String y, int[][] mat) {
		int i = x.length();
		int j = y.length();
		String ans = "";
		while (i > 0 && j > 0) {
			char x1 = x.charAt(i - 1);
			char y1 = y.charAt(j - 1);
			if (x.charAt(i - 1) == y.charAt(j - 1)) {
				ans = x.charAt(i - 1) + ans;
				i = i - 1;
				j = j - 1;
			} else {
				if (mat[i - 1][j] > mat[i][j - 1])
					i--;
				else
					j--;
			}
		}
		return ans;
	}

	private static void Print(int[][] mat) {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat[i].length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}
	}

	private static void GetAllSubSequences(String x, String y, int[][] mat, int i, int j, String ans, Set<String> allSubSequences) {

		while (i > 0 && j > 0) {
			if (x.charAt(i - 1) == y.charAt(j - 1)) {
				ans = x.charAt(i - 1) + ans;
				i--;
				j--;
			} else // chars are different, we need to check who is the bigger
			{
				if (mat[i - 1][j] > mat[i][j - 1]) {
					i--;
				} // there is big
				else if (mat[i - 1][j] < mat[i][j - 1]) {
					j--;
				} // there is big
				else // there is no big caz the numbers are equals
				{
					GetAllSubSequences(x, y, mat, i - 1, j, ans, allSubSequences);
					GetAllSubSequences(x, y, mat, i, j - 1, ans, allSubSequences);
					return;
				}
			}
		}
		allSubSequences.add(ans);

	}
}



