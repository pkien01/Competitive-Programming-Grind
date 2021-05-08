import java.util.*;
import java.io.*;

class Main {
    int[] solve(int s, int d) { 
        if (s < d) return new int[]{};
        if (s % 2 != d % 2) return new int[]{};
        return new int[]{(s + d) / 2, (s - d) / 2};
    }
    public static void main(String[] args) {
        Main obj = new Main();
        Scanner inp = new Scanner(System.in);
        int n = inp.nextInt();
        while (n-- > 0) {
            int s = inp.nextInt(), d = inp.nextInt();
            int[] ans = obj.solve(s, d);
            System.out.println(ans.length == 0 ? "impossible" : (ans[0] + " " + ans[1]));
        }
    }
}