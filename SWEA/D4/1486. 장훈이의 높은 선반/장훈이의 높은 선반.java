import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int B;
	static int heights[];
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());

			heights = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				heights[i] = Integer.parseInt(st.nextToken());
			}

			result = Integer.MAX_VALUE;
			solve(0, 0);

			sb.append('#').append(tc).append(' ').append(result - B).append('\n');
		}
		System.out.print(sb);
	}

	private static void solve(int idx, int total) {
		if (idx == N) {
			if (total >= B) {
				result = Math.min(total, result);
				return;
			}
			return;
		}

		// 선택함
		solve(idx + 1, total + heights[idx]);

		// 선택안함
		solve(idx + 1, total);
	}

}
