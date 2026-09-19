import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {

	final static int SIZE = 4;
	static Set<Integer> result;
	static int[][] map;
	static int[][] delta = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// input
			map = new int[SIZE][SIZE];
			for (int i = 0; i < SIZE; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < SIZE; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// solve
			result = new HashSet<>();
			for (int i = 0; i < SIZE; i++) {
				for (int j = 0; j < SIZE; j++) {
					dfs(i, j, 1, map[i][j]);
				}
			}

			// output
			sb.append('#').append(tc).append(' ').append(result.size()).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(int r, int c, int depth, int number) {
		if(depth==7) {
			result.add(number);
			return;
		}
		
		for(int dir=0; dir<4; dir++) {
			int nr = r + delta[dir][0];
			int nc = c + delta[dir][1];
			if(canGo(nr, nc)) {
				dfs(nr, nc, depth+1, number*10 + map[nr][nc]);
			}
		}
		
	}

	private static boolean canGo(int nr, int nc) {
		return nr>=0 && nr<SIZE && nc>=0 && nc<SIZE;
	}

}
