/*input
3
ab
bc
abc
aa
bb
ab
aab
acb
bcaabacbc
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

    static class Hash {
    	long[] h, pw;
    	final long base = 41L, mod = (long)1e9 + 9;
    	Hash(String str) {
    		h = new long[str.length() + 1];
    		pw = new long[str.length() + 1];
    		h[0] = 0; pw[0] = 1;
    		for (int i = 1; i <= str.length(); i++) {
    			h[i] = (base * h[i - 1] % mod + str.charAt(i - 1) - 'a') % mod;
    			pw[i] = pw[i - 1] * base % mod;
    		}
    	}
    	long get(int l, int r) {
    		return (h[r + 1] - h[l] * pw[r - l + 1] % mod + mod) % mod;
    	}	
    	long get(int i) {
    		return h[i + 1];
    	}
    }

    static String p, q, s;	

    static long solve() {
    	Hash hp = new Hash(p), hq = new Hash(q), hs = new Hash(s);
    	int[] cnt_p = new int[s.length() + 1], cnt_q = new int[s.length() + 1];
    	for (int i = 0; i < s.length(); i++) {
    		int l = i, r = s.length() - 1;
    		while (l <= r) {
    			int mid = (l + r) >> 1;
    			if (mid - i < p.length() && hs.get(i, mid) == hp.get(mid - i)) l = mid + 1;
    			else r = mid - 1;
    		}
    		if (l - 1 >= i) {
    			cnt_p[i] += 1;
    			cnt_p[l] -= 1;
    		} 
    	}
		for (int i = 1; i < s.length(); i++) cnt_p[i] += cnt_p[i - 1];

        int[] dup = new int[Math.min(p.length(), q.length())];
        for (int i = 0; i < dup.length; i++) 
            dup[i] = (i == 0? 0: dup[i - 1]) + (hp.get(i) == hq.get(i)? 1 : 0);
    	
        System.out.println(Arrays.toString(dup));
        long ans = 0;
        for (int i = 0; i < s.length(); i++) {
            int l = i, r = s.length() - 1;
            while (l <= r) {
                int mid = (l + r) >> 1;
                if (mid - i < q.length() && hs.get(i, mid) == hq.get(mid - i)) l = mid + 1;
                else r = mid - 1;
            }
            //System.out.println(i + " " + (l - 1));
            int lp = i == 0? 0 : cnt_p[i - 1], lq = l > i? l - i : 0;
            System.out.println(s.charAt(i) + " " + lp + " " + lq + " " + (lq == 0? 0 : dup[lq - 1]));
            ans += lp * lq + lp + lq - (lq == 0? 0 : dup[lq - 1]);
        }

    	return ans + 1;
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        int numTests = inp.nextInt();
        while (numTests-- > 0) {
        	p = inp.next(); q = inp.next(); s = inp.next();
        	System.out.println(solve());
        }
        
        //out.close();
    }
}