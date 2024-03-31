import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static ArrayList<Brick> boardList = new ArrayList<>();
    public static int Bsize = 8; // Size of the board
    public static int[][] board = new int[Bsize][Bsize];
    public static int[][] temp = new int[Bsize][Bsize];
    public static long startTimer;

    public static void main(String[] args) {
        PrintingBoard();//print empty board
        int color = 0;
        // printing the menu
        System.out.println("Enter which mode do u wanna play !\n1- Manual\n2-Automatic\n");
        int mchoice = in.nextInt();
        if (mchoice == 2) {
            System.out.println("Which color do u wanna play with?\n1-White\n2-Black\n");
            color = in.nextInt();
        }

        if (mchoice == 1) {
            Manual_Mode();

        } else if (mchoice == 2 && color == 1) {
            Auto_Mode();
        } else if (mchoice == 2 && color == 2) {
            Auto_Mode2();
        }

    }

    public static void Auto_Mode() {
        int x = 0, y = 0;
        int mode=0;
        int tie=0;
        while (boardList.size() != (Bsize * Bsize)) {
            //////////////////// black turn /////////////////////////////////////////////
            int flag = 1;
            int wonFlag = 0;
            startTimer=System.nanoTime();

            while (flag == 1) {
                mode=1;
                System.out.println("Black Player turn (enter Colomn  & Row e.g (A5) ");
                CreateTree2(mode);//find the best move
                long duration=(System.nanoTime()-startTimer)/1000000;

                PrintingBoard();
                System.out.println(duration+" ms");
                if (check_win(mode)) {
                    System.out.println("Black won!!!!");
                    tie=1;
                    wonFlag = 1;
                    break;

                }
                flag = 0;
            }
            if (wonFlag == 1) {
                break;
            }

            //////////////////// white turn /////////////////////////////////////////////

            flag = 1;
            wonFlag = 0;
            while (flag == 1) {
                mode=2;
                System.out.println("White Player turn (enter Colomn  & Row e.g (A5) ");
                String choice = in.next();
                choice = choice.toUpperCase();
                //get the indix from user then check it's within range
                if ((choice.charAt(0) <= 'H') && (choice.charAt(1) <= '8') && (choice.charAt(0) >= 'A') && (choice.charAt(0) > '0')) {
                    y = get_index(choice.charAt(0));
                    x = Character.getNumericValue(choice.charAt(1));
                    x--;//to start from index 0

                    if (Check_if_index_available(x, y)) {

                        boardList.add(new Brick(x, y, "white"));
                        Adding_Brick_to_Board(x, y,mode);
                        PrintingBoard();
                        if (check_win(mode)) {
                            tie=1;
                            System.out.println("White won!!!!");
                            wonFlag = 1;
                            break;

                        }
                        flag = 0;
                    } else {
                        System.out.println("Wrong index! plz try again! \n");
                    }
                }

            }
            if (wonFlag == 1) {
                break;
            }
        }
        if (tie==0){
            System.out.println("DRAW !!!");
        }


    }

    public static void Auto_Mode2() {
        int x = 0, y = 0;
        int mode=0;
        int tie=0;
        while (boardList.size() != (Bsize * Bsize)) {
            //////////////////// black turn /////////////////////////////////////////////
            int flag = 1;
            int wonFlag = 0;

            while (flag == 1) {
                System.out.println("Black Player turn (enter Colomn  & Row e.g (A5) ");
                mode=1;
                String choice = in.next();
                choice = choice.toUpperCase();
                //get the indix from user then check it's within range
                if ((choice.charAt(0) <= 'H') && (choice.charAt(1) <= '8') && (choice.charAt(0) >= 'A') && (choice.charAt(0) > '0')) {
                    y = get_index(choice.charAt(0));
                    x = Character.getNumericValue(choice.charAt(1));
                    x--;//to start from index 0

                    if (Check_if_index_available(x, y)) {

                        boardList.add(new Brick(x, y, "black"));
                        Adding_Brick_to_Board(x, y,mode);
                        PrintingBoard();
                        if (check_win(mode)) {
                            tie=1;
                            System.out.println("Black won!!!!");
                            wonFlag = 1;
                            break;

                        }
                        flag = 0;
                    } else {
                        System.out.println("Wrong index! plz try again! \n");
                    }
                }
            }
            if (wonFlag == 1) {
                break;
            }

            //////////////////// white turn /////////////////////////////////////////////

            flag = 1;
            wonFlag = 0;
            while (flag == 1) {
                mode =2;
                System.out.println("White Player turn (enter Colomn  & Row e.g (A5) ");
                startTimer=System.nanoTime();
                CreateTree2(mode);
                //return the x and y from the minimax
                long duration=(System.nanoTime()-startTimer)/1000000;
                PrintingBoard();
                System.out.println(duration+" ms");
                if (check_win(mode)) {
                    System.out.println("White won!!!!");
                    tie=1;
                    wonFlag = 1;

                    break;
                }
                flag = 0;


            }
            if (wonFlag == 1) {
                break;
            }
        }
        if (tie == 0) {
            System.out.println("DRAW !!!");

        }

    }

    public static void Manual_Mode() {
        int x = 0, y = 0;
        int tie=0;
        while (boardList.size() != (Bsize * Bsize)) {

            //////////////////// black turn /////////////////////////////////////////////
            int flag = 1;
            int wonFlag = 0;
            int mode =0;

            while (flag == 1) {
                mode=1;
                System.out.println("Black Player turn (enter Colomn  & Row e.g (A5) ");
                String choice = in.next();
                choice = choice.toUpperCase();
                //get the indix from user then check it's within range
                if ((choice.charAt(0) <= 'H') && (choice.charAt(1) <= '8') && (choice.charAt(0) >= 'A') && (choice.charAt(1) > '0')) {
                    y = get_index(choice.charAt(0));
                    x = Character.getNumericValue(choice.charAt(1));
                    x--;//to start from index 0

                    if (Check_if_index_available(x, y)) {

                        boardList.add(new Brick(x, y, "Black"));
                        Adding_Brick_to_Board(x, y,mode);
                        PrintingBoard();
                        if (check_win(mode)) {
                            tie=1;
                            System.out.println("Black won!!!!");
                            wonFlag = 1;
                            break;
                        }
                        flag = 0;
                    } else {
                        System.out.println("Wrong index! plz try again! \n");
                    }
                }
            }
            if (wonFlag == 1) {
                break;
            }

            //////////////////// white turn /////////////////////////////////////////////


            flag = 1;
            wonFlag = 0;
            while (flag == 1) {
                mode=2;
                System.out.println("White Player turn (enter Colomn  & Row e.g (A5) ");
                String choice = in.next();
                choice = choice.toUpperCase();
                //get the indix from user then check it's within range
                if ((choice.charAt(0) <= 'H') && (choice.charAt(1) <= '8') && (choice.charAt(0) >= 'A') && (choice.charAt(0) > '0')) {
                    y = get_index(choice.charAt(0));
                    x = Character.getNumericValue(choice.charAt(1));
                    x--;//to start from index 0

                    if (Check_if_index_available(x, y)) {

                        boardList.add(new Brick(x, y, "white"));
                        Adding_Brick_to_Board(x, y,mode);
                        PrintingBoard();
                        if (check_win(mode)) {
                            tie=1;
                            System.out.println("White won!!!!");
                            wonFlag = 1;
                            break;
                        }
                        flag = 0;
                    } else {
                        System.out.println("Wrong index! plz try again! \n");
                    }
                }
            }
            if (wonFlag == 1) {
                break;
            }
        }
        if (tie==0){
            System.out.println("DRAW !!!");
        }
    }
    //get the index from colomn char

    public static boolean Check_if_index_available(int x, int y) {

        if (board[x][y] != 0) { // if the board is empty

            return false;
        } else if (board[x][y] == 0 && (y == 0 || y == 7)) {//if index is empty and moving next to magnatic
            return true;
        } else if (board[x][y] == 0 && board[x][y - 1] != 0) {//if index is empty and has a brick before it from left
            return true;
        } else if (board[x][y] == 0 && board[x][y + 1] != 0) {//if index is empty and has a brick before it from right
            return true;
        } else {
            return false;

        }
    }

    public static void Adding_Brick_to_Board(int x, int y,int mode) {
        board[x][y] = mode;
    }

    public static void PrintingBoard() {
        System.out.print("     A  |   B   |    C  |   D   |   E   |   F   |   G   |   H   |\n");
        System.out.println("   ------------------------------------------------------------");
        for (int i = 0; i < 8; i++) {
            System.out.print(i+1+"   ");
            for (int j = 0; j < 8; j++) {

                if(board[i][j]==1){
                    System.out.print("□" + "   |   ");
                }
                else if(board[i][j]==2){
                    System.out.print("■" + "   |   ");
                }
                else {
                    System.out.print(" " + "   |   ");
                }
            }
            System.out.println();
            System.out.println("   ------------------------------------------------------------");
        }

    }

    public static boolean check_win(int mode) {
        // Check rows
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col <= board[row].length - 5; col++) {
                if (board[row][col] != 0 &&
                        board[row][col] == board[row][col + 1] &&
                        board[row][col] == board[row][col + 2] &&
                        board[row][col] == board[row][col + 3] &&
                        board[row][col] == board[row][col + 4]) {
                    return true;
                }
            }
        }
        //check colomn
        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row <= board.length - 5; row++) {
                if (board[row][col] != 0 &&
                        board[row][col] == board[row + 1][col] &&
                        board[row][col] == board[row + 2][col] &&
                        board[row][col] == board[row + 3][col] &&
                        board[row][col] == board[row + 4][col]) {
                    return true;
                }
            }
        }
        //check diagonal
        // Check diagonals starting from top-left to bottom-right
            for (int row = 0; row <= board.length - 5; row++) {
                for (int col = 0; col <= board[row].length - 5; col++) {
                    if (board[row][col] != 0 &&
                            board[row][col] == board[row + 1][col + 1] &&
                            board[row][col] == board[row + 2][col + 2] &&
                            board[row][col] == board[row + 3][col + 3] &&
                            board[row][col] == board[row + 4][col + 4]) {
                        return true;
                    }
                }
            }

            // Check anti diagonals
            for (int row = 0; row <= board.length - 5; row++) {
                for (int col = board[row].length - 1; col >= 4; col--) {
                    if (board[row][col] != 0 &&
                            board[row][col] == board[row + 1][col - 1] &&
                            board[row][col] == board[row + 2][col - 2] &&
                            board[row][col] == board[row + 3][col - 3] &&
                            board[row][col] == board[row + 4][col - 4]) {
                        return true;
                    }
                }
            }


        return false;

    }

    public static int get_index(char y) {
        if (y == 'A') {
            return 0;
        } else if (y == 'B') {
            return 1;
        } else if (y == 'C') {
            return 2;
        } else if (y == 'D') {
            return 3;
        } else if (y == 'E') {
            return 4;
        } else if (y == 'F') {
            return 5;
        } else if (y == 'G') {
            return 6;
        } else if (y == 'H') {
            return 7;
        } else
            return 8;
    }

    ////////////////////////////////////////////////////////////////////////////////
    public static int HeuristicFunction(int mode) {
        int bscore = 0, wscore = 0;

        if(mode==1){
            // Check rows by calculating the number of sequence color
            for (int row = 0; row < temp.length; row++) {
                int wCount = 0, bCount = 0;
                for (int col = 0; col < temp[row].length; col++) {
                    if (temp[row][col] == 1) {
                        wCount++;
                        bCount = 0;
                    } else if (temp[row][col] == 2) {
                        bCount++;
                        wCount = 0;
                    } else {
                        wCount = 0;
                        bCount = 0;
                    }
                    // giving scorse accordint to the number of sequenceing color
                    if (wCount == 5)
                        wscore += 1000;
                    else if (bCount == 5)
                        bscore += 1000;
                    else if (wCount == 4 && bCount == 0)
                        wscore += 100;
                    else if (bCount == 4 && wCount == 0)
                        bscore += 100;
                    else if (wCount == 3 && bCount == 0)
                        wscore += 10;
                    else if (bCount == 3 && wCount == 0)
                        bscore += 10;
                    else if (wCount == 2 && bCount == 0)
                        wscore += 5;
                    else if (bCount == 2 && wCount == 0)
                        bscore += 5;
                    else if (wCount == 1 && bCount == 0)
                        wscore += 1;
                    else if (bCount == 1 && wCount == 0)
                        bscore += 1;
                }
            }

            // Check columns
            for (int col = 0; col < temp[0].length; col++) {
                int wCount = 0, bCount = 0;
                for (int row = 0; row < temp.length; row++) {
                    if (temp[row][col] == 1) {
                        wCount++;
                        bCount = 0;
                    } else if (temp[row][col] == 2) {
                        bCount++;
                        wCount = 0;
                    } else {
                        wCount = 0;
                        bCount = 0;
                    }

                    if (wCount == 5)
                        wscore += 1000;
                    else if (bCount == 5)
                        bscore += 1000;
                    else if (wCount == 4 && bCount == 0)
                        wscore += 100;
                    else if (bCount == 4 && wCount == 0)
                        bscore += 100;
                    else if (wCount == 3 && bCount == 0)
                        wscore += 10;
                    else if (bCount == 3 && wCount == 0)
                        bscore += 10;
                    else if (wCount == 2 && bCount == 0)
                        wscore += 5;
                    else if (bCount == 2 && wCount == 0)
                        bscore += 5;
                    else if (wCount == 1 && bCount == 0)
                        wscore += 1;
                    else if (bCount == 1 && wCount == 0)
                        bscore += 1;
                }
            }

            // Check diagonals
            for (int i = 0; i <= temp.length - 5; i++) {
                for (int j = 0; j <= temp[i].length - 5; j++) {
                    int wCount = 0, bCount = 0;
                    for (int k = 0; k < 5; k++) {
                        if (temp[i + k][j + k] == 1) {
                            wCount++;
                            bCount = 0;
                        } else if (temp[i + k][j + k] == 2) {
                            bCount++;
                            wCount = 0;
                        } else {
                            wCount = 0;
                            bCount = 0;
                        }

                        if (wCount == 5)
                            wscore += 1000;
                        else if (bCount == 5)
                            bscore += 1000;
                        else if (wCount == 4 && bCount == 0)
                            wscore += 100;
                        else if (bCount == 4 && wCount == 0)
                            bscore += 100;
                        else if (wCount == 3 && bCount == 0)
                            wscore += 10;
                        else if (bCount == 3 && wCount == 0)
                            bscore += 10;
                        else if (wCount == 2 && bCount == 0)
                            wscore += 5;
                        else if (bCount == 2 && wCount == 0)
                            bscore += 5;
                        else if (wCount == 1 && bCount == 0)
                            wscore += 1;
                        else if (bCount == 1 && wCount == 0)
                            bscore += 1;
                    }
                }
            }

            return wscore - bscore;
        }
        else {
            // Check rows
            for (int row = 0; row < temp.length; row++) {
                int wCount = 0, bCount = 0;
                for (int col = 0; col < temp[row].length; col++) {
                    if (temp[row][col] == 1) {
                        wCount++;
                        bCount = 0;
                    } else if (temp[row][col] == 2) {
                        bCount++;
                        wCount = 0;
                    } else {
                        wCount = 0;
                        bCount = 0;
                    }

                    if (wCount == 5)
                        wscore += 1000;
                    else if (bCount == 5)
                        bscore += 1000;
                    else if (wCount == 4 && bCount == 0)
                        wscore += 100;
                    else if (bCount == 4 && wCount == 0)
                        bscore += 100;
                    else if (wCount == 3 && bCount == 0)
                        wscore += 10;
                    else if (bCount == 3 && wCount == 0)
                        bscore += 10;
                    else if (wCount == 2 && bCount == 0)
                        wscore += 5;
                    else if (bCount == 2 && wCount == 0)
                        bscore += 5;
                    else if (wCount == 1 && bCount == 0)
                        wscore += 1;
                    else if (bCount == 1 && wCount == 0)
                        bscore += 1;
                }
            }

            // Check columns
            for (int col = 0; col < temp[0].length; col++) {
                int wCount = 0, bCount = 0;
                for (int row = 0; row < temp.length; row++) {
                    if (temp[row][col] == 1) {
                        wCount++;
                        bCount = 0;
                    } else if (temp[row][col] == 2) {
                        bCount++;
                        wCount = 0;
                    } else {
                        wCount = 0;
                        bCount = 0;
                    }

                    if (wCount == 5)
                        wscore += 1000;
                    else if (bCount == 5)
                        bscore += 1000;
                    else if (wCount == 4 && bCount == 0)
                        wscore += 100;
                    else if (bCount == 4 && wCount == 0)
                        bscore += 100;
                    else if (wCount == 3 && bCount == 0)
                        wscore += 10;
                    else if (bCount == 3 && wCount == 0)
                        bscore += 10;
                    else if (wCount == 2 && bCount == 0)
                        wscore += 5;
                    else if (bCount == 2 && wCount == 0)
                        bscore += 5;
                    else if (wCount == 1 && bCount == 0)
                        wscore += 1;
                    else if (bCount == 1 && wCount == 0)
                        bscore += 1;
                }
            }

            // Check diagonals
            for (int i = 0; i <= temp.length - 5; i++) {
                for (int j = 0; j <= temp[i].length - 5; j++) {
                    int wCount = 0, bCount = 0;
                    for (int k = 0; k < 5; k++) {
                        if (temp[i + k][j + k] == 1) {
                            wCount++;
                            bCount = 0;
                        } else if (temp[i + k][j + k] == 2) {
                            bCount++;
                            wCount = 0;
                        } else {
                            wCount = 0;
                            bCount = 0;
                        }

                        if (wCount == 5)
                            wscore += 1000;
                        else if (bCount == 5)
                            bscore += 1000;
                        else if (wCount == 4 && bCount == 0)
                            wscore += 100;
                        else if (bCount == 4 && wCount == 0)
                            bscore += 100;
                        else if (wCount == 3 && bCount == 0)
                            wscore += 10;
                        else if (bCount == 3 && wCount == 0)
                            bscore += 10;
                        else if (wCount == 2 && bCount == 0)
                            wscore += 5;
                        else if (bCount == 2 && wCount == 0)
                            bscore += 5;
                        else if (wCount == 1 && bCount == 0)
                            wscore += 1;
                        else if (bCount == 1 && wCount == 0)
                            bscore += 1;
                    }
                }
            }

            return bscore - wscore;
        }

    }



    public static boolean Check_possiblties(int x, int y) {

        if (temp[x][y] != 0) { // if the board is empty

            return false;
        } else if (temp[x][y] == 0 && (y == 0 || y == 7)) {
            return true;
        } else if (temp[x][y] == 0 && temp[x][y - 1] != 0) {
            return true;
        } else if (temp[x][y] == 0 && temp[x][y + 1] != 0) {
            return true;
        } else {
            return false;

        }


    }

    public static void CreateTree2(int mode) {
        TreeOfBricks perent = new TreeOfBricks(0, 0, 0);
        ArrayList<TreeOfBricks> current = new ArrayList<>();
        TreeOfBricks minNode=new TreeOfBricks(0,0,0);
        TreeOfBricks maxNode=new TreeOfBricks(0,0,0);


        ArrayList<TreeOfBricks> d1 = new ArrayList<>();
        ArrayList<TreeOfBricks> d2 = new ArrayList<>();
        ArrayList<TreeOfBricks> d3 = new ArrayList<>();
        ArrayList<TreeOfBricks> d4 = new ArrayList<>();
        int c1=0,c2=0,c3=0,c4=0;
        int ai=0;
        int user=0;
        if(mode==1){
             ai=1;
             user=2;
        }
        else if(mode==2){
             ai=2;
             user=1;
        }
        temp = board;
        boolean turn = true;

        for (int i = 0; i < Bsize; i++) {
            for (int j = 0; j < Bsize; j++) {
                temp = board;
                if (Check_possiblties(i, j)) {
                    c2=0;
                    perent.addChild(new TreeOfBricks(0, i, j));//creat the possible moves for player one
                    current.add(0,perent.child.get(perent.child.size() - 1));//get the first move to current
                    d1.add(perent.child.get(perent.child.size() - 1));
                    temp[current.get(0).x][current.get(0).y] = ai;//assuming that player chooses this move
                    c1++;
                    for (int a = 0; a < Bsize; a++) {
                        for (int b = 0; b < Bsize; b++) {
                            if (Check_possiblties(a, b)) {
                                c3=0;
                                current.get(0).addChild(new TreeOfBricks(0, a, b));//creat the possible moves for player two after p1 move
                                current.add(1,current.get(0).child.get(current.get(0).child.size() - 1)) ;//add this next move to the arraylist
                                d2.add(current.get(0).child.get(current.get(0).child.size() - 1));
                                temp[current.get(1).x][current.get(1).y]  = user;//assuming player 2 moves there
                                c2++;
                                for (int c = 0; c < Bsize; c++) {
                                    for (int d = 0; d < Bsize; d++) {
                                        if (Check_possiblties(c, d)) {
                                            current.get(1).addChild(new TreeOfBricks(0, c, d));//creat the possible moves for player one at level3
                                            current.add(2,current.get(1).child.get(current.get(1).child.size() - 1));//add this move to arraylist
                                            d3.add(current.get(1).child.get(current.get(1).child.size() - 1));
                                            temp[current.get(2).x][current.get(2).y]  = ai;//assuming player 1 moved here
                                            c3++;
                                            for (int e = 0; e < Bsize; e++) {
                                                for (int f = 0; f < Bsize; f++) {
                                                    if (Check_possiblties(e, f)) {
                                                        current.get(2).addChild(new TreeOfBricks(0, e, f));//creat the possible moves for player two at level 4
                                                        current.add(3,current.get(2).child.get(current.get(2).child.size() - 1)); //add this move to arraylist
                                                        d4.add(current.get(2).child.get(current.get(2).child.size() - 1));
                                                        temp[current.get(3).x][current.get(3).y]  = user;//assuming player 2 moved here
                                                        current.get(3).setData(HeuristicFunction(mode));  ;//get the heuristic value for that path if played
                                                        //System.out.println(current.get(3).data+":"+current.get(3).x+","+current.get(3).y);
                                                        temp[current.get(3).x][current.get(3).y] = 0;
                                                        current.remove(3);

                                                    }
                                                }

                                            }

                                            temp[current.get(2).x][current.get(2).y] = 0;
                                            current.remove(2);
                                            //find the min huristic for player 2
                                            minNode=d4.get(0);
                                            for (int z=0;z<d4.size();z++){
                                                if(d4.get(z).getData()<minNode.getData()){
                                                    minNode=d4.get(z);
                                                }
                                            }
                                            d3.get(c3-1).setData(minNode.getData());//set the heuristic data for the parent
                                            d4.clear();




                                        }

                                    }
                                }
                                temp[current.get(1).x][current.get(1).y] = 0;
                                current.remove(1);
                               //find the max value of heuristic of player 1
                                maxNode=d3.get(0);
                                for (int z=0;z<d3.size();z++){
                                    if(d3.get(z).getData()>maxNode.getData()){
                                        maxNode=d3.get(z);
                                    }
                                }
                                d2.get(c2-1).setData(maxNode.getData());
                                d3.clear();

                               // System.out.println("d2"+maxNode.getData()+":"+maxNode.getX()+","+maxNode.getY());
                            }

                        }
                    }
                    temp[current.get(0).x][current.get(0).y]= 0;
                    current.remove(0);
                    //find the min value of heuristic of player 2
                    minNode=d2.get(0);
                    for (int z=0;z<d2.size();z++){
                        if(d2.get(z).getData()<minNode.getData()){
                            minNode=d2.get(z);
                        }
                    }
                    d1.get(c1-1).setData(minNode.getData());
                    d2.clear();

                    //System.out.println("d1"+minNode.getData()+":"+minNode.x+","+minNode.y);

                }
            }
        }

       //find the max value of heuristic of player 1
        maxNode=d1.get(0);
        for (int z=0;z<d1.size();z++){
            if(d1.get(z).getData()>=maxNode.getData()){
                maxNode=d1.get(z);
            }
            //System.out.println("d1 ->"+d1.get(z).getData()+":"+d1.get(z).x+","+d1.get(z).y);
        }
        perent.setData(maxNode.getData());//give the root the max heuristic
        //give the parent the best move
        perent.setX(maxNode.getX());
        perent.setY(maxNode.getY());
        //System.out.println("pernt"+perent.getX()+","+ perent.getX()+":"+perent.getData());
        boardList.add(new Brick(perent.x, perent.y, "Black"));
        Adding_Brick_to_Board(perent.x, perent.y,mode);


    }
}


