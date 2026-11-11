import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제]
 * - map[][] : 벌통의 꿀 정보
 * 
 * - 2명의 일꾼
 * 		- M : 각 일꾼이 채취할 수 있는 벌통 길이 (가로)
 * 		- C : 각 일꾼이 채취할 수 있는 꿀의 양 (C 이하 가능)
 * 		- 두 일꾼은 칸을 겹쳐서 채취할 수 없음
 * 
 * - 수익금
 * 		- 채취한 꿀의 양의 제곱
 * 		- 칸마다 제곱해서 합으로 봐야 한다
 * 		- 일꾼 A가 6,1 을 / 일꾼B가 8을 땄다면 -> (6*6) + (1*1) + (8*8) = 101
 * 
 * [풀이]
 * - 완전탐색
 * 		- 두 일꾼이 채취할 수 있는 조합의 수
 * 			- 일꾼A가 선택할 수 있는 칸의 개수 N * (N-M+1)
 * 			- 일꾼A가 선택한 뒤 일꾼B가 선택할 수 있는 칸의 개수 약 (N-1) * (N-M+1) (같은 행을 선택하는건 고려하진 않았지만)
 * 			- N,M의 범위가 작음 최대 10 000 (어쨌든 상한이 10 000)
 * 		- DFS로 풀어볼 수 있겠다
 * 
 * - 근데 C에 맞춰서 각 일꾼이 꿀을 선택하는 것도 DFS로 완탐을 돌리면
 * 		- 이중 DFS 처럼 되려나?
 * 		- M칸을 선택하는 건 반복문으로 조합을 구하자
 * */

public class Solution {

	static int result;
	static int N, M, C;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(reader.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// read input
			StringTokenizer st = new StringTokenizer(reader.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());

			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(reader.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// solve
			result = 0;

			// choose A
			for (int ar = 0; ar < N; ar++) {
				for (int ac = 0; ac <= N - M; ac++) { // col 범위가 M을 신경써야 함

					// choose B
					for (int br = 0; br < N; br++) {
						for (int bc = 0; bc <= N - M; bc++) {

							// B가 같은 행에 있을 수 없는 경우 (지금 인덱스가 겹치면)
							if ((ar == br) && !(ac + M <= bc || bc + M <= ac)) {
								continue;
							}

							// M개의 벌통에서 합이 C 이하가 되게 선택하기

							// A의 수익
							int profitA = dfs(ar, ac, 0, 0, 0);

							// B의 수익
							int profitB = dfs(br, bc, 0, 0, 0);

							result = Math.max(result, profitA + profitB);
						}
					}
				}
			}

			// print output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static int dfs(int r, int c, int count, int sum, int profit) {

		// 종료 조건
		if (count == M) {
			return profit;
		}

		// 재귀
		// 현재 벌통을 선택하지 않음
		int maxProfit = dfs(r, c, count + 1, sum, profit);

		// 현재 벌통을 선택함 (아직 꿀 양이 C 이하)
		int curH = map[r][c + count];
		if (sum + curH <= C) {
			
			maxProfit = Math.max(maxProfit, dfs(r, c, count + 1, sum + curH, profit + (curH * curH)));
		}
		
		return maxProfit;

	}

}
