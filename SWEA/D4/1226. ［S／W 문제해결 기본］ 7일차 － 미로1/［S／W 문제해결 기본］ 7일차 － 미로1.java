import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	static final int SIZE = 16;
	static boolean finished;
	static int arr[][];
	static boolean visited[][];
	static int delta[][] = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	private static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();

			finished = false;
			arr = new int[SIZE][SIZE];
			visited = new boolean[SIZE][SIZE];

			for (int i = 0; i < SIZE; i++) {
				String line = br.readLine();
				for (int j = 0; j < SIZE; j++) {
					arr[i][j] = line.charAt(j) - '0';
				}
			}

			bfs(1, 1);

			sb.append('#').append(tc).append(' ').append((finished == true) ? 1 : 0).append('\n');
		}
		System.out.print(sb);
	}

	private static void bfs(int r, int c) {
		
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(r, c));
		visited[r][c] = true;

		while (!queue.isEmpty()) {

			Point current = queue.poll();
			int curR = current.x;
			int curC = current.y;

			if(arr[curR][curC]==3) {
				finished = true;
				return;
			}
			
			for(int i=0; i<4; i++) {
				int nr = curR + delta[i][0];
				int nc = curC + delta[i][1];
				if (!isValid(nr,nc) || arr[nr][nc]==1 || visited[nr][nc]==true) {
					continue;
				}
				queue.offer(new Point(nr, nc));
				visited[nr][nc]=true;
			}
		}
	}

	private static boolean isValid(int r, int c) {
		return r > 0 && r < SIZE && c > 0 && c < SIZE;
	}

}
