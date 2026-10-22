import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제]
 * 정점 개수 N : 1 ~ 10 (번호 1-based)
 * 간선 개수 M : 0 ~ 20
 * 무방향 그래프
 * 
 * 최장 경로의 길이 구하기
 * 		- 길이 : 경로 상에 등장하는 정점의 개수
 * 
 * [풀이]
 * !! 일반 최장 경로 길이 구하기 문제랑 뭐가 다를까?
 * 		- 무방향 그래프이고, 사이클이 존재할 수 있음
 * 		- 위상 정렬 + DP로 최장 경로를 찾는게 안됨
 * 		- 이 문제는 N이 10 이내로 작아서 백트래킹 DFS로 할 수 있음
 * 
 * - 모든 정점을 각각 출발지로 삼아서 갈 수 있는 모든 경로를 탐색하기
 * - 가지치기
 * 		- 지금까지 방문한 정점 개수 + 남은 모든 정점 개수 < 전역 최댓값
 * 		- 이론상 가능한 최대 길이 도달 (정점 N)
 * 
 * [입력]
 * T
 * N M
 * x y
 * 
 * [출력]
 * #tc length
 * */

public class Solution {

	static int N, M, result;
	static int[][] edgeList;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			edgeList = new int[N + 1][N + 1];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				edgeList[x][y] = 1;
				edgeList[y][x] = 1; // 무방향 그래프이기 때문에 반대로도 저장해줘야 함
			}

			// 모든 정점에 대해서 dfs 돌려보기
			result = 0;
			visited = new boolean[N + 1];
			for (int i = 1; i <= N; i++) {
				if(result==N) {
					break;
				}
				visited[i] = true; // 시작점 처리하기
				dfs(i, 1);	// 시작점도 방문했으니까 길이 1으로 시작
				visited[i] = false;
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	// len이 즉 현재 방문한 정점의 개수 역할도 한다
	private static void dfs(int cur, int len) {
		result = Math.max(result, len);
		
		// 가지 치기
		int unvisited = 0;
		for (int i = 1; i <= N; i++) {
			if(!visited[i])
				unvisited++;
		}
		if (len + unvisited < result) {
			return;
		}

		// 종료 조건
		if (result == N) {
			return;
		}

		// 재귀
		for (int i = 1; i <= N; i++) {
			if(cur==i || visited[i]) {
				continue;
			}

			if (edgeList[cur][i] == 1) {
				visited[i] = true;
				dfs(i, len + 1);
				visited[i] = false;
			}
		}

	}

}
