import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int R, C, r, c;
	static int result;
	static char arr[][];
	static boolean visited[][];
	static int delta[][] = { { 0, -1 }, { 0, 1 }, { -1, 0 }, { 1, 0 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());

			arr = new char[R][C];
			visited = new boolean[R][C];
			result = 0;
			
			for (int i = 0; i < R; i++) {
				String line = br.readLine();
				for (int j = 0; j < C; j++) {
					arr[i][j] = line.charAt(j);
				}
			}
			findCanGo(r, c);
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void findCanGo(int r, int c) {
		if (!isValid(r,c) || visited[r][c] || arr[r][c]=='#') {
			return;
		}

		visited[r][c]=true;
		result++;
		
		for (int i = 0; i < 4; i++) {
			int nr = r + delta[i][0];
			int nc = c + delta[i][1];
			findCanGo(nr,nc);
		}

	}

	private static boolean isValid(int r, int c) {
		return r >= 0 && r < R && c >= 0 && c < C;
	}
	
	

}
