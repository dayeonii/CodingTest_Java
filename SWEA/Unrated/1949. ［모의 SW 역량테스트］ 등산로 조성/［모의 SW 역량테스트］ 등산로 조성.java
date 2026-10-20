import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * [문제]
 * - N*N 부지에 '최대한 긴 등산로' 만들기
 * 		- 각 칸에는 지형의 높이가 적혀있음
 * - 등산로 규칙
 * 		- 가장 높은 봉우리에서 시작 (길이=1 시작)
 * 		- 높은 지형에서 낮은 지형으로만 연결할 수 있음
 * 		- 가로 또는 세로 방향으로만 연결할 수 있음
 * - 지형 깎기
 * 		- 등산로를 만드는 도중 '1번의 기회'가 있다
 * 		- K 만큼 지형을 깎을 수 있다
 * 
 * [풀이]
 * - 최대 경로 찾기
 * 		- dfs로 모든 경우를 탐색
 * 			- 지도 크기 최대 8*8이므로 작음
 * 			- 가능한 등산로 또한 높은 지형 -> 낮은 지형이므로 모든 칸의 순열을 탐색하진 않게 됨
 * 		- 가지치기가 있는가?
 * 			- 최대 값을 구할 때의 가지치기라면 보통 : 지금까지의 값 + 남은경우에서도 모두 선택했을때(?) 가 전역 최댓값보다 작을때
 * 			- 근데 이 문제에서는 남은 길이 얼마나 될지 명확하지 않기 때문에
 * 			- 그리고 그 남은 길을 탐색하는 것 자체가 그냥 각각의 dfs로 애초에 시도하고 있음
 * 		- 종료조건이 있는가?
 * 			- 명확하지 않음. 갈 수 있을 때 까지는 계속 가야함
 * 			- 즉, 현재 자리에서 더 이상 사방으로 갈 수 없으면 종료 (깎을 기회도 없으면) 
 * = 지형 깎기
 * 		- 언제 깎아야 하는가?
 * 			- 지금 위치에서 사방으로 움직일 수 없을 때
 * 			- 이게 모든 경우를 탐색 커버해주는가? yes
 * 		- 얼마나 깎아야 하는가?
 * 			- 지금 나의 높이 - 1 만큼 해야 : 그 다음 선택지를 '최대화' 할 수 있다
 * [입력]
 * T
 * N K
 * N*N map info
 * 
 * [출력]
 * #tc maxLen
 * */

public class Solution {

	static int N, K, result;
	static int[][] map;
	static boolean[][] visited;
	static ArrayList<Point> startPoints;

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// read input map info & search max value
			int max = 0;

			map = new int[N][N];
			visited = new boolean[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					max = Math.max(max, map[i][j]);
				}
			}

			// find start points
			startPoints = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j] == max) {
						startPoints.add(new Point(i, j));
					}
				}
			}

			// solve
			for (Point cur : startPoints) {
				visited = new boolean[N][N];
				
				visited[cur.r][cur.c] = true;
				dfs(cur, 1, true);
			}

			// print output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(Point cur, int length, boolean chance) {
		
		int curR = cur.r;
		int curC = cur.c;
		
		// result 갱신하기
		result = Math.max(result, length);
		
		// 현재 위치에서 사방 탐색
		for(int d=0; d<4; d++) {
			int nr = curR + delta[d][0];
			int nc = curC + delta[d][1];
			
			// 갈 수 없는 곳 거르기
			if(!(nr>=0 && nr<N && nc>=0 && nc<N)) {
				continue;
			}
			
			if(visited[nr][nc]) {
				continue;
			}
			
			// 높은 곳에서 낮은 곳으로 이동할 수 있으면
			if(map[nr][nc] < map[curR][curC]) {
				visited[nr][nc] = true;
				dfs(new Point(nr,nc), length+1, chance);
				visited[nr][nc] = false;
			}
			// 현재 위치가 가려는 곳보다 낮으면
			else {
				
				// 근데 깎아서 이동 가능하다면 (찬스가 있고, 깎아야 하는 높이가 K 미만)
				int diff = map[nr][nc] - map[curR][curC];
				int backup = map[nr][nc];
				if(chance && diff<K) {
					chance = false;
					map[nr][nc] = map[curR][curC]-1;
					visited[nr][nc] = true;
					dfs(new Point(nr,nc), length+1, chance);
					visited[nr][nc] = false;
					chance = true;
					map[nr][nc] = backup;
				}
				// 깎아서 갈 수 없으면 끝
				else {
					continue;
				}
			}
		}

	}
}
