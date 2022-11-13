import java.util.Scanner;

public class Connect4Game {

    public static boolean quitGame;

    public static Connect4Grid2DArray grid;
    public static ConnectPlayer player1;
    public static ConnectPlayer player2;

    public static void main(String[] args) throws InterruptedException {

        Scanner input = new Scanner(System.in);
        boolean quit = false;

        while (!quit)
        {
            System.out.print("Insert play/quit > ");
            if (input.hasNext())
            {
                String answer = input.next();
                printLines(2);
                if (answer.equalsIgnoreCase("play"))
                {
                    grid = new Connect4Grid2DArray();
                    setPlayerType(input);
                    System.out.print(grid);
                    gameLoop();
                    quit = true;
                    System.out.println("GAME OVER");
                }
                else if (answer.equalsIgnoreCase("quit"))
                    quit = true;
                else
                    input.nextLine();
            }
        }
        input.close();

    }

    public static void gameLoop() throws InterruptedException {
        while(!quitGame)
        {
            if(grid.isGridFull())
                quitGame=true;
            else if(!quitGame)
            {
                player1.makeMove(grid);
                if(!quitGame)
                {
                    System.out.print(grid);
                    if(player1.moves>3 && grid.didLastPieceConnect4())
                    {
                        System.out.println("Player 1 wins");
                        quitGame=true;
                        return;
                    }
                    player2.makeMove(grid);
                    System.out.print(grid);
                    if(player2.moves>3 && grid.didLastPieceConnect4())
                    {
                        System.out.println("Player 2 wins");
                        quitGame=true;
                        return;
                    }
                }

            }
        }
    }

    public static void setPlayerType(Scanner input) {
        boolean isValid = true;
        do
        {
            System.out.println("TYPES OF PLAYER\n-> Human\n-> AI");
            System.out.println();
            System.out.print("Choose type for Player 1 > ");
            String answer1 = input.next();
            System.out.print("Choose type for Player 2 > ");
            String answer2 = input.next();
            printLines(20);
            if (answer1.equalsIgnoreCase("Human"))
                player1 = new C4HumanPlayer('R');
            else if (answer1.equalsIgnoreCase("AI"))
                player1 = new C4RandomAIPlayer('R', 1);
            else
                isValid = false;
            if (answer2.equalsIgnoreCase("Human"))
                player2 = new C4HumanPlayer('G');
            else if (answer2.equalsIgnoreCase("AI"))
                player2 = new C4RandomAIPlayer('G', 2);
            else
                isValid = false;
            if(player1!=null && player2!=null)
                isValid = true;
            input.nextLine();
        }
        while (!isValid);
    }

    public static void printLines(int lines) {
        for(int i = 0; i<lines; i++)
            System.out.println();
    }

}
