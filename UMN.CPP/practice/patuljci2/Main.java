/*input
10 3
1 2 1 2 1 2 3 2 3 3
8
1 2
1 3
1 4
1 5
2 5
2 6
6 9
7 10
*/
import java.util.*;
import java.io.*;

public class Main {
    static void runFromFile() {
        final String IODir = "D:/Kien/competitive_programming/io";
        final File inputFile = new File(IODir + "/input.txt");
        final File outputFile = new File(IODir + "/output.txt");
        try {
            System.setIn(new FileInputStream(inputFile));
            System.setOut(new PrintStream(outputFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    static int n, c, m, n_block, block_size;

    static class Query implements Comparable<Query> {
        int l, r, idx, bl, br;
        Query(int l, int r, int idx) {
            this.l = l; this.r = r; this.idx = idx;
            this.bl = l / block_size;
            this.br = r / block_size;
        }
        @Override
        public int compareTo(Query other) {
            return bl == other.bl? br - other.br : bl - other.bl;
        }
    }

    static int[] arr;
    static Query[] q;
    static int[] cnt;
    static TreeMap<Integer, TreeSet<Integer>> mp;

    static void insert(int val) {
        if (mp.containsKey(cnt[val])) {
            mp.get(cnt[val]).remove(val);
            if (mp.get(cnt[val]).isEmpty()) mp.remove(cnt[val]);
        }
        cnt[val]++;
        if (!mp.containsKey(cnt[val])) mp.put(cnt[val], new TreeSet<>());
        mp.get(cnt[val]).add(val);
    }
    static void delete(int val) {
        if (cnt[val] == 0) return;
        mp.get(cnt[val]).remove(val);
        if (mp.get(cnt[val]).isEmpty()) mp.remove(cnt[val]);
        cnt[val]--;
        if (!mp.containsKey(cnt[val])) mp.put(cnt[val], new TreeSet<>());
        mp.get(cnt[val]).add(val);
    }

    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        n = inp.nextInt(); c = inp.nextInt();
        block_size = (int)Math.sqrt(n);
        n_block = (int)(n / block_size);
        arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = inp.nextInt();
        m = inp.nextInt();
        q = new Query[m];
        for (int i = 0; i < m; i++) q[i] = new Query(inp.nextInt() - 1, inp.nextInt() - 1, i);

        Arrays.sort(q);

        cnt = new int[c + 1];
        mp = new TreeMap<>();
        

        int[] ans = new int[m];

        int curL = -1, curR = -1;
        for (int i = 0; i < m; i++) {
            while (curL < q[i].l) {
                if (curL >= 0 && curL < n) delete(arr[curL]);
                curL++;
            }
            while (curL >= q[i].l) {
                if (curL >= 0 && curL < n) insert(arr[curL]);
                curL--;
            }
            while (curR <= q[i].r) {
                if (curR >= 0 && curR < n) insert(arr[curR]);
                curR++;
            }
            while (curR > q[i].r) {
                if (curR >= 0 && curR < n) delete(arr[curR]);
                curR--;
            }
            
            int maxCnt = mp.lastKey();

            System.out.println(maxCnt + " "  + mp.get(maxCnt));
            if (maxCnt > (q[i].r - q[i].l + 1) / 2) 
                ans[q[i].idx] = mp.get(maxCnt).first();
        }
        for (int i = 0; i < m; i++) {
            System.out.println(ans[i] == 0? "no" : ("yes " + ans[i]));
        }
        
        //out.close();
    }
}