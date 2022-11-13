import java.util.Scanner;

public class C4HumanPlayer extends ConnectPlayer {

    public static final int QUIT_PARAMETER = -1;
    Scanner input = new Scanner(System.in);

    C4HumanPlayer(Character colour) {
        super.colour=colour;
    }

    @Override
    public void makeMove(Connect4Grid2DArray grid) {
        int column = chooseColumn(grid);
        if(column!=QUIT_PARAMETER)
            grid.dropPiece(this, column);
        else
            Connect4Game.quitGame=true;
    }

    public int chooseColumn(Connect4Grid2DArray grid) {
        boolean quit = false;
        int colChoice;
        String altAnswer;
        while(!quit)
        {
            Connect4Game.printLines(2);
            System.out.print("Choose column (1-7) or type 'quit' to stop > ");
            if (input.hasNextInt())
            {
                colChoice = input.nextInt();
                if(grid.isValidColumn(colChoice))
                {
                    Connect4Game.printLines(20);
                    return colChoice - 1;
                }
                else
                    System.out.println("Invalid column");
            }
            else
            {
                altAnswer = input.next();
                if (altAnswer.equalsIgnoreCase("quit"))
                    quit = true;
                else
                {
                    System.out.println("Please enter a number (1-7)");
                    input.nextLine();
                }
            }
            Connect4Game.printLines(20);
        }
        return QUIT_PARAMETER;
    }
}
