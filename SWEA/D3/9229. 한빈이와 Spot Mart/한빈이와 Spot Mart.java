import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int maxWeight;
	static int snacks[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			snacks = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				snacks[i] = Integer.parseInt(st.nextToken());
			}

			maxWeight = -1;
			perm(0, 0, 0);

			sb.append('#').append(tc).append(' ').append(maxWeight).append('\n');
		}
		System.out.print(sb);
	}

	private static void perm(int count, int curWeight, int idx) {

		// 종료 조건
		if (count == 2) {
			if (curWeight > maxWeight) {
				maxWeight = curWeight;
			}
			return;
		}
		if (idx == snacks.length) {
			return;
		}

		// 재귀
		if (curWeight + snacks[idx] <= M) {
			perm(count + 1, curWeight + snacks[idx], idx + 1); // 선택
		}
		perm(count, curWeight, idx + 1); // 안함
	}

}