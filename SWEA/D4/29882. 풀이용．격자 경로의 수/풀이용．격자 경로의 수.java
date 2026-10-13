import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			// add padding
			int[][] dp = new int[N+1][M+1];

			// base condition (start point)
			dp[1][1] = 1;
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=M; j++) {
					if(i==1 && j==1) continue;	// base condition pass
					dp[i][j] = dp[i][j-1] + dp[i-1][j];
				}
			}
			
			sb.append('#').append(tc).append(' ').append(dp[N][M]).append('\n');
		}
		System.out.print(sb);
	}

}
