import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int H, W, N;
	static int currentDir, currentR, currentC;
	static char[][] map;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // U D L R
	static String shapeCar = "^v<>";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			// init map & set start location
			map = new char[H][W];
			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);

					int dir = shapeCar.indexOf(map[i][j]);
					if (dir != -1) {
						currentR = i;
						currentC = j;
						currentDir = shapeCar.indexOf(map[i][j]);
					}
				}
			}

			// input commands & play
			N = Integer.parseInt(br.readLine());
			String commands = br.readLine();
			for (int i = 0; i < N; i++) {
				char command = commands.charAt(i);

				if (command == 'S') {
					shoot(currentDir, currentR, currentC);
				} else {
					move(command);
				}

			}

			// output
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

	private static void move(char command) {
		String moveCommand = "UDLR";
		int dir = moveCommand.indexOf(command);

		currentDir = dir;

		int nr = currentR + delta[dir][0];
		int nc = currentC + delta[dir][1];

		if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] == '.') {
			map[currentR][currentC] = '.';
			currentR = nr;
			currentC = nc;
		}

		map[currentR][currentC] = shapeCar.charAt(dir);
	}

	private static void shoot(int dir, int r, int c) {
		int nr = r + delta[dir][0];
		int nc = c + delta[dir][1];

		while (nr >= 0 && nr < H && nc >= 0 && nc < W) {
			if (map[nr][nc] == '*') {
				// break wall and stop
				map[nr][nc] = '.';
				break;
			} else if (map[nr][nc] == '#') {
				// stop
				break;
			}

			// if ground or water -> pass
			nr += delta[dir][0];
			nc += delta[dir][1];
		}
	}

}
