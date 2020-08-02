/**
 * @author Simon
 * @date 2020/8/1 17:20
 */
public class TicTacToe {
    private static final int SIZE = 3;
    private final Character[][] board = {
            {'0', '0', '0'},
            {'0', '0', '0'},
            {'0', '0', '0'}
    };
    private char lastPlayer = '0';

    public String play(int x, int y) {
        checkAxis(x, "X");
        checkAxis(y, "Y");
        lastPlayer = nextPlayer();
        setBox(x, y, lastPlayer);
        if (isWin()) {
            return lastPlayer + " is the winner";
        } else if (isDraw()) {
            return "The result is draw";
        }
        return "No winner";
    }

    private boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '0') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isWin() {
        for (int i = 0; i < SIZE; i++) {
            if (board[0][i] == lastPlayer &&
                    board[1][i] == lastPlayer &&
                    board[2][i] == lastPlayer) {
                return true;
            } else if (board[i][0] == lastPlayer &&
                    board[i][1] == lastPlayer &&
                    board[i][2] == lastPlayer) {
                return true;
            }
            if (board[0][0] == lastPlayer && board[1][1] == lastPlayer && board[2][2] == lastPlayer) {
                return true;
            }
            if (board[0][2] == lastPlayer && board[1][1] == lastPlayer && board[2][0] == lastPlayer) {
                return true;
            }
        }
        return false;
    }

    private void setBox(int x, int y, char lastPlayer) {
        if (board[x - 1][y - 1] != '0') {
            throw new RuntimeException("Here is occupied");
        }
        board[x - 1][y - 1] = lastPlayer;
    }

    private void checkAxis(int axis, String axisName) {
        if (axis < 1 || axis > 3) {
            throw new RuntimeException(axisName + " is outside board");
        }
    }

    public char nextPlayer() {
        if (lastPlayer == 'X') {
            return 'O';
        }
        return 'X';
    }
}
