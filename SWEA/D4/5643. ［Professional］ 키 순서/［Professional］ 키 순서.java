import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 일반적인 DFS
 * - 지금 나 와 연결된 관계가 있는지 확인
 * 		- 나보다 키가 큰 거
 * 		- 나보다 키가 작은 거
 * 		- 이 두 개의 합이 N-1 이면 통과
 * */

public class Solution {

	static int result, N, M;
	static boolean[][] adj;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// init
			result = 0;

			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());

			adj = new boolean[N + 1][N + 1];

			// read input
			for (int i = 0; i < M; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adj[a][b] = true;
			}

			// dfs 탐색
			// 모든 학생들에 대해서 검사해주면 됨
			for (int std = 1; std <= N; std++) {
				// 한 케이스마다 사용해줘야 하니까 이 안에서 초기화
				boolean[] visited = new boolean[N + 1];

				// 나보다 큰 학생의 수 찾기
				int taller = dfs(std, visited, true);

				// 나보다 작은 학생의 수 찾기
				visited = new boolean[N + 1];
				int smaller = dfs(std, visited, false);

				// 두 수를 합친게 N-1 이면 이 학생은 본인의 키를 알고 있음
				if (taller + smaller == N - 1) {
					result++;
				}
			}

			// print output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	// forward : 하나의 dfs 함수 안에서 adj[std][next] 와 std[next][std]를 구분하기 위함
	// forward==true 이면 adj[std][next]를 찾고, next에서 또 다른 학생들을 검사 (깊이 탐색)
	private static int dfs(int std, boolean[] visited, boolean forward) {
		// 지금 std를 기준으로 나보다 큰/작은 애들의 수
		int count = 0;

		// 나 방문 처리
		visited[std] = true;

		// 나머지 학생들에 대해서 나보다 큰 애들이 있는가?
		for (int next = 1; next <= N; next++) {
			boolean connected;

			if (forward) {
				connected = adj[std][next];
			} else {
				connected = adj[next][std];
			}

			if (connected && !visited[next]) {
				count += 1 + dfs(next, visited, forward);
			}
		}

		return count;
	}
}
