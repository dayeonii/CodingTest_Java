import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Solution {

	final static int INF = 1_000_000_000;

	static int[][] map;

	static int[][] dist;
	static PriorityQueue<Point> queue;

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Point {
		int r, c, cost;

		Point(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// read input
			int N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}

			// init
			queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
			queue.offer(new Point(0, 0, 0)); // start

			dist = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = INF;
				}
			}

			// solve
			while (!queue.isEmpty()) {
				Point cur = queue.poll();
				int curR = cur.r;
				int curC = cur.c;
				int curCost = cur.cost;

				// pruning
				if (curCost > dist[curR][curC]) {
					continue;
				}

				// end point
				if (curR == N - 1 && curC == N - 1) {
					break;
				}

				for (int d = 0; d < 4; d++) {
					int nr = curR + delta[d][0];
					int nc = curC + delta[d][1];

					if (!(nr >= 0 && nr < N && nc >= 0 && nc < N)) {
						continue;
					}

					int nCost = curCost + map[nr][nc];

					if (nCost < dist[nr][nc]) {
						dist[nr][nc] = nCost;
						queue.offer(new Point(nr, nc, nCost));
					}
				}
			}

			// print output
			sb.append('#').append(tc).append(' ').append(dist[N-1][N-1]).append('\n');
		}
		System.out.print(sb);
	}
}
