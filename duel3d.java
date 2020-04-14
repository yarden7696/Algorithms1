package Algo;
//////////////////
class  Player {
    String name;
    boolean isAlive = true;
    double v; //success rate

    public Player(String name, double v) {
        this.name = name;
        this.v = v;
    }

}

public class duel3d {

    public static void duel2d(Player p1,Player p2,int steps) {
        int turn = 1;
        while(p1.isAlive && p2.isAlive) {
            if(steps>200) {
                System.out.println("Time is over - no winner");
                return;
            }
            steps++;
            switch (turn) {
                case 1:
                    if(Math.random() < p1.v) {
                        p2.isAlive = false;
                        System.out.println(p2.name + " was killed by " + p1.name);
                    }
                    else {
                        System.out.println(p1.name + " missed");
                    }
                    break;
                case 2:
                    if(Math.random() < p2.v) {
                        p1.isAlive = false;
                        System.out.println(p1.name + " was killed by " + p2.name);
                    }
                    else {
                        System.out.println(p2.name + " missed");
                    }
                    break;
            }
            turn = 3 - turn;
        }
        System.out.println("The winner is: " + (p1.isAlive ? p1.name : p2.name));
        System.out.println("Steps = " + steps);
    }

    /**
     * Fight between 3 soldiers each one have his own strategy
     * Complexity: O(?)
     */
    public static void duel3d(Player p1,Player p2,Player p3) {
        int turn = (int)(Math.random()*3+1);
        int steps = 0; // caz the game is limited to 200 steps
        Player target;
        while(p1.isAlive && p2.isAlive && p3.isAlive) { // while the all 3 players are alive
            if(steps>200) {
                System.out.println("Time is over - no winner");
                return;
            }
            steps++;
            turn++; // turn of the next player
            if(turn > 3) turn = 1; // turn can be maximum 3 (caz when he become 4 we decrease him to 1 to the next player)
            switch (turn) {
                case 1: //case 1= strategy of player 1 is to kill the best after him .
                    target = strategy(p1,p2,p3);  //notice: there is importance to come into the players into fun
                                                 //strategy. when were in case 1 we need to find the best player
                                                // after p1 caz this is the player that p1 wont to kill.
                                               // This is the case for the other cases!
                    if(target != null && Math.random() < p1.v) { //( target != null ) means not the worst player caz
                                                                // we wont fight between the 2 bests.
                        target.isAlive = false;
                        System.out.println(target.name + " was killed by " + p1.name);
                    }
                    else { // when Math.random()> p1.v
                        System.out.println(p1.name + " missed");
                    }
                    break;
                case 2: //case 2 = strategy of player 2
                    target = strategy(p2,p1,p3);
                    if(target != null && Math.random() < p2.v) {
                        target.isAlive = false;
                        System.out.println(target.name + " was killed by " + p2.name);
                    }
                    else {
                        System.out.println(p2.name + " missed");
                    }
                    break;
                case 3: //case 3 = strategy of player 3
                    target = strategy(p3,p1,p2);
                    if(target != null && Math.random() < p3.v) {
                        target.isAlive = false;
                        System.out.println(target.name + " was killed by " + p3.name);
                    }
                    else {
                        System.out.println(p3.name + " missed");
                    }
                    break;
            }
        }
        // if someone is died, we will check who is it(we didn't go into while loop)
        if(!p1.isAlive) { // if p1 is died
            if(turn == 2) duel2d(p3, p2,steps);
            else duel2d(p2, p3,steps);
        }
        else if(!p2.isAlive) { // if p2 is died
            if(turn == 3) duel2d(p1, p3,steps);
            else duel2d(p3, p1,steps);
        }
        else if(!p3.isAlive) { // if p3 is died
            if(turn == 2) duel2d(p1, p2,steps);
            else duel2d(p2, p1,steps);
        }
    }

    /**
     * in this function we check who is the best player  and return him.
     * if p1 is the worst player we return null (shot in the air), else we check who is the best player between
     * the 2 players.
     * @return the best player.
     */
    private static Player strategy(Player p1, Player p2, Player p3) {
        if(p1.v < p2.v && p1.v < p3.v) { //The condition says that if p1 is the worst player ,
                                                // then we will not do anything, "shot in the air by p1"
            return null;
        }
        return p2.v > p3.v ? p2: p3; // check who is the best between p2 and p3. return the best
    }

    public static void main(String[] args) {

        duel3d(new Player("B",1.0), new Player("C",0.8), new Player("D",0.5));
    }
}