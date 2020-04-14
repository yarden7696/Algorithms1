package Algo;

public class TESTS_answers {

    public static int majority (int [] arr){

        int [] help= new int [arr.length];
        help[0]=1;
        int max= arr[0];
        int index=0;

        for (int i=1; i<arr.length; i++){
            for (int j=i; j>=0; j--){
                if(arr[i]==arr[j])
                {
                    help[i]=help[i]+1;
                }
                else if(help[i]==0) help[i]=1;
                else help[i]=help[i]+0;

                if (help[i]> max)
                {
                    max=help[i];
                    index=i;
                }
            }
        }
        if (max > (arr.length/2)) return arr[index];
        else return Integer.MAX_VALUE;
    }

    public static void main (String args[]){

        int [] mat = {3,4,2,4,2,4,4,4,4,4,4,4,4,4,5,4,8,6,2,0,8,6};
        int ans= majority(mat);
        System.out.println(" the majority is : " + ans);


    }
}
