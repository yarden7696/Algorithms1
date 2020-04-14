package Algo;

public class prisoners {

    private static final int NumOfPrisoners = 100; // 0...99
    private static final int counterMan = 0;

    /**
     * prison problem when the initial state of light is known (on)
     * Complexity: O(?)
     */
    public static void prisonProblem() {
        boolean light = true;
        boolean firstTime = true;
        boolean[] enter = new boolean[NumOfPrisoners];
        int count = 0;
        int steps = 0;
        while(count < NumOfPrisoners) {
            steps++;
            int turnToEnter = (int)(Math.random()*NumOfPrisoners);
            if(turnToEnter == counterMan) {
                if(firstTime) {
                    enter[counterMan] = true;
                    count++;
                    firstTime = false;
                }
                if(!light) {
                    light = true;
                    count++;
                }
            }
            else {
                if(light && !enter[turnToEnter]) {
                    light = false;
                    enter[turnToEnter] = true;
                }
            }
        }
        for (int i = 0; i < enter.length; i++) {
            if(!enter[i]) {
                System.out.println("Fail!");
                return;
            }
        }
        System.out.println("We are free!");
        System.out.println("Number of steps = " + steps);
    }

    /**
     * prison problem when the initial state of light is Unknown
     */
    public static void prisonProblemUnknownState() {
        boolean light = ((int)(Math.random() * 2) == 0) ? false : true;
        boolean firstTime = true;
        int[] enter = new int[NumOfPrisoners];
        int countPrisoners = 0;
        int steps = 0;
        while (countPrisoners < 2 * NumOfPrisoners) {
            steps++;
            int p = (int) (Math.random() * NumOfPrisoners);
            if (p == 0) {
                enter[0]++;
                if (firstTime) {
                    countPrisoners += 2;
                    firstTime = false;
                }
                if (light) {
                    light = false;
                    countPrisoners++;
                }
            } else {
                if (!light && enter[p] < 2) {
                    light = true;
                    enter[p]++;
                }
            }
        }
        for (int i = 0; i < enter.length; i++) {
            if (enter[i] == 0) {
                System.out.println("Fail!");
                return;
            }
        }
        System.out.println("We are free!");
        System.out.println("Number of steps = " + steps);

    }


    public static int lightOFF(){
        int n=100;
        boolean light= false;
        int prisonersCounter=0, enterCounter=0;
        boolean isVisit[] = new boolean[n];
        for(int i=0; i<isVisit.length; i++){
            isVisit[i]=false; //set the array with false caz no one yes enter to thr room.
        }
        while (prisonersCounter<100) {// we need 100 for the counter caz the light is off so we need to turn it
                                     // on first by the charge prisoner. we add 1 every time that the charge turn on
                                    // the light , and this time the charge add another 1 caz the light was of.
            int p=(int)(Math.random()*100);

            if(p==0)
            {
                if(isVisit[0]==false && !light) // of the light is off and its his first enter
                {
                    light=true;
                    prisonersCounter++;
                    isVisit[p]=true;
                }
                else if(!light)
                {
                    light=true;
                    prisonersCounter++;
                }
            }
            else if(isVisit[p]==false && light==true){// if its normal prisoner and its his first time so he will enter
                                                    //to the room and turn off the light
               light=false;
               isVisit[p]=true;
            }
            enterCounter++;
            }
        return prisonersCounter;
        }










        //or algo
        public static int prisonersLigthON(){ // if the light is on its enough 99 for prisonersCounter
            int N = 100;
            boolean lightOn = true;
            boolean[] arr = new boolean[N];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = false;
            }
            int prisonersCounter = 0;
            int enterIntoRoomCounter = 0;
            int p;
            while (prisonersCounter < 99)
            {
                p = (int)(Math.random()*100); //0...99

                if (p == 0)
                {
                    if (lightOn == false)
                    {
                        prisonersCounter++;
                        lightOn = true;
                    }
                }
                else
                {
                    if (lightOn == true && arr[p] == false )
                    {
                        arr[p] = true;
                        lightOn = false;
                    }
                }

                enterIntoRoomCounter++;
            }
           // System.out.println(prisonersCounter);
            return enterIntoRoomCounter;
        }



    public static void main(String[] args) {
      //  prisonProblemUnknownState();
//        System.out.println("______");
//        prisonProblem();
//        System.out.println("______");
        int ans=prisonersLigthON();
        System.out.println(ans);

        System.out.println("_______________________________________________________________");
        double tests = 50000;
        double avg = 0;
        for (int i = 0; i < tests; i++) {
            avg += prisonersLigthON() / tests;
        }

        System.out.println("AVG of enter to room : " + avg);

        int ans1=lightOFF();
        System.out.println( "the light is off,prisonersCounterans : " + ans1);

    }
}

