/*input
5
1 0
5 2
6 6
2 1
6 1
*/

import java.util.*;
import java.io.*;

public class Main {
    
    static int n, k;
    static int maxPeak;
    
	public static void main (String[] args) {
        Scanner scan = new Scanner(System.in);
        int numTestCases = scan.nextInt();
        while(numTestCases-- > 0) {
            n = scan.nextInt();
            k = scan.nextInt();
            
            /*
            maxPeak = 0;
            if(n%2 == 0) {
                maxPeak = n/2 - 1;
            } else {
                maxPeak = n/2;
            }
            
            if(k > maxPeak) {
                System.out.println(-1);
            } else {
                solve();
            }*/
            solve();
        }
	}
    
    
    private static void solve() {
        int[] perm = new int[n+1];
        for(int i = 0; i < n; i++) {
            perm[i] = i + 1;
        }
        for(int i = 0; i < n - 1; i++) {
            if(k == 0) {
                break;
            }
            if(i%2 == 1) {
                int temp = perm[i+1];
                perm[i+1] = perm[i];
                perm[i] = temp;
                k--;  
            }
        }
        
        if (k > 0) {
            System.out.println("-1");
        }
        else {
            for(int i = 0; i < n; i++) {
                System.out.print(perm[i] + " ");
            }
            System.out.println();
        }
    }
    
}