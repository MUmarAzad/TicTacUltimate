public class Board {
    public char[][] grid = new char[3][3];
    public int game;

    public Board() {
        game = 0;
    }
/*
    public void turn(String player) {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == 'X') {
                    GUI.drawX(g, col * 100, row * 100);
                } else if (board[row][col] == 'O') {
                    GUI.drawO(g, col * 100, row * 100);
                }
            }
        }
    }*/
}