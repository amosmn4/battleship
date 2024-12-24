import java.util.*;

public class Game {
    public static int[][] PlayerMap = Tools.zeroMatrix(10, 10);
    public static int[][] ComputerMap = Tools.zeroMatrix(10, 10);

    public static int[][] pShips = {{0,0,5,0,0},{0,0,4,0,0},{0,0,3,0,0},{0,0,3,0,0},{0,0,2,0,0}};
    public static int pShipRow =0;

    public static int[][] cShips = {{0,0,5,0,0},{0,0,4,0,0},{0,0,3,0,0},{0,0,3,0,0},{0,0,2,0,0}};
    public static int cShipRow =0;


    public static void main(String[] args) {
        placePlayerShips();
        Tools.PrintMap(PlayerMap);
        placeCShips();
        //Tools.PrintMap(ComputerMap);
        int playerShips   = 5;
        int computerShips = 5;
        while(playerShips>0 && computerShips>0)
        {
            playerTurn();
            Tools.checkShips(ComputerMap,cShips,"You", "their");
            computerTurn();
            Tools.checkShips(PlayerMap,pShips,"They", "your");
            playerShips=0;
            for(int i=0; i<10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (PlayerMap[i][j] == 1)
                        playerShips++;
                }
            }
            computerShips=0;
            for(int i=0; i<10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (ComputerMap[i][j] == 1)
                        computerShips++;
                }
            }
        }

        if(computerShips>playerShips)
            System.out.println("Sorry, you lost!");
        else if(computerShips<playerShips)
            System.out.println("You won!");
        else
            System.out.println("How?");

    }


    public static void placePlayerShips() {
        Tools.PrintMap(PlayerMap);
        placeShip(5, "Carrier");
        Tools.PrintMap(PlayerMap);
        placeShip(4, "Battleship");
        Tools.PrintMap(PlayerMap);
        placeShip(3, "Cruiser");
        Tools.PrintMap(PlayerMap);
        placeShip(3, "Submarine");
        Tools.PrintMap(PlayerMap);
        placeShip(2, "Destroyer");

    }

    public static void placeCShips() {

        placeCShip(5, "Carrier");
        placeCShip(4, "Battleship");
        placeCShip(3, "Cruiser");
        placeCShip(3, "Submarine");
        placeCShip(2, "Destroyer");

    }
    public static void placeShip(int length, String info) {
        int sum = 0;
        Scanner input = new Scanner(System.in);
        System.out.print("Choose a Row for your " + info + ": ");
        int row = input.nextInt();
        System.out.print("Choose Column for your " + info + ": ");
        int col = input.nextInt();
        System.out.print("Vertical or Horizontal Placement: ");
        int z = input.nextInt();
        if (z == 0) {
            if(col+length-1 >10 ||col < 1 ||row<1 ||row>10) {
                System.out.println("You can't place ships outside the " + 10 + " by " + 10 + " grid");
                Tools.PrintMap(PlayerMap);
                placeShip(length, info);
            }
            else {
                for (int i = col - 1; i <= col + length - 2; i++) {
                    sum = sum + PlayerMap[row-1][i];
                }
                if ((row >= 1 && row < 11) && (col >= 1 && col + length - 1 < 11) && (sum == 0)) {
                    for (int i = col - 1; i <= col + length - 2; i++)
                        PlayerMap[row - 1][i] = 1;
                    pShips[pShipRow][0]=row-1;
                    pShips[pShipRow][1]=col-1;
                    pShips[pShipRow][3]=z;
                    pShipRow++;
                } else if ((row > 0 && row < 11) && (col >= 0 && col < 11) && sum >0) {
                    System.out.println("You can't place two ships on the same location");
                    Tools.PrintMap(PlayerMap);
                    placeShip(length, info);
                }
            }

        }
        else {
            if(col >10 ||col < 1 ||row<1 ||row+length-1>10) {
                System.out.println("You can't place ships outside the " + 10 + " by " + 10 + " grid");
                Tools.PrintMap(PlayerMap);
                placeShip(length, info);
            }
            else {
                for (int i = row - 1; i <= row + length - 2; i++) {
                    sum = sum + PlayerMap[i][col-1];
                }
                if ((row+length - 1 >= 1 && row < 11) && (col >= 1 && col  < 11) && (sum == 0)) {
                    for (int i = row - 1; i <= row + length - 2; i++)
                        PlayerMap[i][col-1] = 1;
                    pShips[pShipRow][0]=row-1;
                    pShips[pShipRow][1]=col-1;
                    pShips[pShipRow][3]=z;
                    pShipRow++;
                } else if ((row > 0 && row < 11) && (col >= 0 && col < 11) && sum >= 1) {
                    System.out.println("You can't place two ships on the same location");
                    Tools.PrintMap(PlayerMap);
                    placeShip(length, info);
                }
            }
        }
    }
    public static void placeCShip(int length, String info) {
        int sum = 0;
        int row =(int)(Math.random() * 10)+1;
        int col =(int)(Math.random() * 10)+1;
        int z =(int)(Math.random() *2 ) ;
        if (z == 0) {
            if(col+length-1 >10 ||col < 1 ||row<1 ||row>10) {
                placeCShip(length, info);
            }
            else {
                for (int i = col - 1; i <= col + length - 2; i++) {
                    sum = sum + ComputerMap[row-1][i];
                }
                if ((row >= 1 && row < 11) && (col >= 1 && col + length - 1 < 11) && (sum == 0)) {
                    for (int i = col - 1; i <= col + length - 2; i++)
                        ComputerMap[row - 1][i] = 1;
                    cShips[cShipRow][0]=row-1;
                    cShips[cShipRow][1]=col-1;
                    cShips[cShipRow][3]=z;
                    cShipRow++;
                } else if ((row > 0 && row < 11) && (col >= 0 && col < 11) && sum >0) {
                    placeCShip(length, info);
                }
            }

        }
        else {
            if(col >10 ||col < 1 ||row<1 ||row+length-1>10) {
                placeCShip(length, info);
            }
            else {
                for (int i = row - 1; i <= row + length - 2; i++) {
                    sum = sum + ComputerMap[i][col-1];
                }
                if ((row+length - 1 >= 1 && row < 11) && (col >= 1 && col  < 11) && (sum == 0)) {
                    for (int i = row - 1; i <= row + length - 2; i++)
                        ComputerMap[i][col-1] = 1;
                    cShips[cShipRow][0]=row-1;
                    cShips[cShipRow][1]=col-1;
                    cShips[cShipRow][3]=z;
                    cShipRow++;
                } else if ((row > 0 && row < 11) && (col >= 0 && col < 11) && sum >= 1) {
                    placeCShip(length, info);
                }
            }
        }
    }
    public static void playerTurn() {
        Tools.PrintBattleMap(ComputerMap);
        Scanner input = new Scanner(System.in);
        System.out.print("Choose a row for your attack:");
        int row = input.nextInt();
        System.out.print("Choose a column for your attack:");
        int col = input.nextInt();
        if(row<1||row>10||col<1||col>10) {
            System.out.println("Out of bounds");
            playerTurn();
        }
        else if(ComputerMap[row-1][col-1]!=0 && ComputerMap[row-1][col-1]!=1 ){
            System.out.println("You already shot here");
            playerTurn();
        }
        else{
            if(ComputerMap[row-1][col-1]==1){
                System.out.println("Hit!");
                ComputerMap[row-1][col-1]=2;
            }
            else{
                System.out.println("Miss!");
                ComputerMap[row-1][col-1]=-1;
            }
        }
    }
    public static void computerTurn() {
        // Priority targeting for more effective computer strategy
        List<int[]> targets = new ArrayList<>();
    
        // Check for potential targets adjacent to previous hits
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (PlayerMap[i][j] == 2) { // A previous hit
                    // Add adjacent positions as potential targets
                    if (i > 0 && PlayerMap[i - 1][j] == 1) targets.add(new int[]{i - 1, j}); // Up
                    if (i < 9 && PlayerMap[i + 1][j] == 1) targets.add(new int[]{i + 1, j}); // Down
                    if (j > 0 && PlayerMap[i][j - 1] == 1) targets.add(new int[]{i, j - 1}); // Left
                    if (j < 9 && PlayerMap[i][j + 1] == 1) targets.add(new int[]{i, j + 1}); // Right
                }
            }
        }
    
        int row, col;
    
        if (!targets.isEmpty()) {
            // If there are prioritized targets, choose one randomly
            int[] target = targets.get((int) (Math.random() * targets.size()));
            row = target[0] + 1;
            col = target[1] + 1;
        } else {
            // Otherwise, choose a random position
            do {
                row = (int) (Math.random() * 10) + 1;
                col = (int) (Math.random() * 10) + 1;
            } while (PlayerMap[row - 1][col - 1] != 0 && PlayerMap[row - 1][col - 1] != 1);
        }
    
        // Attack logic
        if (PlayerMap[row - 1][col - 1] == 1) {
            System.out.println("Hit!");
            PlayerMap[row - 1][col - 1] = 2;
        } else {
            System.out.println("Miss!");
            PlayerMap[row - 1][col - 1] = -1;
        }
    }    
}

