package Algo;

import java.util.Arrays;

public class LIS {

	/** /////////////////////my explain in page 26//////////////////////
	 * @param arr is the original array
	 * @return the length of the largest increment subsequence
	 * /////////complexity o(n*log(n))/////////////
	 */
	public static int LIS_length(int [] arr){
		int [] t= new int [arr.length];
		t[0]= arr[0];
		int lis=1;
		for (int i=1; i<arr.length; i++){
			int index= binarySearchBetween_length(t,arr[i],lis);
			t[index]= arr[i];
			if(index> lis) lis++;
		}
		return lis;
	}

	private static int binarySearchBetween_length(int t[], int a, int lis) {
		int start = 0;
		// tow rules
		if (a < t[0]) return 0;
		if (a > t[lis]) return lis + 1;

		while (start <= lis) {
			int mid = (start + lis) / 2;
			if (start == lis) return start; // stop conditional
			else if (a == t[mid]) return mid;
			else if (a < t[mid]) {	lis = mid; }
			else { start = mid + 1; }
		}
		return -1;
	}

		/**
		 * Step 4.2: Get the largest increment subsequence
		 * Complexity: O(n*log(n)+n^2)=O(n^2)
		 * @param arr - the original array
		 * @return the array of the largest increment subsequence
		 */
		public static int[] LIS2(int [] arr){ // return the array of elements

			int size = arr.length;
			int mat [][] = new int[size][size];
			mat [0][0] = arr[0]; // put the first element in the mat
			int lis = 0; // lis is the *index* of prev element

			for (int i=1; i<size; i++){ // running on arr's size
				int index = binarySearchBetween(mat, lis, arr[i]);

				for(int j=0; j<index; j++){
					mat[index][j]=mat[index-1][j];
				}
				mat[index][index] = arr[i];
				if (index>lis) lis++;
			}

			int ans[] = new int[lis+1];
			for(int i=0; i<=lis; i++) {
				ans[i]=mat[lis][i];
			}
			return ans;
		}
		/**
		 * Help Function for LIS2:
		 * Binary search between on the diagonal of a matrix
		 * Complexity: O(logn)
		 * @param mat - a matrix for search
		 * @param lis - a last index for search
		 * @param a - a value for search
		 * @return
		 */
		public static int binarySearchBetween(int [][]mat, int lis, int a){
			/**
			 if value<arr[0] the function returns zero
			 if value>arr[end] the function returns  end+1
			 if arr[index-1] < value < arr[index]
			 the function returns index,
			 */
			int start = 0;

			if (a<mat[0][0]) return 0;
			if (a>mat[lis][lis])  return lis+1;

			while (start <=lis){
				int mid = (start + lis)/2;
				if (start == lis) { return start; }
				else if(mat[mid][mid] == a) { return mid; }
				else if (a < mat[mid][mid]) { lis = mid; }
				else { start = mid+1; }
			}
			return -1;
		}

	public static int LIS_length11(int [] a, int [] b) {
		int[] t = new int[a.length];
		int lis = 0;
		if (b[0] == 1) {
			t[0] = a[0];
			lis = 1;
		}
		for (int i = 1; i < a.length; i++) {
			if (b[i] == 1) {
				int index = binarySearchBetween_length(t, a[i], lis);
				t[index] = a[i];
				if (index > lis) lis++;
			}
		}
		return lis;
	}


		public static void main(String[] args) {
			int[]arr = {8,4,12,2,3,10,14};
			int [] ans1= LIS2(arr);
			System.out.println("Longest increasing subsequence: " + Arrays.toString(ans1));

			int ans=LIS_length(arr);
			System.out.println(" the length of Longest increasing subsequence is : " + ans);


			int a[]= {0,5,8,3,11,7,9,61};
			int b[]= {1,1,1,1,0,0,1,1};
			int ansTest=LIS_length11(a,b);
			System.out.println(" the length of Longest increasing subsequence with one is : " + ansTest);



		}
}


