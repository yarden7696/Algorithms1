package Algo;

                       //////////////// learn the proof on page 32-33/////////////////
     class Power {

         public static double powerLoop(double x, int n){

             double res=1;
             while (n!=0)
             {
                if(n%2==1) {res = res*x;} //mull res only if power is odd
                x= x*x; // do this anyway
                n=n/2; // do this anyway
             }
                      return res;
         }


         public static double powerRec(double x, int n){

             if(n==0) return 1;
             if(n%2==0) return powerRec(x*x ,n/2);
             else { return x * powerRec(x*x, (n-1)/2);}

         }

         public static void main(String[] args) {

             double ans1= powerLoop(2,6);
             System.out.println(ans1);
             double ans2= powerRec(2,4);
             System.out.println(ans2);

         }
}