import java.util.*;

class Solution {
    final static int SIZE = 51;

	static class Point {
		int r, c;

		Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        // 갈 수 있는 길을 1으로 표현
		// 겹쳐서 내부인 부분은 지워줘야 한다는 것이 포인트!!
		int[][] map = new int[SIZE * 2][SIZE * 2]; // 2배 스케일업 해주어야 한다

		// 먼저 사각형 모두 1으로 채우기
		for (int[] r : rectangle) {

			for (int x = r[0] * 2; x <= r[2] * 2; x++)
				for (int y = r[1] * 2; y <= r[3] * 2; y++)
					map[x][y] = 1;
		}

		// 그 다음 겹치는 내부를 지우기
		for (int[] r : rectangle) {

			for (int x = r[0] * 2 + 1; x < r[2] * 2; x++)
				for (int y = r[1] * 2 + 1; y < r[3] * 2; y++)
					map[x][y] = 0;
		}

		// 만들어진 맵에서 최단 경로 찾기
		Queue<Point> queue = new ArrayDeque<>();
		int[][] visited = new int[SIZE * 2][SIZE * 2]; // 방문 겸 거리비용 표시

		int startX = characterX * 2;
		int startY = characterY * 2;
		int endX = itemX * 2;
		int endY = itemY * 2;

		queue.offer(new Point(startX, startY));
		visited[startX][startY] = 1; // 방문 표시는 해야하니까 1로 두고, 나중에 결과값에서 -1 해주기 잊지말고

		while (!queue.isEmpty()) {
			Point current = queue.poll();

			// 도착했나
			if (current.r == endX && current.c == endY) {
				break;
			}

			for (int d = 0; d < 4; d++) {
				int nr = current.r + delta[d][0];
				int nc = current.c + delta[d][1];

				// 맵 밖으로 벗어나면 다음 방향 탐색
				if (!(nr >= 0 && nr < SIZE * 2 && nc >= 0 && nc < SIZE * 2)) {
					continue;
				}

				// 이미 방문한 곳이거나 땅이 아니면 다음 방향 탐색
				if ((visited[nr][nc] != 0) || (map[nr][nc] != 1)) {
					continue;
				}

				visited[nr][nc] = visited[current.r][current.c] + 1;

				queue.offer(new Point(nr, nc));

			}
		}

		return (visited[endX][endY]-1)/2;
    }
}