import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int minLen;
	static int maxConnect;
	static List<Point> cores;
	static boolean[] visited;
	static int[][] map;

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();

			// input map
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 1) {
						if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
							cores.add(new Point(i, j));
						}
					}
				}
			}

			minLen = Integer.MAX_VALUE;
			maxConnect = Integer.MIN_VALUE;
			visited = new boolean[cores.size()];

			dfs(0, 0, 0);

			sb.append('#').append(tc).append(' ').append(minLen).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(int idx, int length, int connect) {
		// pruning
		if (connect + (cores.size()-idx) < maxConnect) {
			return;
		}

		// base case
		if (idx == cores.size()) {
			if (connect > maxConnect) {
				maxConnect = connect;
				minLen = length;
			} else {
				minLen = Math.min(length, minLen);
			}
			return;
		}

		// recursive
		Point current = cores.get(idx);
		for(int dir=0; dir<4; dir++) {
			if(canConnect(current, dir)) {
				int len = setWire(current, dir, 2);
				dfs(idx+1, length+len, connect+1);
				setWire(current, dir, 0);
			}
		}
		
		// not connect
		dfs(idx+1, length, connect);
	}

	private static int setWire(Point current, int dir, int type) {
		int nx = current.x + delta[dir][0];
		int ny = current.y + delta[dir][1];

		int length = 0;

		while (isAvailable(nx, ny)) {
			map[nx][ny] = type;
			length++;

			nx += delta[dir][0];
			ny += delta[dir][1];
		}

		return length;
	}

	private static boolean canConnect(Point current, int dir) {
		int nx = current.x + delta[dir][0];
		int ny = current.y + delta[dir][1];

		while (isAvailable(nx, ny)) {
			if (map[nx][ny] == 1 || map[nx][ny] == 2) {
				return false;
			}

			nx += delta[dir][0];
			ny += delta[dir][1];
		}
		return true;

	}

	private static boolean isAvailable(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < N;
	}
}
