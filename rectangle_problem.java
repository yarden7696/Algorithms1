package Algo;

import java.util.Stack;

public class rectangle_problem {

    ///////////////////o(m*n)////////////////
    public static int maxHistogram(int[] arr) {

        Stack<Integer> stacky = new Stack<Integer>();
        int max = 0, maxArea = 0;
        int i = 0, top;
        while (i < arr.length) // running the length of the array that we got
        {
            if (stacky.isEmpty() || arr[stacky.peek()] < arr[i]) // if the stack is empty || the value in this index is bigger
            {                                                  // than the value in the last index that in the stack
                stacky.push(i++); // if one of these conditional are existing, then we Promote the index in 1.
            } else // probably we got a value that it smaller than the last value(it place in the last index that in the stack)
            {
                 top = stacky.pop();
                if (stacky.isEmpty()) {
                    max = i * arr[top];
                } else {
                    max = arr[top] * (i - 1 - stacky.peek());
                }
                if (max > maxArea) maxArea = max;
            }
        }

        while (!stacky.isEmpty()) // as long as the stack if full
        {
           top = stacky.pop();
            if (stacky.isEmpty()) {
                max = arr[top] * i;
            } else {
                max = arr[top] * (i - 1 - stacky.peek());
            }
            if (max > maxArea) {
                maxArea = max;
            }
        }
        return maxArea;
    }

    public static int maxAreaRectangle(int[][] mat, int col) {
        int maxAns, max, count=0;
        int[] arr;
        arr = buildArr(0, mat, col);
        maxAns = maxHistogram(arr);

        for (int i = 1; i < mat.length; i++) {
            for (int j = 0; j < col; j++) {

                if (mat[i][j] == 1) {
                    arr[j] = arr[j] + 1;
                } else {
                    arr[j] = 0;
                }
                max = maxHistogram(arr);

                if (max==maxAns) count++;

               else if (max > maxAns)
                {
                    maxAns = max;
                    count=0;
                    count++;
                }

            }
        }
        System.out.println("the count is : " + count);
            return maxAns;
        }


        public static int[] buildArr ( int index, int[][] mat, int col){

            int arr[] = new int[col];

            for (int j = 0; j < col; j++) {
                arr[j] = mat[index][j];
            }
            return arr;
        }


    /**
     * Number of rectangles in ones-zeros matrix
     * Complexity: O(n*m)
     */
    public static int numOfRectangles(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(mat[i][j] == 1) {
                    if((i == 0 || mat[i-1][j] == 0) && (j == 0 || mat[i][j-1] == 0)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }


    public static void main (String args[]){
            int input[][] = {{1, 0, 0, 1, 1,1},
                             {1, 0, 1, 1, 0,1},
                             {0, 1, 1, 1 ,1,1},
                             {0, 0, 1, 1 ,1,1}};


            int ans = maxAreaRectangle(input, 6);
            System.out.println("the biggest area is : " + ans);

            int ans1= numOfRectangles(input);
            System.out.println( " num of rectangles is : " + ans1);
        }
    }

