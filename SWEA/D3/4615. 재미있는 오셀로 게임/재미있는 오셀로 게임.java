import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	// 8방 배열
	static int dx[] = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int dy[] = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			int board[][] = new int[N][N];
			board[(N / 2) - 1][(N / 2) - 1] = 2; // white
			board[(N / 2) - 1][N / 2] = 1; // black
			board[N / 2][(N / 2) - 1] = 1;
			board[N / 2][N / 2] = 2;

			int countB = 0;
			int countW = 0;
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;
				int z = Integer.parseInt(st.nextToken());

				

				for (int j = 0; j < 8; j++) {
					int newX = x + dx[j];
					int newY = y + dy[j];

					if (newX >= N || newY >= N) {
						continue;
					}

					int count = 0;
					while (newX >= 0 && newX < N && newY >= 0 && newY < N && board[newX][newY] == (3 - z)) {
						count++;
						newX += dx[j];
						newY += dy[j];
					}

					if (newX >= 0 && newX < N && newY >= 0 && newY < N && board[newX][newY] == z) {
						if (count > 0) {
							int backX = newX;
							int backY = newY;
							for (int k = 0; k < count; k++) {
								backX -= dx[j];
								backY -= dy[j];
								board[backX][backY] = z;
							}
							board[x][y] = z;
						}
					}

					if (newX >= 0 && newX < N && newY >= 0 && newY < N && board[newX][newY] == 0) {
						continue;
					}
				}

				

			}
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < N; c++) {
					if (board[r][c] == 1) {
						countB++;
					} else if (board[r][c] == 2) {
						countW++;
					}
				}
			}
			
			sb.append('#').append(tc).append(' ').append(countB).append(' ').append(countW).append('\n');
		}

		System.out.print(sb);

	}

}
