import java.util.Scanner;
public class NeighborGame {
    private int[][] grid;
    public NeighborGame() {}

    public void play() {
        Scanner scan = new Scanner(System.in);

        boolean run = true;

        while (run) {
            grid = new int[5][5];

            int total = 0;
            int count = 0;

            for (int i = 0; i < 25; i++) {
                count++;
                System.out.println("-------------------ROLL " + count + "-------------------");
                int roll = roll();

                printGrid();

                System.out.println("The dice rolled: " + roll);
                boolean hasChosen = false;
                while(!hasChosen) {
                    System.out.println("Pick a row: ");
                    int row = scan.nextInt() - 1;
                    System.out.println("Pick a column");
                    int col = scan.nextInt() - 1;
                    if (isValid(row, col)) {
                        grid[row][col] = roll;
                        hasChosen = true;
                    }
                    else {
                        System.out.println("Pick a valid row and column!");
                    }
                }
            }

            //Tallies the ROWS
            for (int i = 0; i < grid.length; i++) {
                int subTotal = 0;
                for (int j = 1; j < grid[i].length - 1; j++) {
                    if (grid[i][j] == grid[i][j - 1] || grid[i][j] == grid[i][j + 1]) {
                        subTotal += grid[i][j];
                    }
                }
                total += subTotal;
            }

            //Tallies the COLUMNS
            for (int i = 0; i < grid[0].length; i++) {
                int subTotal = 0;
                for (int j = 1; j < grid.length-1; j++) {
                    if (grid[j][i] == grid[j-1][i] || grid[j][i] == grid[j+1][i]) {
                        subTotal += grid[j][i];
                    }
                }
                total += subTotal;
            }

            System.out.println("Game end!");
            System.out.println("Your score: " + total);
            System.out.println();
            System.out.println("Play again? y/n");
            scan.nextLine();
            String replay = scan.nextLine();

            if (replay.equals("n")) {
                run = false;
            }
        }
        System.out.println();
        System.out.println("Thanks for playing!");

    }

    private int roll() {
        return (int)(Math.random()*10+1);
    }

    public boolean isValid(int row, int col) {
        if (row >= grid.length || row < 0) {
            return false;
        }
        if (col >= grid[0].length || col < 0) {
            return false;
        }
        return grid[row][col] == 0;
    }

    private void printGrid() {
        int rowNum = 1;
        System.out.println("   1  2  3  4  5");

        for (int i = 0; i < grid.length; i ++) {
            System.out.print(rowNum +" ");
            rowNum++;
            for (int j = 0; j < grid[i].length; j++) {
                System.out.print("[" + grid[i][j] + "]");
            }
            System.out.println();
        }
    }
}
