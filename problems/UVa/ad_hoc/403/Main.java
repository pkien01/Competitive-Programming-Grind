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
    final String StoEnd = "STUVWXYZ ";
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
    char[][] makeC5Grid(String str) {
        char[][] res = new char[5][6 * str.length()];
        for (int i = 0; i < str.length(); i++) {
            char[][] font = mp.get(str.charAt(i));
            for (int r = 0; r < 5; r++)
                for (int c = 0; c < 6; c++) res[r][c + i * 6] = font[r][c];
        }
        return res;
    }
    char[][] makeC1Grid(String str) {
        char[][] res = new char[1][str.length()];
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') res[0][i] = '.';
            else res[0][i] = str.charAt(i);
        }
        return res;
    }
    void placeTopLeft(char[][] curGrid, int row, int col, char[][] strGrid) {
        for (int r = 0; r < strGrid.length && row + r < curGrid.length; r++)
            for (int c = 0; c < strGrid[0].length && col + c < curGrid[0].length; c++) 
                if (strGrid[r][c] != '.') curGrid[row + r][col + c] = strGrid[r][c];
    }
    void placeTopRight(char[][] curGrid, int row, int col, char[][] strGrid) {
        for (int r = 0; r < strGrid.length && row + r < curGrid.length; r++) 
            for (int c = strGrid[0].length - 1; c >= 0 && col - (strGrid[0].length - 1 - c) >= 0; c--)
                if (strGrid[r][c] != '.') curGrid[row + r][col - (strGrid[0].length - 1 - c)] = strGrid[r][c];
    }
    void placeCenter(char[][] curGrid, int row, char[][] strGrid) {
        int odd = strGrid[0].length % 2;
        int mid = (strGrid[0].length - odd) / 2;
        for (int r = 0; r < strGrid.length && row + r < curGrid.length; r++) {
            for (int c = mid - 1; c >= 0 && 30 - (mid - c) >= 0; c--) 
            if (strGrid[r][c] != '.') curGrid[row + r][30 - (mid - c)] = strGrid[r][c];
            
            if (odd == 1 && strGrid[r][mid] != '.') curGrid[row + r][30] = strGrid[r][mid];
            
            for (int c = mid + odd; c < strGrid[0].length && 30 + odd + (c - mid - odd) < curGrid[0].length; c++) 
                if (strGrid[r][c] != '.')curGrid[row + r][30 + odd + (c - mid - odd)] = strGrid[r][c];
        }
    }
    void solve(char[][] curGrid, String line) {
        String[] cmds;
        if (line.charAt(1) == 'P') cmds = line.split(" ", 5);
        else cmds = line.split(" ", 4);
        //System.out.println(Arrays.toString(cmds));

        String str = cmds[cmds.length - 1];
        str = str.substring(1, str.length() - 1);
        int row = Integer.parseInt(cmds[2]) - 1, col;
        if (cmds[0].equals(".P") || cmds[0].equals(".L")) {
            col = cmds[0].equals(".P")? Integer.parseInt(cmds[3]) : 1;
            col--;
            if (cmds[1].equals("C1")) placeTopLeft(curGrid, row, col, makeC1Grid(str));
            else if (cmds[1].equals("C5")) placeTopLeft(curGrid, row, col, makeC5Grid(str));
        }
        else if (cmds[0].equals(".R")) {
            col = 60 - 1;
            if (cmds[1].equals("C1")) placeTopRight(curGrid, row, col, makeC1Grid(str));
            else if (cmds[1].equals("C5")) placeTopRight(curGrid, row, col, makeC5Grid(str));
        }
        else if (cmds[0].equals(".C")) {
            if (cmds[1].equals("C1")) placeCenter(curGrid, row, makeC1Grid(str));
            else placeCenter(curGrid, row, makeC5Grid(str));
        }
    }
    void resetGrid(char[][] curGrid) {
        for (int i = 0; i < curGrid.length; i++)
            Arrays.fill(curGrid[i], '.');
    }
    public static void main(String[] args) {
        Main obj = new Main();
        //obj.runFromFile();
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
                System.out.println();
                for (int i = 0; i < 60; i++) System.out.print('-');
                System.out.println("\n");
                obj.resetGrid(ans);
            }
            else obj.solve(ans, s);
        }
    }
}
