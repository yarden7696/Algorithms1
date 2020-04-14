package Algo;

public class pizza {
//need to kearn the proof i wrote at the withe pages
    /**
     * returns the optimal division for the faster man
     * Complexity: O(1)
     */
    public static int getNumberOfPieces(double k) {
        if (k == (int) k) return (int) k + 1; // k is integer
        return (int) k + 2;
    }
            //not working
//    public static int pizza(double x, int n){
//        int k= (int)x+1;
//        int p = n/k+1; // k+1 division of n
//        int ans=-1;
//        int r= n%(k+1); // r=1 forbidden state caz The remainder should be bigger from 1
//        if(r!=1)
//        {
//            double t= (x*p+r-1)/((x+1)*p+r);
//            if(t<x/x+1) {
//                ans = 1; // נוסחא נכונה
//            }
//            else { ans=0;} // נוסחא לא נכונה
//        }
//        return ans;
//    }


    public static void main(String[] args) {
       double ans= getNumberOfPieces(4.4);
        System.out.println(ans);
//        double res= pizza(54.5,3);
//        System.out.println(res);


    }
}


