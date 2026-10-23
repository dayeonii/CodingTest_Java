import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {

	static int N, maxCore, minLen;
	static int[][] map;
	static ArrayList<Point> coreList;
	static boolean[] connected;

	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Point {
		int r, c;
		int length;

		Point(int r, int c, int length) {
			this.r = r;
			this.c = c;
			this.length = length;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			coreList = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] == 1) {
						if (i > 0 && i < N - 1 && j > 0 && j < N - 1) {
							coreList.add(new Point(i, j, 0)); // 연결해야되는 코어들
						}
					}
				}
			}

			connected = new boolean[coreList.size()];

			maxCore = 0;
			minLen = Integer.MAX_VALUE;

			dfs(0,0,0);

			sb.append('#').append(tc).append(' ').append(minLen).append('\n');
		}
		System.out.print(sb);

	}

	private static void dfs(int idx, int count, int length) {

		// 가지치기 : 남은 코어를 다 연결해도 전역 최댓값보다 작을 때
		if (maxCore > count + (coreList.size() - idx + 1)) {
			return;
		}

		// 종료조건 : 코어 개수 다 봤을 때
		if (idx == coreList.size()) {
			// 만약 지금 발견한 코어 연결 수가 더 많으면
			if(count > maxCore) {
				maxCore = count;
				minLen = length;
			}
			// 더 많지는 않고 같으면 최소 길이로 갱신
			else if (count==maxCore) {
				minLen = Math.min(minLen, length);
			}
			return;
		}

		// 재귀
		Point curCore = coreList.get(idx);
		
		// 지금 코어 연결해보기
		for (int d = 0; d < 4; d++) {
			// 지금 자리에서 현재 방향(d)으로 연결할 수 있는가?
			int len = check(curCore, d);
			
			// 만약 연결할 수 없으면 (len==0) -> 막혔으니까 다음 방향으로
			if(len==0) continue;
			
			// 연결할 수 있으면 -> 전선 깔고 재귀 호출
			setWire(curCore, d, 2);
			dfs(idx+1, count+1, length+len);
			// 원상복구
			setWire(curCore, d, 0);
		}
		
		// 연결하지 않기
		dfs(idx+1, count, length);
	}

	private static void setWire(Point curCore, int d, int type) {
		int nr = curCore.r + delta[d][0];
		int nc = curCore.c + delta[d][1];
		
		while(canGo(nr,nc)) {
			map[nr][nc] = type;
			nr += delta[d][0];
			nc += delta[d][1];
		}
	}

	private static int check(Point curCore, int d) {
		int len = 0;
		
		int nr = curCore.r + delta[d][0];
		int nc = curCore.c + delta[d][1];
		
		while(true) {
			// 맵 밖에 도달하면 연결 가능함
			if(!canGo(nr,nc)) {
				return len;
			}
			
			// 코어나 전선에 막히면 연결할 수 없다
			if(map[nr][nc]!=0) {
				return 0;
			}
			
			len++;
			
			nr += delta[d][0];
			nc += delta[d][1];
		}
	}

	private static boolean canGo(int nr, int nc) {
		return nr >= 0 && nr < N && nc >= 0 && nc < N;
	}
}
