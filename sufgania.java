package Algo;

public class sufgania {
    private static final int time = 2;

    /**
     * returns the total time for the daunts
     * Complexity: O(1)
     */
    public static int getTime(int numOfDonuts,int capacity) {
        if(capacity >= numOfDonuts) return time;
        if((time*numOfDonuts)%capacity == 0) return (time*numOfDonuts)/capacity;
        return (time*numOfDonuts)/capacity + 1;
    }

    public static void main(String[] args) {
        int res11=getTime(3,2);
        int res12=getTime(4,6);
        int res13=getTime(5,4);
        int res14=getTime(5,3);
        int res15=getTime(4,3);
        int res16=getTime(9,3);
        System.out.println(res11);
        System.out.println(res12);
        System.out.println(res13);
        System.out.println(res14);
        System.out.println(res15);
        System.out.println(res16);

    }
}

