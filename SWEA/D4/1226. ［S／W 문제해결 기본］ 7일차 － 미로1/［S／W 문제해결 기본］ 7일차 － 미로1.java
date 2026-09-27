import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	final static int SIZE = 16;

	static int result;
	static int startR, startC, endR, endC;
	static int[][] maze;
	static boolean[][] visited;

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			// tc number
			br.readLine();

			// input maze
			maze = new int[SIZE][SIZE];
			for (int i = 0; i < SIZE; i++) {
				String line = br.readLine();
				for (int j = 0; j < SIZE; j++) {
					maze[i][j] = line.charAt(j) - '0';

					if (maze[i][j] == 2) {
						startR = i;
						startC = j;
					} else if (maze[i][j] == 3) {
						endR = i;
						endC = j;
					}
				}
			}

			// solve
			result = 0;
			visited = new boolean[SIZE][SIZE];
			
			visited[startR][startC] = true;
			dfs(startR, startC);

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(int r, int c) {

		if (r == endR && c == endC) {
			result = 1;
			return;
		}

		for (int i = 0; i < 4; i++) {

			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (canGo(nr, nc)) {
				visited[nr][nc] = true;
				dfs(nr, nc);
				visited[nr][nc] = false;
			}
		}
	}

	private static boolean canGo(int nr, int nc) {
		return nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE && maze[nr][nc] != 1 && !visited[nr][nc];
	}

}
