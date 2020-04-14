package Algo;

import java.util.Arrays;

public class SecretaryProblem {

    public static double Secretary (double[] turnsTime){

        Arrays.sort(turnsTime); //sort the array from low time to high
        double sum= 0;
        double sumAns=0;
        for(int i=0; i<turnsTime.length; i++) {
            sum = sum + turnsTime[i]; // adding the previous time to the next time
            sumAns= sum+sumAns;
        }
        return sumAns/turnsTime.length; // return the average time for each client
        }

    public static void main(String[] args) {
        double arr []= {10,1,8};
        double ans=Secretary(arr);
        System.out.println(ans);
    }


    }


