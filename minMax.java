package Algo;

public class minMax {

	public static void main(String[] args) {

		//create array
		int[] arr = GenerateArray(100);

		//find min / max
		System.out.println("Min = " + Min(arr));
		System.out.println("Max = " + Max(arr));

		//find minmax - algorithm1
		System.out.println("minMax1:");
		MinMax1(arr);

		System.out.println("minMax2:");
		//find minmax - algorithm2
		MinMax2(arr);

		System.out.println("minMax3:");
		//find minmax - algorithm3
		MinMax3(arr);

		System.out.println("minMax4:");
		//find minmax - algorithm4
		MinMax4(arr);

		System.out.println("minMax5:");
		//find minmax - algorithm5
		MinMax5(arr);

		System.out.println("Simulation for minMax3:");
		//calculate compares for algorithm3
		Simulation();


		SimulationForNormal();

		SimulationForImprove();

		SimulationForTheBest();
	}

	public static int Min(int[] arr) // n-1 comparisons
	{
		int min = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
		}
		return min;
	}

	public static int Max(int[] arr) // n-1 comparisons
	{
		int max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > max)
				max = arr[i];
		}
		return max;
	}

	private static int[] GenerateArray(int n) { // n is the length of the array
		int[] arr = new int[n];
		for (int i = 0; i < arr.length; i++) { // put random numbers in the array
			arr[i] = (int) (Math.random() * 1000);
		}
		return arr;
	}

	private static void MinMax1(int[] arr) { // 2n-2
		int min = Min(arr);
		int max = Max(arr);
		System.out.println("Min = " + min);
		System.out.println("Max = " + max);
	}

	private static void MinMax2(int[] arr) { // 2n-2
		int min, max;
		min = max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
			if (arr[i] > max)
				max = arr[i];
		}
		System.out.println("Min = " + min);
		System.out.println("Max = " + max);
	}

	private static void MinMax3(int[] arr) { //check number of compares in a simulation
		int min, max;
		min = max = arr[0];
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
			else if (arr[i] > max)
				max = arr[i];
		}
		System.out.println("Min = " + min);
		System.out.println("Max = " + max);
	}

	private static void MinMax4(int[] arr) { //2n-3
		int min, max;
		if (arr[0] < arr[1]) {
			min = arr[0];
			max = arr[1];
		} else {
			max = arr[0];
			min = arr[1];
		}
		for (int i = 2; i < arr.length; i++) {
			if (arr[i] < min)
				min = arr[i];
			if (arr[i] > max)
				max = arr[i];
		}
		System.out.println("Min = " + min);
		System.out.println("Max = " + max);

	}

	private static void MinMax5(int[] arr) { // 3n/2 - 2
		int min, max;
		if (arr[0] < arr[1]) {
			min = arr[0];
			max = arr[1];
		} else {
			max = arr[0];
			min = arr[1];
		}
		for (int i = 2; i < arr.length - 1; i = i + 2) {
			if (arr[i] < arr[i + 1]) {
				if (arr[i] < min)
					min = arr[i];
				if (arr[i + 1] > max)
					max = arr[i + 1];
			} else {
				if (arr[i] > max)
					max = arr[i];
				if (arr[i + 1] < min)
					min = arr[i + 1];
			}
		}
		if (arr.length % 2 == 1) // size of the array is odd
		{
			int last = arr[arr.length - 1];
			if (last < min)
				min = last;
			else if (last > max)
				max = last;
		}
		System.out.println("Min = " + min);
		System.out.println("Max = " + max);


	}

	private static void SimulationForImprove() {

		int[] mat = {5, 11, 1, 9, -2,4,9,23,5,0,11,35,76,-7,12,55};
		int min=mat[0];
		int max=mat[0];
		int counter = 0;
		for (int i = 1; i < mat.length; i++) {
			counter++;
			if (mat[i] < min)
				min = mat[i];
			else {
				counter++;
				if (mat[i] > max)
					max = mat[i];
			}
		}
		System.out.println("Average Counter improve = " + counter );
		}



	private static void SimulationForNormal() {

		int[] mat = {5, 11, 1, 9, -2, 4, 9, 23, 5, 0, 11, 35, 76, -7, 12, 55};
		int min = mat[0];
		int max = mat[0];
		int counter = 0;
		for (int i = 1; i < mat.length; i++) {
			counter++;
			if (mat[i] < min)
				min = mat[i];

			counter++;
			if (mat[i] > max)
				max = mat[i];
		}

		System.out.println("Average Counter normal = " + counter);
	}

	private static void SimulationForTheBest() {
		int[] mat = {5, 11, 1, 9, -2, 4, 9, 23, 5, 0, 11, 35, 76, -7, 12, 55};
		minMax(mat);

	}

	private static void Simulation() {
		int numOfTests = 1000;
		int[] arr;
		int min, max;
		int counter = 0;

		for (int j = 0; j < numOfTests; j++) {

			arr = GenerateArray(1000);
			min = max = arr[0];
			for (int i = 1; i < arr.length; i++) {
				counter++;
				if (arr[i] < min)
					min = arr[i];
				else {
					counter++;
					if (arr[i] > max)
						max = arr[i];
				}
			}
		}
		System.out.println("Average Counter = " + counter / numOfTests);

	}






	public static void minMax(int[] arr) { // (3n/2)-2

		int comparisons = 1;
		int min = arr[0];
		int max = arr[1];
		if (arr[0] > arr[1]) {
			max = arr[0];
			min = arr[1];
		}

		for (int i = 2; i < arr.length-1; i = i + 2) { //jump with i=i+2
			comparisons++;
			if (arr[i] > arr[i + 1]) // arr[i] is bigger than arr[i+1]
			{
				comparisons = comparisons + 2;
				if (arr[i] > max) {
					max = arr[i];
				}
				if (arr[i + 1] < min) {
					min = arr[i + 1];
				}
			} else // arr[i]<= arr[i+1]
			{
				comparisons = comparisons + 2;
				if (arr[i] < min) {
					min = arr[i];
				}
				if (arr[i + 1] > max) {
					max = arr[i + 1];
				}
			}
		}
		if (arr.length % 2 != 0) // the array is odd and we do not check the last element in the array
		{
			comparisons++;
			if (arr[arr.length - 1] > max) {
				max = arr[arr.length - 1];
			} else {
				comparisons++;
				if(arr[arr.length-1]<min)
				min = arr[arr.length - 1];
			}
		}
		System.out.println("the max is : " + max + "\n the min is : " + min +"\n the number of comparisons is : " + comparisons);

	}
}

//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//		int[] mat = {5, 11, 1, 9, -2};
//
//		minMax(mat);
//	}
//
//}
//




