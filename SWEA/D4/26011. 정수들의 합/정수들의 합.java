import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static int N, K;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			result = 0;
			
			for(int x=2; x<=2*N; x++) {
				long cntX = count(x);
				int y = x-K;
				long cntY = count(y);
				
				result += cntX * cntY;
			}

			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static long count(int x) {
		if(x<2 || x>2*N) {
			return 0;
		}
		
		if(x<=N+1) {
			return x-1;
		} else {
			return (2*N) - x + 1;
		}
	}

}
