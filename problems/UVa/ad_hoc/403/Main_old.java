import java.util.*;
import java.io.*;

public class Main {
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
    final String[] C5batchAtoI = new String[] {
        ".***.. ****.. .****. ****.. *****. *****. .****. *...*. *****.",
        "*...*. *...*. *...*. *...*. *..... *..... *..... *...*. ..*...",
        "*****. ****.. *..... *...*. ***... ***... *..**. *****. ..*...",
        "*...*. *...*. *..... *...*. *..... *..... *...*. *...*. ..*...",
        "*...*. ****.. .****. ****.. *****. *..... .***.. *...*. *****."
    };
    final String AtoI = "ABCDEFGHI";
    final String[] C5batchJtoR = new String[] {
        "..***. *...*. *..... *...*. *...*. .***.. ****.. .***.. ****..",
        "...*.. *..*.. *..... **.**. **..*. *...*. *...*. *...*. *...*.",
        "...*.. ***... *..... *.*.*. *.*.*. *...*. ****.. *...*. ****..",
        "*..*.. *..*.. *..... *...*. *..**. *...*. *..... *..**. *..*..",
        ".**... *...*. *****. *...*. *...*. .***.. *..... .****. *...*."
    };
    final String JtoR = "JKLMNOPQR";
    final String[] C5batchStoEnd = new String[] {
        ".****. *****. *...*. *...*. *...*. *...*. *...*. *****. ......",
        "*..... *.*.*. *...*. *...*. *...*. .*.*.. .*.*.. ...*.. ......",
        ".***.. ..*... *...*. .*.*.. *.*.*. ..*... ..*... ..*... ......",
        "....*. ..*... *...*. .*.*.. **.**. .*.*.. ..*... .*.... ......",
        "****.. .***.. .***.. ..*... *...*. *...*. ..*... *****. ......"
    };
    final String StoEnd = "STUVWXYZ .*";
    final String C1chars = AtoI + JtoR + StoEnd;
    HashMap<Character, char[][]> mp;
    void init() {
        mp = new HashMap<>();
        for (int r = 0; r < 5; r++) {
            String[] curRows = C5batchAtoI[r].split(" ");
            assert curRows.length == AtoI.length();
            for (int i = 0; i < curRows.length; i++) {
                char[][] font = mp.get(AtoI.charAt(i));
                if (font == null) font = new char[5][6];
                font[r] = curRows[i].toCharArray();
                mp.put(AtoI.charAt(i), font);
            }

            curRows = C5batchJtoR[r].split(" ");
            assert curRows.length == JtoR.length();
            for (int i = 0; i < curRows.length; i++) {
                char[][] font = mp.get(JtoR.charAt(i));
                if (font == null) font = new char[5][6];
                font[r] = curRows[i].toCharArray();
                mp.put(JtoR.charAt(i), font);
            }

            curRows = C5batchStoEnd[r].split(" ");
            assert curRows.length == StoEnd.length();
            for (int i = 0; i < curRows.length; i++) {
                char[][] font = mp.get(StoEnd.charAt(i));
                if (font == null) font = new char[5][6];
                font[r] = curRows[i].toCharArray();
                mp.put(StoEnd.charAt(i), font);
            }
        }
    }
    void printGrid(char[][] grid) {
        //System.out.println(grid.length);
        //System.out.println(grid[0].length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) System.out.print(grid[i][j]);
            System.out.println();
        }
    }
    boolean placeCharTopLeft(char[][] curGrid, char c, int row, int col) {
        if (row < 0 || row >= curGrid.length || col < 0 || col >= curGrid[0].length) return false;
        char[][] font = mp.get(c);
        for (int i = row; i < row + 5 && i < curGrid.length; i++) 
            for (int j = col; j < col + 6 && j < curGrid[i].length; j++) curGrid[i][j] = font[i - row][j - col];
        return true;
    }
    boolean placeCharTopRight(char[][] curGrid, char c, int row, int col) {
        if (row < 0 || row >= curGrid.length || col < 0 || col >= curGrid[0].length) return false;
        char[][] font = mp.get(c);
        for (int i = row; i < row + 5 && i < curGrid.length; i++) 
            for (int j = col; j >= col - 5 && j >= 0; j--) curGrid[i][j] = font[i - row][j - (col - 5)];
        return true;
    }
    boolean placeStringC1TopLeft(char[][] curGrid, String str, int row, int col) {
        if (row < 0 || row >= curGrid.length || col < 0 || col >= curGrid[0].length) return false;
        for (int j = col; j < col + str.length() && j < curGrid[row].length; j++)
            curGrid[row][j] = str.charAt(j - col);
        return true;
    }
    boolean placeStringC1TopRight(char[][] curGrid, String str, int row, int col) {
        if (row < 0 || row >= curGrid.length || col < 0 || col >= curGrid[0].length) return false;
        for (int j = col; j >= col - str.length() + 1 && j >= 0; j--)
            curGrid[row][j] = str.charAt(str.length() - 1 - (col - j));
        return true;
    }
    boolean placeStringC5TopLeft(char[][] curGrid, String str, int row, int col) {
        if (row < 0 || row >= curGrid.length || col < 0 || col >= curGrid[0].length) return false;
        for (int i = 0; i < str.length(); i++) {
            int curCol = col + i * 6;
            if (!placeCharTopLeft(curGrid, str.charAt(i), row, curCol)) break;
        }
        return true;
    }
    boolean placeStringC5TopRight(char[][] curGrid, String str, int row, int col) {
        if (row < 0 || row >= curGrid.length || col < 0 || col >= curGrid[0].length) return false;
        for (int i = str.length() -1; i >= 0; i--) {
            int curCol = col - (str.length() - 1 - i) * 6;
            if (!placeCharTopRight(curGrid, str.charAt(i), row, curCol)) break;
        }
        return true;
    }
    void solve(char[][] curGrid, String line) {
        String[] cmds;
        if (line.charAt(1) == 'P') cmds = line.split(" ", 5);
        else cmds = line.split(" ", 4);
        System.out.println(Arrays.toString(cmds));

        String str = cmds[cmds.length - 1];
        str = str.substring(1, str.length() - 1);
        int row = Integer.parseInt(cmds[2]) - 1, col;
        if (cmds[0].equals(".P") || cmds[0].equals(".L")) {
            col = cmds[0].equals(".P")? Integer.parseInt(cmds[2]) : 1;
            col--;
            if (cmds[1].equals("C1")) placeStringC1TopLeft(curGrid, str, row, col);
            else if (cmds[1].equals("C5")) placeStringC5TopLeft(curGrid, str, row, col);
        }
        else if (cmds[0].equals(".R")) {
            col = 60 - 1;
            if (cmds[1].equals("C1")) placeStringC1TopRight(curGrid, str, row, col);
            else if (cmds[1].equals("C5")) placeStringC5TopRight(curGrid, str, row, col);
        }
        else if (cmds[0].equals(".C")) {
            if (cmds[1].equals("C1")) {
                int mid = (str.length() - str.length() % 2) / 2;
                System.out.println(str.substring(0, centerLeft + 1) + ", " + str.substring(centerRight));
                if (str.length() % 2 == 1) curGrid[row][30] = str.charAt(mid);
                if (cmds[1].equals("C1")) {
                    placeStringC1TopRight(curGrid, str.substring(0, centerLeft + 1), row, 29);
                    placeStringC1TopLeft(curGrid, str.substring(centerRight), row, 30 + str.length() % 2);
                }
                else if (cmds[1].equals("C5")) {
                    placeStringC5TopRight(curGrid, str.substring(0, centerLeft + 1), row, 29);
                    placeStringC5TopLeft(curGrid, str.substring(centerRight), row, 30 + str.length() % 2);
                }
            }
        }
    }
    void resetGrid(char[][] curGrid) {
        for (int i = 0; i < curGrid.length; i++)
            Arrays.fill(curGrid[i], '.');
    }
    public static void main(String[] args) {
        Main obj = new Main();
        obj.runFromFile();
        Scanner inp = new Scanner(System.in);
        obj.init();
        //obj.printGrid(obj.mp.get('B'));
        char[][] ans = new char[60][60];
        obj.resetGrid(ans);
        //obj.printGrid(ans);
        while (inp.hasNextLine()) {
            String s;
            s = inp.nextLine();
            if (s.isEmpty()) continue;
            if (s.equals(".EOP")) {
                //obj.placeStringC5TopRight(ans, "FUCK", 3, 20);
                obj.printGrid(ans);
                obj.resetGrid(ans);
            }
            else obj.solve(ans, s);
        }
    }
}
