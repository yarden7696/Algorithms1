package Algo;

 class Fibonacci {

     //learn proof on page 33
    private static final int[][] F = {{1,1},{1,0}};

    /**
     * This function calculates a value of returns the n'th element in fibonacci series
     * It's an iterative solution
     * Complexity: O(log n)
     * @param n n'th element in fibonacci series
     * @return value of n'th element in fibonacci series
     */
    public static int fibLoop(int n) { // we wont to return f(n)
        int[][] ans = {{1,1},{1,0}};
        int[][] A = {{1,1},{1,0}};
        while(n != 0) {
            if(n % 2 == 1) ans = mulMat(ans,A);
            A = mulMat(A,A);
            n /=2;
        }
        return ans[1][1];

    }

    /**
     * This function calculates a multiple two matrix 2x2
     * Complexity: O(1)
     * @param m1 - first matrix
     * @param m2 - second matrix
     * @return matrix of multiple two matrix 2x2
     */
    public static int[][] mulMat(int[][] m1, int[][] m2) {
        int[][] ans = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                ans[i][j] = m1[i][0]*m2[0][j] + m1[i][1]*m2[1][j];

            }
        }
        return ans;
    }

    /**
     * This function calculates a value of returns the n-th element in fibonacci series
     * It's a recursion solution
     * Complexity: O(log n)
     * @param n n-th element in fibonacci series
     * @return value of n-th element in fibonacci series
     */
    public static int fibRecursion(int n) {
        return fibRecursion1(F,n)[1][1];
    }

    public static int[][] fibRecursion1(int[][] A,int n) {
        if(n == 0) return F;
        if(n % 2 == 1) return mulMat(fibRecursion1(mulMat(A,A),(n-1)/2),A);
        return fibRecursion1(mulMat(A,A),n/2);
    }


    public static void main(String[] args) {
        long  res = 0, n = 3;

        long begTime = System.nanoTime();
        for(int i=1; i<=n; i++)
            res = fibRecursion(i);
        System.out.println("By Recursion fib number at " + n +
                "th element is " + res);
        long endTime = System.nanoTime();
        System.out.println("Recursion time: " + (endTime - begTime)  + " nsec");
        System.out.println();

        begTime = System.nanoTime();
        for(int i=1; i<=n; i++)
            fibLoop(i);
        System.out.println("By Iteration fib number at " + n +
                "th element is " + res);
        endTime = System.nanoTime();
        System.out.println("Iteration time: " + (endTime - begTime)  + " nsec");
    }
}
