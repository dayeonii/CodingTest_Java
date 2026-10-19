import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * dfs/bfs + 메모이제이션
 * - d/bfs으로 간접 관계로 연결된 정보들을 기존 adj[][] 에 반영해준다
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

			// make adj
			for (int std = 1; std <= N; std++) {
				boolean[] visited = new boolean[N+1];
				dfs(std,std, visited);
			}

			// count result
			result = countResult();

			// print output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(int from, int mid, boolean[] visited) {
		
		visited[mid] = true;
		
		for(int to=1; to<=N; to++) {
			if(adj[mid][to] && !visited[to]) {
				adj[from][to] = true;	// 경유해서 갈 수 있음
				dfs(from, to, visited);
			}
		}

	}

	private static int countResult() {
		int res = 0;

		for (int i = 1; i <= N; i++) {
			int count = 0; // 현재 i 학생이 알고있는 관계의 수

			for (int j = 1; j <= N; j++) {
				if (i == j)
					continue;

				if (adj[i][j] || adj[j][i]) { // 다른 학생 j와의 관계가 있다면
					count++;
				}
			}

			if (count == N - 1) {
				res++;
			}
		}

		return res;
	}
}
