package Algo;

import java.util.ArrayList;
import java.util.Vector;

//kind of questions:
// return best price of each node, return 1 best path (steps) , return all best paths(steps) ,
// return true/false if a given point in the best path,return the minimal price from point (p1,q1) to (p2,q2),
// return true if all the points in p is on the same shortest path,returns the shortest path with minimal turnings

class Node1 {
    int x; // line - mat(i,j).x is the price of edge right
    int y; // column - mat(i,j).y is the price of edge up
    int price; // the best price from (0,0) to this node
    int nPath; // number of shortest paths until this node
    int priceEnd; // the best price from (n,m) to this node

    public Node1(int x, int y) {
        this.x = x;
        this.y = y;
        price = 0;
        priceEnd = 0;
        nPath = 1;
    }


    public static class BestPath {
        int cheapestPrice; // sum of cheapest path
        int numOfBPaths; // amount of best paths
         Node1[][] mat;

        public BestPath(Node1[][] mat) {
            this.mat = mat;
            cheapestPrice = 0;
            numOfBPaths = 0;
            buildMatrix();
        }

        /**
         * build the matrix contains the price to get each point (from (0,0))
         * and the number of shortest path until each point
         * Complexity: O(n*m)
         */
        private void buildMatrix() {
            int n = mat.length, m = mat[0].length; // n,i- lines , m,j-columns

            for (int i = 1; i < n; i++) { // init first column, i=1
                mat[i][0].price = mat[i - 1][0].price + mat[i - 1][0].y;
                mat[i][0].nPath = 1; // caz there is only 1 way to init
            }
            for (int j = 1; j < m; j++) { // init first line , j=1
                mat[0][j].price = mat[0][j - 1].price + mat[0][j - 1].x;
                mat[0][j].nPath = 1; // caz there is only 1 way to init
            }

            for (int i = 1; i < n; i++) { // init rest of mat
                for (int j = 1; j < m; j++) {
                    int y = mat[i - 1][j].price + mat[i - 1][j].y; //red example in page 34- 3+1=4
                    int x = mat[i][j - 1].price + mat[i][j - 1].x; // black example- 5+4=9
                    mat[i][j].price = x <= y ? x : y; // put the min between x and y
                    if (y < x) mat[i][j].nPath = mat[i - 1][j].nPath; //it get y numberBP
                    else if (y > x) mat[i][j].nPath = mat[i][j - 1].nPath; //it get x numberBP
                    else mat[i][j].nPath = mat[i][j - 1].nPath + mat[i - 1][j].nPath;//if x==y so we found 2
                                                                                             //best paths until this node
                }
            }
            numOfBPaths = mat[n - 1][m - 1].nPath; // amount of best paths in node (n,m)
            cheapestPrice = mat[n - 1][m - 1].price; // cheapest price in node (n,m)
        }


        /**
         * returns one of shortest path - induction
         * Complexity: O(n+m) - but need to build the matrix first in O(n*m)
         */
        public String getOnePath() {
            String ans = "";
            int i = mat.length - 1, j = mat[0].length - 1; // (i,j) its the point (n,m)
            while (i > 0 && j > 0) {
                int a = mat[i - 1][j].price + mat[i - 1][j].y;
                int b = mat[i][j - 1].price + mat[i][j - 1].x;
                if (a < b) {
                    ans = "1" + ans;
                    i--;
                } else {
                    ans = "0" + ans;
                    j--;
                }
            }
            while (j > 0) { // case that i=0 and j did not caz j>0
                ans = "0" + ans;
                j--;
            }
            while (i > 0) { // case that j=0 and u did not caz i>0
                ans = "1" + ans;
                i--;
            }
            return ans;
        }

        /**
         * get all shortest paths
         * complexity: O((n+m)choose(n))
         */
        public Vector<String> getAllPathsRec() {
            Vector<String> ans = new Vector<String>();
            getAllPathsRecHelp("", mat.length - 1, mat[0].length - 1, ans);
            return ans;
        }

        private void getAllPathsRecHelp(String string, int i, int j, Vector<String> ans) {
            if (i == 0 && j == 0) { // stop condition
                ans.add(string); // add "" caz its the end
                return;
            } else if (i > 0 && j == 0) { // first column
                getAllPathsRecHelp("1" + string, i - 1, 0, ans);
            } else if (i == 0 && j > 0) { // first line
                getAllPathsRecHelp("0" + string, 0, j - 1, ans);
            } else { // i>0 && j>0 , we find the price of the 2 edges of the node and send theme recursively
                int a = mat[i - 1][j].price + mat[i - 1][j].y; //a is up
                int b = mat[i][j - 1].price + mat[i][j - 1].x; //b is right
                if (a < b) { // a is min and he up so we send 1
                    getAllPathsRecHelp("1" + string, i - 1, j, ans);
                } else if (b < a) {
                    getAllPathsRecHelp("0" + string, i, j - 1, ans);
                } else { //when a==b we found 2 best paths
                    getAllPathsRecHelp("1" + string, i - 1, j, ans);
                    getAllPathsRecHelp("0" + string, i, j - 1, ans);
                }
            }
        }

        /**
         * returns true if the given point is on one of the shortest paths
         * Complexity: O(n*m) but if we call buildMatrixFromTheEnd() first and then call isOnBestPath
         * the answer is in O(1)
         */
        public boolean isOnBestPath(int i, int j) {
            buildMatrixFromTheEnd();
            return (mat[i][j].price + mat[i][j].priceEnd) == cheapestPrice;
        } // mat[i][j].price its best price from (0,0) to this node
         // mat[i][j].priceFromTheEnd its best price from (n,m) to this node
        // if we add these tow sums we get the sum of the cheapest price caz both of them will give me the full
        // shortest path.

        /**
         * the same like build matrix but now we build it from the end to (0,0)
         * Complexity: O(n*m)
         */
        private void buildMatrixFromTheEnd() {

            int n = mat.length - 1, m = mat[0].length - 1;

            //init last column
            for (int i = n - 1; i >= 0; i--) { // as we start from i,j=1 in normal buildMatrix,
                                              // now we start from i,j= n-1,m-1
                mat[i][m].priceEnd = mat[i][m].y + mat[i + 1][m].priceEnd;
            } // נרצה למצוא את העלות המינימלית עבור נקודה (i,m) אז הפעם נלך הפוך - ניקח את הפרייס של הנקודה 3,3 לדוגמא והפעם ניקח את הערך
            // של הצלע y מנקודה (i,m) (שורה אחת מטה) כדי שנוכל לקחת את הy שזה up

            // init last line
            for (int j = m - 1; j >= 0; j--) {
                mat[n][j].priceEnd = mat[n][j + 1].priceEnd + mat[n][j].x;
            }
            // init rest of mat
            for (int i = n - 1; i >= 0; i--) {
                for (int j = m - 1; j >= 0; j--) {
                    int y = mat[i + 1][j].priceEnd + mat[i][j].y; //(2,2)= mat(3,2).priceFrom+mat(2,2).y(up)
                    int x = mat[i][j + 1].priceEnd + mat[i][j].x;
                    mat[i][j].priceEnd = x <= y ? x : y;
                }
            }
        }

        /**
         * return the minimal price from point (x1,y1) to (x2,y2)
         * Complexity: O(n*m)
         */
        private int bestPathBetween(int x1, int y1, int x2, int y2) {
            // Assuming p2>=p1 and q2>=q1
           Node1[][] temp = new Node1[x2 - x1 + 1][y2 - y1 + 1];
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < temp[0].length; j++) {
                    temp[i][j] = new Node1(0, 0);
                }
            }
            for (int i = 1; i < x2 - x1 + 1; i++) {// init first col
                temp[i][0].price = mat[i - 1 + x1][y1].y + temp[i - 1][0].price;
            }
            for (int j = 1; j < y2 - y1 + 1; j++) { // init first line
                temp[0][j].price = temp[0][j - 1].price + mat[x1][j - 1 + y1].x;
            }
            for (int i = 1; i < x2 - x1 + 1; i++) {
                for (int j = 1; j < y2 - y1 + 1; j++) {
                    int x = temp[i][j-1].price + mat[i + x1][j -1 + y1].x;
                    int y = temp[i-1][j].price + mat[i -1 + x1][j + y1].y;
                    temp[i][j].price = x <= y ? x : y;
                }
            }
            return temp[x2 - x1][y2 - y1].price;
        }


        /**
         * return true if all the points in p is on the same shortest path
         * sorting the array first (using counting sort: O(k)) by x and then by y
         * Complexity: O(n*m*k) k = |p|
         */
        public boolean isAllPointOnBestPath(Node1[] p) {
            sort(p);
            int sum = mat[p[0].y][p[0].x].price;
            for (int i = 1; i < p.length; i++) {
                if (p[i].y < p[i - 1].y) return false;
                sum += bestPathBetween(p[i - 1].x, p[i - 1].y, p[i].x, p[i].y);
            }
            sum += bestPathBetween(p[p.length - 1].x, p[p.length - 1].y, mat[0].length - 1, mat.length - 1);
            return sum == cheapestPrice;
        }

        private void sort(Node1[] p) {
            @SuppressWarnings("unchecked")
            ArrayList<Node1>[] freqy = new ArrayList[mat.length];
            for (int i = 0; i < freqy.length; i++) {
                freqy[i] = new ArrayList<Node1>();
            }
            Node1[] temp = new Node1[p.length];
            for (int i = 0; i < p.length; i++) {
                freqy[p[i].y].add(p[i]);
            }
            int k = 0;
            for (int i = 0; i < freqy.length; i++) {
                for (int j = 0; j < freqy[i].size(); j++) {
                    temp[k++] = freqy[i].get(j);
                }
            }
            @SuppressWarnings("unchecked")
            ArrayList<Node1>[] freqx = new ArrayList[mat[0].length];
            for (int i = 0; i < freqx.length; i++) {
                freqx[i] = new ArrayList<Node1>();
            }
            Node1[] temp2 = new Node1[p.length];
            for (int i = 0; i < temp.length; i++) {
                freqx[temp[i].x].add(temp[i]);
            }
            k = 0;
            for (int i = 0; i < freqx.length; i++) {
                for (int j = 0; j < freqx[i].size(); j++) {
                    temp2[k++] = freqx[i].get(j);
                }
            }
            for (int i = 0; i < temp2.length; i++) {
                p[i] = temp2[i];
            }
        }

        /**
         * returns the shortest path with minimal turnings
         * Complexity: O((n+m)choose(n)*(m+n))
         */
        public String optimalPath_minTurns() {
            Vector<String> paths = getAllPathsRec(); // we need all the paths to see who has the minimal turns
            String ans = ""; // here we will get the best path with minimal turns
            int min = Integer.MAX_VALUE;
            for (String pat : paths) { // in paths i have the all best paths, we need theme caz we check
                                     // per best path how much turning there is
                int turning = 0;
                for (int i = 1; i < pat.length(); i++) { //check per path how much turning there is
                    if (pat.charAt(i) != pat.charAt(i - 1)) turning++; // נניח ויש לי מסלול : 011001
                }
                if (turning < min) {
                    ans = pat;
                    min = turning;
                }
            }
            return ans;
        }

        public static void main(String[] args) {
            Node1[][] mat = new Node1[4][4];
            mat[0][0] = new Node1(3, 5);
            mat[0][1] = new Node1(3, 1);
            mat[0][2] = new Node1(1, 5);
            mat[0][3] = new Node1(0, 9);
            mat[1][0] = new Node1(4, 9);
            mat[1][1] = new Node1(6, 3);
            mat[1][2] = new Node1(3, 6);
            mat[1][3] = new Node1(0, 8);
            mat[2][0] = new Node1(10, 1);
            mat[2][1] = new Node1(4, 5);
            mat[2][2] = new Node1(7, 3);
            mat[2][3] = new Node1(0, 1);
            mat[3][0] = new Node1(7, 0);
            mat[3][1] = new Node1(8, 0);
            mat[3][2] = new Node1(18, 0);
            mat[3][3] = new Node1(0, 0);

            BestPath bp = new BestPath(mat);

//            System.out.println(bp.cheapestPrice);
//            System.out.println(bp.numOfBPaths);
//
//            System.out.println(bp.getOnePath());
//
//
//            System.out.println(bp.getAllPathsRec());
//
//            System.out.println(bp.isOnBestPath(1, 1));
//
//            System.out.println(bp.isOnBestPath(1, 2));

            System.out.println(bp.isAllPointOnBestPath(new Node1[]{new Node1(1, 1), new Node1(2, 1)}));
            System.out.println(bp.isAllPointOnBestPath(new Node1[]{new Node1(1, 1), new Node1(3, 1), new Node1(3, 3)}));

            System.out.println(bp.optimalPath_minTurns());

            System.out.println(bp.bestPathBetween(1,0,2,3));



        }
    }
}


