import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - N*M 격자
 * 		- 일부가 부서져 있음 (#)
 * 		- 멀쩡한 타일은 (.)
 * - 부서진 타일은 2*2 새로운 타일으로 덮을 수 있음
 * 		- 무조건 2*2 모두 부서져있는 부분에다가 덮을 수 있다
 * - N*M 격자의 부서진 부분을 모두 덮을 수 있는지 YES/NO 출력
 * 
 * [입력]
 * TC
 * N M
 * N*M 타일 상태
 * 
 * [출력]
 * #tc answer
 * 
 * [풀이]
 * - 가지치기 : #의 개수는 4배수여야 함
 * - 그냥 시뮬 돌려보기
 * */

public class Solution {

	static char[][] map;
	static int N, M;
	static String result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			int count = 0;
			result = "YES";

			// read input : map
			map = new char[N][M];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < M; j++) {
					map[i][j] = line.charAt(j);
					if (map[i][j] == '#')
						count++;
				}
			}

			if (count % 4 != 0) {
				result = "NO";
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '#' && isAvailable(i, j)) {
						map[i][j] = 'o';
						map[i][j + 1] = 'o';
						map[i + 1][j] = 'o';
						map[i + 1][j+1] = 'o';
					}
				}
			}
			

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == '#') {
						result = "NO";
					}
				}
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static boolean isAvailable(int i, int j) {
		if (i < N - 1 && j < M - 1) {
			if (map[i][j + 1] == '#' && map[i + 1][j] == '#' && map[i + 1][j+1] == '#') {
				return true;
			}
		}
		return false;
	}

}
