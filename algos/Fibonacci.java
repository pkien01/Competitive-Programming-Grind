// Find the element at 10^18th position in the Fibonacci modulo 10^9+9, running time should be <1 second
// Solution by Kien Pham
import java.util.*;
import java.io.*;

public class Fibonacci {
    // The approach:
    // Let f(n) be the nth fibonacci number. From definition, f(n) = f(n - 1) + f(n - 2).
    
    // Because n = 10^18 is very large, we cannot use recursion with time complexity of O(2^n) 
    // or even dynamic programming/iterative loop in O(n). We have to somehow come up with an algorithm
    // that runs in O(constant) or O(log(n)).

    // However, if the values of f(n - 1) and f(n - 2) are stored in a state matrix {{f(n - 1)}, {f(n - 2)}},
    // we can find a transition matrix T such that {{f(n - 1)}, {f(n - 2)}} * T = {{{f(n)}, {f(n - 1)}}}. 

    // Solving the above equation, we have: T = {{1, 1}, {1, 0}}.
    
    // After that, we can compute f(n) through matrix exponentiation as follows
    // {{f(n)}, {f(n - 1)}} = {{f(n - 1)}, {f(n - 2)}} * T = {{f(n - 2)}, {f(n - 3)}} * T * T = ... = {{f(1)}, {f(0)}} * T^(n - 1)
    // In other words, {{f(n)}, {f(n - 1)}} = {{1}, {0}} * T^(n - 1). 
    // We can further simply to {{f(n + 1)}, {f(n)}, {f(n), f(n - 1)}} = T^(n - 1) * {{1}, {1}} + T^(n - 1) * {{1}, {0}} = T^n.
    // The output f(n) = T^n[0][1] = T^n[1][0]

    // Doesn't that still take O(n) to compute? No, because we can optimize by using binary exponention, which costs O(log(n)):
    // If n is even, T^n = T^(n / 2) * T^(n / 2). Else, T^n = T^(floor(n / 2)) * T^(floor(n / 2)) * T. 
	// We can compute the above using recursion that terminates when n == 0, at which T^0 = I (identity matrix).

	// As a result, the total time complexity of the aforementioned algorithm is O(2^3 * log(n)) and memory of ~ O(1)


    static final int mod = (int)1e9 + 9; // the modulo as requires by the statement
    static final int[][] identity = new int[][]{{1, 0}, {0, 1}}; // 2x2 identity matrix
    static final int[][] base = new int[][]{{1, 1}, {1, 0}}; // base matrix for exponentation (= matrix T described above)

    //this function computes the multiplication res of 2 matrices mat1 and mat2 
    static int[][] multiply(int[][] mat1, int[][] mat2) {
    	int[][] res = new int[2][2];

    	for (int i = 0; i < 2; i++)
    		for (int j = 0; j < 2; j++)
    			for (int k = 0; k < 2; k++) 
    				// "temporarily" casting to long to prevent int overflow before taking modulo
    				res[i][j] = (int)(((long)mat1[i][k] * mat2[k][j] % mod + res[i][j]) % mod);
    	return res;
    }

    // raise matrix mat to the n-th power using binary exponentiation described above
    static int[][] power(int[][] mat, long n) {
    	if (n == 0L) return identity.clone();
    	int[][] res = power(mat, n / 2);
    	res = multiply(res, res);
    	if (n % 2 != 0) res = multiply(res, mat);
    	return res;
    }

    static int fib(long n) {
    	// return the (1, 0) entry of T^n (the entry (0, 1) also produces the same result)
    	return power(base, n)[1][0];
    }
    public static void main(String[] args) {      
        //runFromFile();
        Scanner inp = new Scanner(System.in);
        //PrintWriter out = new PrintWriter(System.out);
        long n = (long)1e18;
        // output the result when n = 10^18. 
        // Note that this is 0 indexed (i.e. fib(0) = 0, fib(1) = 1, fib(2) = 1, fib(3) = 2, ...)
        // If you want 1 indexed, just print out fib(n - 1) instead!
        System.out.println(fib(n)); 
        
        //out.close();
    }
}