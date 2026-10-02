import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * [Problem]
 * - arr[i][j] = i*j
 * - you can move only right or down
 * 		- if you are at (i,j) , you can move (i+1, j) or (i, j+1)
 * - how many moves are needed to reach arr[i][j]==N?
 * 		- the starting position is arr[1][1]
 * 
 * [Input]
 * T
 * N
 * 
 * [Output]
 * #tc result
 * 
 * [Solution]
 * - find divisors A, B such that N = A*B
 * - brute force
 * 		- N : 2~10^12
 * 		- TLE
 * - check divisors only up to sqrt(N)
 * 		- O(sqrt(N)) = O(10^6)
 * - move count
 * 		(A-1) + (B-1) = A+B-2
 * 		minimize A+B-2
 * */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			long N = Long.parseLong(br.readLine());

			long result = Long.MAX_VALUE;

			for (long A = 1; A * A <= N; A++) {
				if (N % A == 0) {
					long B = N / A;
					result = (long) Math.min(result, A+B-2);
				}
			}
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}
