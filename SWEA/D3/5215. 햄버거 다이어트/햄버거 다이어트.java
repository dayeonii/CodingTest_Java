import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, L;
	static int maxPoint;
	static int ingredients[];
	static int calrories[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			ingredients = new int[N];
			calrories = new int[N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				ingredients[i] = Integer.parseInt(st.nextToken());
				calrories[i] = Integer.parseInt(st.nextToken());
			}

			maxPoint = 0;
			solve(0, 0, 0);

			sb.append('#').append(tc).append(' ').append(maxPoint).append('\n');
		}
		System.out.print(sb);
	}

	private static void solve(int count, int curCal, int curPoint) {
		// 가지치기
		if (curCal > L) {
			return;
		}

		if (count == N) {
			if (maxPoint < curPoint) {
				maxPoint = curPoint;
			}
			return;
		}

		solve(count + 1, curCal + calrories[count], curPoint + ingredients[count]);
		solve(count + 1, curCal, curPoint);
	}

}
