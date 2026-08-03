import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		
		for(int testCase=1; testCase<=T; testCase++) {
			
			int N = Integer.parseInt(br.readLine());
			
			int [] prices = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i++) {
				prices[i] = Integer.parseInt(st.nextToken());
			}
			
			// 뒤에서부터 접근
			long max = prices[N-1];
			long profit = 0;
			
			for(int i=N-2; i>=0; i--) {
				if(max<prices[i]) {
					max = prices[i];
				}
				profit += (max-prices[i]);
			}
			sb.append("#").append(testCase).append(" ").append(profit).append("\n");
		}
		System.out.println(sb);
	}

}
