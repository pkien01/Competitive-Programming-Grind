/*input
5k1r/2q3p1/p3p2p/1B3p1Q/n4P2/6P1/bbP2N1P/1K1RR3
rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR
*/
import java.util.*;
import java.io.*;

class Main {
    void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    char[][] board;
    boolean[][] check;
    boolean validPos(int r, int c) {
    	return r >= 0 && r < 8 && c >= 0 && c < 8 && board[r][c] == '.';
    }    
    void markRow(int r, int c) {
    	for (int i = c - 1; i >= 0 && board[r][i] == '.'; i--) check[r][i] = true;
    	for (int i = c + 1; i < 8 && board[r][i] == '.'; i++) check[r][i] = true;
    }
	void markCol(int r, int c) {
		for (int i = r - 1; i >= 0 && board[i][c] == '.'; i--) check[i][c] = true;
		for (int i = r + 1; i < 8 && board[i][c] == '.'; i++) check[i][c] = true;
	}
	void markDiag(int r, int c) {
		for (int i = 1; i < 8; i++) {
			if (r + i < 8 && c + i < 8 && board[r + i][c + i] == '.') check[r + i][c + i] = true;
			else break;
		}
		for (int i = 1; i < 8; i++) {
			if (r - i >= 0 && c - i >= 0 && board[r - i][c - i] == '.') check[r - i][c - i] = true;
			else break;
		}
		for (int i = 1; i < 8; i++) {
			if (r + i < 8 && c - i >= 0 && board[r + i][c - i] == '.') check[r + i][c - i] = true;
			else break;
		}
		for (int i = 1; i < 8; i++) {
			if (r - i >= 0 && c + i < 8 && board[r - i][c + i] == '.') check[r - i][c + i] = true;
			else break;
		}
	}
	void markKing(int r, int c) {
		for (int dr = -1; dr <= 1; dr++)
			for (int dc = -1; dc <= 1; dc++) 
				if ((dr != 0 || dc != 0) && validPos(r + dr, c + dc)) check[r + dr][c + dc] = true;
	} 
	void markKnight(int r, int c) {
		final int[] dr = {-2, -2, -1, -1,  1, 1,  2, 2};
		final int[] dc = {-1,  1, -2,  2, -2, 2, -1, 1};
		for (int i = 0; i < 8; i++)
			if (validPos(r + dr[i], c + dc[i])) check[r + dr[i]][c + dc[i]] = true;
	}
	void markPawn(int r, int c, boolean isBlack) {
		if (isBlack) {
			if (validPos(r + 1, c - 1)) check[r + 1][c - 1] = true;
			if (validPos(r + 1, c + 1)) check[r + 1][c + 1] = true;
		}
		else {
			if (validPos(r - 1, c - 1)) check[r - 1][c - 1] = true;
			if (validPos(r - 1, c + 1)) check[r - 1][c + 1] = true;
		}
	}
    int solve(String fen) {
    	String[] rows = fen.split("/");
    	//System.out.println(Arrays.toString(rows));
    	check = new boolean[8][8];
    	board = new char[8][8];

    	for (int r = 0; r < 8; r++) {
    		int c = 0;
    		if (r >= rows.length) {
    			for (int i = 0; i < 8; i++) board[r][i] = '.';
    			continue; 
    		}
    		for (int i = 0; i < rows[r].length(); i++) {
    			char cur = rows[r].charAt(i);
    			if (Character.isDigit(cur)) {
    				for (int j = 0; j < (int)cur - '0'; j++) {
    					board[r][c] = '.';
    					c++;
    				}
    			}
    			else {
    				board[r][c] = cur;
    				c++;
    			}
    		}
    	}
    	for (int r = 0; r < 8; r++) {
    		for (int c = 0; c < 8; c++) {
    			if (board[r][c] == '.') continue;
    			check[r][c] = true;
    			if (board[r][c] == 'k' || board[r][c] == 'K') markKing(r, c);
    			else if (board[r][c] == 'q' || board[r][c] == 'Q') {
    				markRow(r, c); markCol(r, c); markDiag(r, c);
    			}
    			else if (board[r][c] == 'r' || board[r][c] == 'R') {
    				markRow(r, c); markCol(r, c);
    			}
    			else if (board[r][c] == 'b' || board[r][c] == 'B') {
    				markDiag(r, c);
    			}
    			else if (board[r][c] == 'n' || board[r][c] == 'N') markKnight(r, c);
    			else {
    				assert board[r][c] == 'p' || board[r][c] == 'P';
    				markPawn(r, c, Character.isLowerCase(board[r][c]));
    			}
    		}
    	}
    	//for (int i = 0; i < 8; i++) System.out.println(Arrays.toString(check[i]));
    	int res = 0;
    	for (int r = 0; r < 8; r++)
    		for (int c = 0; c < 8; c++) if (!check[r][c]) res++;
    	return res;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        while (inp.hasNextLine()) {
        	String s = inp.nextLine();
        	System.out.println(obj.solve(s));
        }
    }
}