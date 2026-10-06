import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - N명의 직원 (1~N번)
 * - N개의 할 일 (1~N번)
 * 		- 모든 직원에게 공평하게 하나씩 분배
 * - i번 직원이 j번 일을 하면 성공할 확률 P i,j
 * 		- 주어진 일이 모두 성공할 확률의 최댓값 구하기
 * 
 * [입력]
 * T
 * N (1~16)
 * N*N (i번째 줄은 i번 사람이 각 일을 성공할 확률들을 나타냄)
 * 
 * [출력]
 * #tc 최대확률 (소수점6자리까지, 반올림)
 * 
 * [접근]
 * - 직원에게 일을 분배하는 모든 경우의 수 : N!
 * 		- worst case N = 16 -> 안됨
 * - 그렇다면 그리디하게 풀어도 되는가?
 * 		- 최적해가 보장되지 않음 (당장 tc1번만 드라이런 해도 안나옴)
 * - 일단 N이 최대 16으로 작은 편이니까
 * 		- 완전탐색을 하되, 가지치기가 필요하겠다
 * 		- 가지치기를 할 수 있는가? -> 현재의 확률곱이 이전에 구한 값보다 작아지면 컷 (확률은 곱할수록 작아지거나 유지)
 * - 순서가 있는 선택의 연속
 * 		- 단계별로 선택을 이어나가면서 트리 가지가 '뻗어나가는' 형태 >> 자연스럽게 dfs를 생각해낼 줄 알아야...
 * 
 * [풀이]
 * 
 * */

public class Solution {

	static int N;
	static double result;
	static int[][] prob;
	static boolean[] selectedJob;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			init();

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					prob[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			dfs(0, 1.0);

			sb.append('#').append(tc).append(' ').append(String.format("%.6f", result*100)).append('\n');
		}
		System.out.print(sb);
	}

	private static void init() {
		prob = new int[N][N];
		selectedJob = new boolean[N];
		result = 0.0;
	}

	private static void dfs(int count, double currentProb) {
		// 기저 조건
		if (currentProb <= result) {
			return;
		}

		// 종료 조건
		if (count == N) {
			result = Math.max(result, currentProb);
			return;
		}

		// 재귀
		for (int job = 0; job < N; job++) {
			if (!selectedJob[job]) {
				selectedJob[job] = true;
				dfs(count + 1, currentProb * prob[count][job] * 0.01);
				selectedJob[job] = false;
			}
		}
	}

}
