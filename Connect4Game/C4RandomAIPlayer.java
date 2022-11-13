import java.util.ArrayList;
import java.util.Random;

public class C4RandomAIPlayer extends ConnectPlayer {

    Random randomizer = new Random();

    C4RandomAIPlayer(Character colour, int number) {
        super.colour=colour;
        super.playerNumber=number;
    }

    @Override
    public void makeMove(Connect4Grid2DArray grid) throws InterruptedException {
        ArrayList<Integer> validColumns = new ArrayList<>(getValidColumns(grid));
        Connect4Game.printLines(20);
        System.out.printf("Player %d is moving...", playerNumber);
        Thread.sleep(1000);
        Connect4Game.printLines(20);
        if(validColumns.size()>0)
            grid.dropPiece(this, randomizeColumn(validColumns)-1);
    }

    public ArrayList<Integer> getValidColumns(Connect4Grid2DArray grid) {
        ArrayList<Integer> validColumns = new ArrayList<>();
        for (int i = 1; i<=grid.columns; i++)
        {
            if(grid.isValidColumn(i))
                validColumns.add(i);
        }
        return validColumns;
    }

    public int randomizeColumn(ArrayList<Integer> validColumns) {
        return validColumns.get(randomizer.nextInt(validColumns.size()));
    }

}
