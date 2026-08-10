import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			sb.append('#').append(testCase).append(' ');
			
			String input = br.readLine();
			
			StringTokenizer st = new StringTokenizer(input);
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			
			
			int result = getLongest(str1, str2);
			
			sb.append(result).append('\n');
		}
		System.out.print(sb);
		 

	}

	private static int getLongest(String str1, String str2) {
		int len1 = str1.length();
		int len2 = str2.length();
		int [][] dp = new int[len1+1][len2+1];
		
		for(int i=1; i<len1+1; i++) {
			for(int j=1; j<len2+1; j++) {
				if(str1.charAt(i-1) == str2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		return dp[len1][len2];
	}

	

}
