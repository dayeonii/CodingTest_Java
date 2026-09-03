import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static final int SIZE = 16;
	static boolean finished;
	static int arr[][];
	static boolean visited[][];
	static int delta[][] = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

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

			dfs(1, 1);

			sb.append('#').append(tc).append(' ').append((finished == true) ? 1 : 0).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(int r, int c) {
		if (!isValid(r, c)) {
			return;
		}
		if (arr[r][c]==3) {
			finished = true;
			return;
		}

		visited[r][c] = true;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];

			if (arr[nr][nc] == 1) {
				continue;
			}
			if (visited[nr][nc]==true) {
				continue;
			}

			dfs(nr, nc);
		}
	}

	private static boolean isValid(int r, int c) {
		return r > 0 && r < SIZE && c > 0 && c < SIZE;
	}

}
