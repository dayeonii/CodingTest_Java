import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int H, W, N;
	static int curX, curY;
	static String play;

	static int currentDir;
	static char[] tank = { '^', 'v', '<', '>' };

	static char[][] map;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			map = new char[H][W];

			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == '^') {
						currentDir = 0;
						curX = i;
						curY = j;
					} else if (map[i][j] == 'v') {
						currentDir = 1;
						curX = i;
						curY = j;
					} else if (map[i][j] == '<') {
						currentDir = 2;
						curX = i;
						curY = j;
					} else if (map[i][j] == '>') {
						currentDir = 3;
						curX = i;
						curY = j;
					}
				}
			}

			N = Integer.parseInt(br.readLine());

			play = br.readLine();

			playGame();

			sb.append('#').append(tc).append(' ');
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}

	private static void playGame() {

		for (int index = 0; index < play.length(); index++) {

			// Move
			switch (play.charAt(index)) {
			case 'U':
				moving(0);
				break;
			case 'D':
				moving(1);
				break;
			case 'L':
				moving(2);
				break;
			case 'R':
				moving(3);
				break;
			case 'S':
				shooting();
				break;
			}
		}

	}

	private static void moving(int dir) {
		int nx = curX + delta[dir][0];
		int ny = curY + delta[dir][1];

		if (nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] == '.') {
			map[curX][curY] = '.';
			curX = nx;
			curY = ny;
		}
		currentDir = dir;
		map[curX][curY] = tank[currentDir];
	}

	private static void shooting() {
		int nx = curX + delta[currentDir][0];
		int ny = curY + delta[currentDir][1];

		while (nx >= 0 && nx < H && ny >= 0 && ny < W) {
			if (map[nx][ny] == '*') {
				map[nx][ny] = '.';
				return;
			} else if (map[nx][ny] == '#') {
				return;
			} else {
				nx += delta[currentDir][0];
				ny += delta[currentDir][1];
			}
		}
	}

}
