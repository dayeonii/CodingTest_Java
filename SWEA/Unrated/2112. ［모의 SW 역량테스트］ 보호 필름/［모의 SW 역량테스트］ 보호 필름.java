import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - 필름 (D*W 크기)
 * 		- W개의 셀이 가로 방향으로 붙여서 만들어진 하나의 막D
 * 		- 각 셀의 특성 : 0(A) 또는 1(B)
 * - 약품 주입
 * 		- 약품은 막(각 행)별로 투입할 수 있음
 * 		- 약품 A를 넣으면 그 행은 모두 A로 변경됨 / 약품B를 넣으면 그 행은 모두 B로 변경됨
 * - 성능 검사 통과 기준
 * 		- 모든 세로방향 (각 열)에 대해서 동일한 특성의 셀이 K개 이상 연속적으로 있는 경우
 * 		- e.g. film[0][0] - film[1][0] - film[2][0] (K=3일 때)
 * - 결론
 * 		- 처음 필름이 만들어졌는데 성능 검사 통과 -> 0
 * 		- 약품을 투입해서 통과하게 만들어야 하는 경우 -> 약품 주입 최소 횟수 구하기
 * [제약사항]
 * - 필름 두께 D : 3 ~ 13
 * - 필름 가로 W : 1 ~ 20
 * - 합격 기준 K : 1 ~ D
 * 
 * [풀이]
 * - 무조건 통과 하는 방법 : 약품 K번 넣기
 * - 가지치기 : K==1 일 때 뭐로 채워져있던지 pass됨 -> 투입 횟수 0 출력
 * 
 * - 각 막 마다 할 수 있는 것
 * 		- 약품A를 넣기
 * 		- 약품B를 넣기
 * 		- 약품을 넣지 않기 
 * - 
 * */

public class Solution {

	static int result;
	static int D, W, K;
	static int[][] film;
	static int[][] backupFilm;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');

			// read input
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			// init
			result = K;
			film = new int[D][W];
			backupFilm = new int[D][W];

			for (int i = 0; i < D; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					int cur = Integer.parseInt(st.nextToken());
					film[i][j] = cur;
					backupFilm[i][j] = cur;
				}
			}

			// pruning (K==1)
			if (K == 1) {
				sb.append(0).append('\n');
				continue;
			}

			// solve
			dfs(0, 0);

			// output
			sb.append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(int depth, int input) {

		// 기저 조건 : D번째 막까지 다 봤으면 끝까지 다 돈 거 -> 성능을 통과한다면 -> result 갱신
		if (depth == D) {
			if (passed()) {
				result = Math.min(input, result);
			}
			return;
		}

		// 가지치기 : 현재 구한 투여 횟수가, 지난 result보다 크면
		if (input >= result) {
			return;
		}

		// 재귀 + 백트래킹
		// 3. 안 넣는 경우 -> 얘를 먼저
		dfs(depth+1, input);
		
		// 1. 약품A(0)를 넣는 경우
		Arrays.fill(film[depth], 0);
		dfs(depth + 1, input + 1);

		// 2. 약품B(1)를 넣는 경우
		Arrays.fill(film[depth], 1);
		dfs(depth + 1, input + 1);

		// 원복
		System.arraycopy(backupFilm[depth], 0, film[depth], 0, W);
	}

	private static boolean passed() {
		boolean pass = true;
		
		for(int c=0; c<W; c++) {
			int count = 1;
			
			for(int r=0; r<D-1; r++) {
				if(film[r][c] == film[r+1][c]) {
					count++;
				} else {
					count = 1;
				}
				
				if(count>=K) {
					break;
				}
			}
			
			if(count<K) {
				pass = false;
			}
		}
		
		return pass;
	}

}
