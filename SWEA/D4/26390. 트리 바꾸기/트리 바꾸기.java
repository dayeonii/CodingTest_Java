import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static int result;
	static int[] degree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			
			degree= new int[N+1];
			
			for (int i = 1; i <= N - 1; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				
				degree[u]++;
				degree[v]++;
			}

			result = 0;
			for(int i=1; i<=N; i++) {
				if(degree[i]>2) {
					result += degree[i]-2;
				}
			}

			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}

}
