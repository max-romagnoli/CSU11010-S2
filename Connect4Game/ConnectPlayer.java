public abstract class ConnectPlayer {

    int playerNumber;
    Character colour;
    int connected;
    int moves;

    public abstract void makeMove(Connect4Grid2DArray grid) throws InterruptedException;

    public Character getColour() {
        return colour;
    }

    public void resetConnected() {
        connected=0;
    }

}
