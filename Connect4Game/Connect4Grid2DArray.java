import java.util.Arrays;

public class Connect4Grid2DArray implements Connect4Grid {

    final static int ROWS = 6;
    final static int COLUMNS = 7;

    ConnectPlayer currentPlayer;
    Character [][] grid;
    int rows;
    int columns;
    int currentRow;
    int currentColumn;

    Connect4Grid2DArray() {
        emptyGrid();
        rows = grid.length;
        columns = grid[0].length;
    }

    @Override
    public void emptyGrid() {
        grid = new Character[ROWS][COLUMNS];
        for (Character[] characters : grid)
            Arrays.fill(characters, '0');
    }

    @Override
    public boolean isValidColumn(int column) {
        column-=1;
        if(column<0 || column>columns-1)
            return false;
        else return !isColumnFull(column);
    }

    @Override
    public boolean isColumnFull(int column) {
        return grid[0][column] != '0';
    }

    @Override
    public void dropPiece(ConnectPlayer player, int column) {
        currentPlayer=player;
        for(int row = rows-1; row >= 0; row--)
        {
            if(grid[row][column]=='0')
            {
                grid[row][column] = player.getColour();
                currentRow = row;
                currentColumn = column;
                break;
            }
        }
        player.moves++;
    }

    @Override
    public boolean didLastPieceConnect4() {
        return checkVertical() ||
                checkHorizontal() ||
                checkDiagonals();
    }

    @Override
    public boolean isGridFull() {
        return(!Arrays.deepToString(grid).contains("0"));
    }

    @Override
    public String toString() {
        String grid = "1 2 3 4 5 6 7\n-------------\n";
        for (int i = 0; i<rows; i++)
        {
            for (int j = 0; j<columns; j++)
                grid += this.grid[i][j] + " ";
            grid += "\n";
        }
        return grid;
    }

    public boolean checkVertical() {
        switch (currentRow)
        {
            case 0 -> checkLowerVertical();
            case 5 -> checkUpperVertical();
            default -> {
                checkUpperVertical();
                checkLowerVertical();
            }
        }
        if(currentPlayer.connected>=3)
        {
            currentPlayer.resetConnected();
            return true;
        }
        else
            currentPlayer.resetConnected();
        return false;
    }

    public void checkUpperVertical() {
        for (int row = currentRow - 1; (row >= 0) && (grid[row][currentColumn] == currentPlayer.colour); row--)
        {
            if (grid[row][currentColumn] == currentPlayer.colour)
                currentPlayer.connected++;
        }
    }

    public void checkLowerVertical() {
        for(int row = currentRow+1; (row <= 5) && (grid[row][currentColumn] == currentPlayer.colour); row++)
        {
            if(grid[row][currentColumn]== currentPlayer.colour)
                currentPlayer.connected++;
        }
    }

    public boolean checkHorizontal() {
        switch (currentColumn)
        {
            case 0 -> checkRightHorizontal();
            case 6 -> checkLeftHorizontal();
            default -> {
                checkRightHorizontal();
                checkLeftHorizontal();
            }
        }
        if(currentPlayer.connected>=3)
        {
            currentPlayer.resetConnected();
            return true;
        }
        else
            currentPlayer.resetConnected();
        return false;
    }

    public void checkRightHorizontal() {
        for (int col = currentColumn + 1; (col <= 6) && (grid[currentRow][col] == currentPlayer.colour); col++)
        {
            if (grid[currentRow][col] == currentPlayer.colour)
                currentPlayer.connected++;
        }
    }

    public void checkLeftHorizontal() {
        for (int col = currentColumn - 1; (col >= 0) && (grid[currentRow][col] == currentPlayer.colour); col--)
        {
            if (grid[currentRow][col] == currentPlayer.colour)
                currentPlayer.connected++;
        }
    }

    public boolean checkDiagonals() {
        if(currentColumn==0 || currentColumn==columns-1 || currentRow==0 || currentRow==rows-1)
        {
            checkColumnDiagonal();
            checkRowDiagonal();
        }
        else
            checkAllDiagonals();
        if(currentPlayer.connected>=3)
        {
            currentPlayer.resetConnected();
            return true;
        }
        else
            currentPlayer.resetConnected();
        return false;
    }

    public void checkColumnDiagonal() {
        switch (currentColumn) {
            case 0 -> {
                checkUpperRight();
                checkLowerRight();
            }
            case 6 -> {
                checkUpperLeft();
                checkLowerLeft();
            }
            default -> checkAllDiagonals();
        }
    }

    public void checkRowDiagonal() {
        switch (currentRow) {
            case 0 -> {
                checkLowerRight();
                checkLowerLeft();
            }
            case 5 -> {
                checkUpperRight();
                checkUpperLeft();
            }
            default -> checkAllDiagonals();
        }
    }

    public void checkAllDiagonals() {
        checkUpperRight();
        checkLowerRight();
        checkUpperLeft();
        checkLowerLeft();
    }

    public void checkLowerLeft() {
        int row = currentRow+1;
        int col = currentColumn-1;
        while ((col >= 0) && (row <= 5) && (grid[row][col] == currentPlayer.colour))
        {
            checkConnection(row, col);
            row++;
            col--;
        }
    }

    public void checkUpperLeft() {
        int row = currentRow-1;
        int col = currentColumn-1;
        while ((col >= 0) && (row >= 0) && (grid[row][col] == currentPlayer.colour))
        {
            checkConnection(row, col);
            row--;
            col--;
        }
    }

    public void checkLowerRight() {
        int row = currentRow+1;
        int col = currentColumn+1;
        while ((col <= 6) && (row <= 5) && (grid[row][col] == currentPlayer.colour))
        {
            checkConnection(row, col);
            row++;
            col++;
        }
    }

    public void checkUpperRight() {
        int row = currentRow-1;
        int col = currentColumn+1;
        while ((col <= 6) && (row >= 0) && (grid[row][col] == currentPlayer.colour))
        {
            checkConnection(row, col);
            row--;
            col++;
        }
    }

    public void checkConnection(int row, int col) {
        if(grid[row][col]==currentPlayer.getColour())
            currentPlayer.connected++;
    }


}
