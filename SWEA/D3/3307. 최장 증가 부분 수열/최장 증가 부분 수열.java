import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [Problem]
 * - sequence A : A1, A2, ... , AN (N: 1~1000)
 * - subsequence B : B1 < B2 < ... < BK (K: 1~N)
 * - does not need to be continuous
 * 		- e.g.
 * 		- A : {1,3,2,5,4,7}
 * 		- B : {1,2,4,7} or {1,3,4,7} or ...
 * 		- Longest Increasing Subsequence B Length is 4
 * 
 * [Input]
 * T
 * N
 * elements of sequence A
 * 
 * [Output]
 * #tc answer
 * 
 * [Solution]
 * - For each arr[i] :
 * 		- what is the maximum len of increasing subsequence that ends at arr[i]
 * - e.g. A : {1,4,2,3}
 * 		- arr[0] = 1, {1}
 * 		- arr[1] = 4, 1 < 4 -> {1,4}
 * 		- arr[2] = 2, {1,4} or {1,2}
 * 		- arr[3] = 3, check all previous elements
 * 					1<3 -> {1,3}
 * 					4>3 -> cannot append
 * 					2<3 -> {1,2,3} -> max len is 3
 * - DP
 * 		- dp[i] : the maximum length of an increasing subsequence ending at arr[i]
 * 		
 * 		- For each arr[i] -> check every previous element arr[j] (j: 0~i-1)
 * 
 * 		- If arr[j] < arr[i]
 * 			arr[i] can be appended to an increasing subsequence ending at arr[i]
 * 		=> dp[i] = Math.max(dp[i], dp[j]+1)
 * 
 * 		- dp[] init every idx
 * 			(cuz, each element itself froms a subsequence)
 * 
 * 		- result : the maximum value in dp[]
 * */

public class Solution {

	static int N, result;
	static int arr[];
	static int dp[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			dp = new int[N];
			for (int i = 0; i < N; i++) {
				dp[i] = 1;
			}

			for (int i = 0; i < N; i++) {
				int current = arr[i];
				for (int j = 0; j <= i - 1; j++) {
					if (arr[j] < arr[i]) {
						dp[i] = Math.max(dp[i], dp[j] + 1);
					}
				}
			}

			result = 0;
			for (int i = 0; i < N; i++) {
				result = Math.max(result, dp[i]);
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}
