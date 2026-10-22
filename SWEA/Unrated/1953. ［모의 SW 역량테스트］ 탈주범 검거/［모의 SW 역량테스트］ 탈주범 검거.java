import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, R, C, L;
	static int result;
	static int[][] map;
	static boolean[][] visited;

	static int[][] delta = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } }; // 좌우상하

	static class Point {
		int r, c;
		int time;

		Point(int r, int c, int time) {
			this.r = r;
			this.c = c;
			this.time = time;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];
			result = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			bfs();

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void bfs() {
		// 큐 생성
		ArrayDeque<Point> queue = new ArrayDeque<>();

		// 시작점 방문 처리
		queue.offer(new Point(R, C, 1));
		visited[R][C] = true;
		result = 1;	  // 갈 수 있는 공간

		// 큐가 빌 때까지
		while (!queue.isEmpty()) {
			// bfs 로직
			// 하나 꺼내서
			Point current = queue.poll();

			// 소요 시간이 되면, 현재 노드까지만 이동할 수 있다
			// break가 아닌 continue : 현재 노드에서 다음 방향으로 터널 확장하는 로직을 관두는 것이지
			// 이미 큐에 들어있던 다른 케이스들은 봐줘야 함
			if (current.time == L) {
				continue;
			}

			// 그 자리에서 갈 수 있는 곳을 탐색
			// 현재 자리의 터널 모양에 따라서 분기 - 시작점은 무조건 터널이니까, 바로 터널 모양을 확인해도 된다

			switch (map[current.r][current.c]) {
			// 십자모양 (1)
			case 1:
				for (int d = 0; d < 4; d++) {
					move(current.r, current.c, d, current.time, queue);
				}
				break;

			// 상 하
			case 2:
				move(current.r, current.c, 2, current.time, queue);
				move(current.r, current.c, 3, current.time, queue);
				break;

			// 좌 우
			case 3:
				move(current.r, current.c, 0, current.time, queue);
				move(current.r, current.c, 1, current.time, queue);
				break;

			// 상 우
			case 4:
				move(current.r, current.c, 2, current.time, queue);
				move(current.r, current.c, 1, current.time, queue);
				break;

			// 하 우
			case 5:
				move(current.r, current.c, 3, current.time, queue);
				move(current.r, current.c, 1, current.time, queue);
				break;

			// 하 좌
			case 6:
				move(current.r, current.c, 3, current.time, queue);
				move(current.r, current.c, 0, current.time, queue);
				break;

			// 상 좌
			case 7:
				move(current.r, current.c, 2, current.time, queue);
				move(current.r, current.c, 0, current.time, queue);
				break;
			}
		}

	}

	private static void move(int r, int c, int d, int t, ArrayDeque<Point> queue) {
		/***
		 * 현재 위치 (r,c) 에서 -> 다음 위치(nr,nc)로 갈 수 있는지를 판단하려 한다 - 맵 범위, 미방문, 터널 종류 중 하나여야 함
		 * - 현재 위치랑 다음 위치가 터널으로 연결되어있는지 봐야 함
		 */

		int nr = r + delta[d][0];
		int nc = c + delta[d][1];

		// 맵 범위 체크
		if (!(nr >= 0 && nr < N && nc >= 0 && nc < M)) {
			return;
		}

		// 이미 방문했거나 터널이 아닌 곳
		if (visited[nr][nc] || map[nr][nc] == 0) {
			return;
		}

		int nextType = map[nr][nc];
		boolean canGo = false;

		// 델타 배열을 좌(0)-우(1)-상(2)-하(3) 순서로 적어놓음
		switch (d) {

		// 내가 현재 위치에서 왼쪽으로 이동하려면, 다음 터널이 상하좌우(1)/좌우(3)/상우(4)/하우(5) 모양이어야 한다
		case 0:
			if (nextType == 1 || nextType == 3 || nextType == 4 || nextType == 5) {
				canGo = true;
			}
			break;

		// 나머지 케이스도 동일하게 처리
		case 1: // 오른쪽 이동 : 필요 터널 1,3,6,7
			if (nextType == 1 || nextType == 3 || nextType == 6 || nextType == 7) {
				canGo = true;
			}
			break;

		case 2: // 위쪽 이동 : 필요 터널 1,2,5,6
			if (nextType == 1 || nextType == 2 || nextType == 5 || nextType == 6) {
				canGo = true;
			}
			break;

		case 3: // 아래 이동 : 필요 터널 1,2,4,7
			if (nextType == 1 || nextType == 2 || nextType == 4 || nextType == 7) {
				canGo = true;
			}
			break;
		}

		// 이동할 수 있다면 이동 처리 해주기
		if (canGo) {
			visited[nr][nc] = true;
			queue.offer(new Point(nr, nc, t+1));
			result++;
		}
	}
}
