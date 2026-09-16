import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [문제 요약]
 * - N명의 사람들
 * - 두 사람을 비교한 정보 M쌍
 * - N명을 줄 세우는 경우의 수 찾기
 * 
 * [제약사항]
 * - 사람의 수 N : 2~16
 * - 순위에 대한 정보 M : 1~120
 * 
 * [이슈]
 * - 완전탐색
 * 		- N명의 순서를 정하는 모든 경우의 수 N!
 * 		- worst case 16! -> 에바많음
 * - 정점이 N개인 방향 그래프 구현
 * 		- 제일 작은 정점에서 모든 정점을 거치는 경우의 수 확인
 * 		- 생각해보니까 모든 사람에 대해서 등수가 주어지는 보장이 없음
 * 		- 예를 들어 1등과 꼴등은 정해져있고, 나머지는 상관없으면 그 남은 사람들의 조합 가짓수가 모두 정답에 추가됨
 * 		- so, 그래프를 구현해서 탐색할 수 없을지도
 * 
 * [해결 프로세스]
 * - 비트마스킹으로 모든 사람들의 완주 여부를 확인함
 * - dp 메모이제이션 + dfs 탐색
 * */

public class Solution {

	static int N, M;
	static int[] need; // need[i] : i번째 사람의 선행조건
	static long[] dp;	// 메모이제이션, dp[i] : 현재 완주한 사람들의 집합 i의 경우의수

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			need = new int[N];
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken()) - 1;
				int y = Integer.parseInt(st.nextToken()) - 1;

				need[y] = need[y] | (1 << x);
			}

			dp = new long[1 << N];
			Arrays.fill(dp, -1);

			long result = dfs(0);
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	static long dfs(int visited) {
		// 기저조건
		if (visited == (1 << N) - 1) {
			return 1;
		}

		// 가지치기
		if (dp[visited] != -1) {
			return dp[visited];
		}

		long totalCase = 0;

		for (int nextPerson = 0; nextPerson < N; nextPerson++) {
			if ((visited & (1 << nextPerson)) == 0) {	// 아직 완주하지 않은 사람이면
				if ((visited & need[nextPerson]) == need[nextPerson]) {	// nextPerson의 선행완주자들이 이미 다 완주한 상태(visited에 포함)이면
					// nextPerson이 완주할 수 있는 모든 경우의 수를 더해준다
					totalCase += dfs(visited | (1 << nextPerson));
				}
			}
		}

		return dp[visited] = totalCase;
	}

}
