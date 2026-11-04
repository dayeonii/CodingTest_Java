import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());

			long[] P = new long[100];

			// base values
			for (int i = 0; i <= 2; i++)
				P[i] = 1;
			P[3] = 2;
			P[4] = 2;
			P[5] = 3;

			// insert values
			for (int i = 6; i < N; i++)
				P[i] = P[i - 1] + P[i - 5];
			
			// print output
			sb.append('#').append(tc).append(' ').append(P[N-1]).append('\n');;

		}
		System.out.print(sb);
	}

}
