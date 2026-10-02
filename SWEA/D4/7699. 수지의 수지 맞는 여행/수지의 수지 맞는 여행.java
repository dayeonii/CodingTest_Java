import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [Problem]
 * - grid : arr[0][0] ~ arr[R-1][C-1] (0-base)
 * 		- arr[i][j] == oe uppercase alphabet
 * 		- the first visit to an alphabet is free
 * 		- visiting the same alphabet again costs money
 * - the starting position is arr[0][0]
 * 		- suzy can move up/down/left/right
 * - suzy wants to see as many different alphabets as possible without paying money
 * 		- so, suzy cannot visit the same alphabet twice
 * - find the maximum number of different alphabets suzy can visit
 * 
 * [Input]
 * T
 * R C
 * R*C grid
 * 
 * [Output]
 * #tc result
 * 
 * [Solution]
 * - DFS+backtracking
 * - the maximum path length is 26
 * 		only 26 alphabets
 * - boolean[26] to check which alphabets have been visited
 * */

public class Solution {

	static int R, C, result;
	static char[][] map;
	static boolean[] alphabets;

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new char[R][C];
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					map[i][j] = line.charAt(j);
				}
			}

			int startIdx = map[0][0] - 'A';

			alphabets = new boolean[26];
			alphabets[startIdx] = true;

			result = 0;
			solve(0, 0, 1);

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void solve(int r, int c, int count) {

		// renewal result
		result = Math.max(result, count);

		// search 4 dir
		for (int d = 0; d < 4; d++) {
			int nr = r + delta[d][0];
			int nc = c + delta[d][1];

			if (!canGo(nr, nc)) {
				continue;
			}
			
			int alphaIdx = map[nr][nc] - 'A';
			if (!alphabets[alphaIdx]) {
				alphabets[alphaIdx] = true;
				solve(nr, nc, count + 1);
				alphabets[alphaIdx] = false;
			}
		}
	}

	private static boolean canGo(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}
}
