import java.util.*;
class Main {
    int solve(int m, int n) {
        int sz = m * n;
        if (m < 2 || n < 2) return sz;
        if (m == 2 || n == 2)
        return (sz + (sz % 2)) / 2;
    }
    public static void main(String[] args) {
        Main obj = new Main();
        int m, n;
        Scanner inp = new Scanner(System.in);
        while (true) {
            m = inp.nextInt(); n = inp.nextInt();
            if (m == 0 && n == 0) break;
            System.out.format("%d knights may be placed on a %d row %d column board.", obj.solve(m, n), m, n);
        }
    } 
}