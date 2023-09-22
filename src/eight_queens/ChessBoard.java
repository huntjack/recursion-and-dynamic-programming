package eight_queens;

import java.util.List;

public class ChessBoard {
    private static final int boardSize = 8;
    private int[] chessBoard;
    public ChessBoard() {
        chessBoard = new int[boardSize];
    }
    public void modelState(List<Integer> state) {
        for(int i = 0; i < state.size(); i++) {
            int row = i;
            int column = state.get(i);
            addQueen(row, column);
        }
    }
    private void addQueen(int row, int column) {
        setSquare(row, column);
        int upperLeft = column;
        int upperRight = column;
        for(int i = row - 1; i >= 0; i--) {
            if(--upperLeft >= 0) {
                setSquare(i, upperLeft);
            }
            if(++upperRight < boardSize) {
                setSquare(i, upperRight);
            }
        }
        int lowerLeft = column;
        int lowerRight = column;
        for(int i = row + 1; i < boardSize; i++) {
            if(--lowerLeft >= 0) {
                setSquare(i, lowerLeft);
            }
            if(++lowerRight < boardSize) {
                setSquare(i, lowerRight);
            }
        }
    }
    private void setSquare(int rowIndex, int column) {
        int row = chessBoard[rowIndex];
        chessBoard[rowIndex] = row | (1 << column);
    }
    public boolean isAvailable(int row, int column) {
        return (chessBoard[row] & (1 << column)) == 0;
    }
}
