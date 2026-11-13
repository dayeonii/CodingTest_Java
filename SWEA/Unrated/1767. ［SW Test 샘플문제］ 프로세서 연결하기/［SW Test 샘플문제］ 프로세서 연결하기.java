import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N, coreCnt, connectCnt, totalLen;
	static int[][] map;
	static List<Core> cores;

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Core {
		int r, c;

		Core(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// read input
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			cores = new ArrayList<>();
			coreCnt=0;
			connectCnt = 0;

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					// 이미 연결된 코어 (가장자리) 제외 하고 연결해야 될 코어들 찾기
					if (map[i][j] == 1 && !(i == 0 || j == 0 || i == N - 1 || j == N - 1)) {
						coreCnt++;
						cores.add(new Core(i, j));
					}
				}
			}

			// solve
			// 각 코어에 대해서 5가지 경우 (상,하,좌,우,연결안함) 를 완탐
			totalLen = Integer.MAX_VALUE;
			dfs(0, 0, 0);

			// print output
			sb.append('#').append(tc).append(' ').append(totalLen).append('\n');
		}
		System.out.print(sb);
	}

	/**
	 * @param idx     - 현재까지 탐색한 코어 개수(번호) (연결했든, 안했든)
	 * @param connect - 현재까지 연결한 코어 개수
	 * @param length  - 현재까지 연결한 전선 길이
	 */
	private static void dfs(int idx, int connect, int length) {

		// 종료 조건 - 모든 코어를 탐색 완료
		if (idx == coreCnt) {

			// 만약 전 케이스보다 더 많이 연결했으면 그걸로 모두 갱신
			if (connect > connectCnt) {
				connectCnt = connect;
				totalLen = length;
			}

			// 만약 전 케이스랑 연결한 개수가 같으면 길이가 더 짧은 것으로 전역 len 갱신
			else if (connect == connectCnt) {
				totalLen = Math.min(totalLen, length);
			}

			return;
		}

		// 가지 치기
		// 현재 연결 코어 개수 + 남은거 다 연결 < 전역 연결 코어 개수면 볼 필요 없음
		if (connect + coreCnt - idx < connectCnt) {
			return;
		}

		// dfs 탐색
		// 상하좌우로 탐색하기
		int r = cores.get(idx).r;
		int c = cores.get(idx).c;
		for (int d = 0; d < 4; d++) {
			int nr = r + delta[d][0];
			int nc = c + delta[d][1];

			// 맵 안에 있는지
			if (!isOnMap(nr, nc)) {
				continue;
			}

			// 이 방향으로 전선을 연결할 수 있는지
			if (!canConnect(nr, nc, d)) {
				continue;
			}

			// 전선을 깔 수 있다면 - 전선을 깔고 길이 반영
			int addLen = setWire(nr,nc,d,2);
			// 다음 코어에 대해 재귀 호출
			dfs(idx+1, connect+1, length+addLen);
			// 백트래킹
			setWire(nr,nc,d,0);
		}

		// 연결안하기
		dfs(idx+1, connect, length);
	}

	private static int setWire(int nr, int nc, int dir, int type) {
		
		// 이번 연결에 사용하는 전선 길이
		int len = 0;
		
		while (isOnMap(nr, nc)) {
			
			// 깔 수 있는 자리에 깔기
			map[nr][nc] = type;
			len++;
			
			nr += delta[dir][0];
			nc += delta[dir][1];
		}

		return len;
	}

	private static boolean canConnect(int nr, int nc, int dir) {

		while (isOnMap(nr, nc)) {

			// 가다가 코어에 걸림 or 가다가 전선에 걸림
			if(map[nr][nc]==1 || map[nr][nc]==2) {
				return false;
			}
			
			nr += delta[dir][0];
			nc += delta[dir][1];
		}

		return true;
	}

	private static boolean isOnMap(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}

}
