import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static StringTokenizer st;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// read input
			int N = Integer.parseInt(br.readLine());

			StringBuilder seq = new StringBuilder();
			for(int i=0; i<N; i++) {
				seq.append(next(br));
			}
			
			String str = seq.toString();

			
			// solve
			int result = 0;
			while(str.contains(String.valueOf(result)))
				result++;


			// print output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static String next(BufferedReader br) throws IOException {
		while(st==null || !st.hasMoreTokens()) {
			st = new StringTokenizer(br.readLine());
		}
		return st.nextToken();
	}
}
