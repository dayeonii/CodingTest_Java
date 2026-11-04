import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제]
 * - 주어진 N*N 흑백 이미지에서 검은 점을 포함하지 않는 가장 큰 빈 정사각형 찾기
 * 		N : 1 ~ 1000
 * 		0은 비어있는 칸, 1은 점
 * 
 * [풀이]
 * - 완탐 가능? -> N이 최대 1000 이라서 1000 * 1000
 * 		- 완탐하려면 O(N^2) 안에 풀어야 한다
 * 
 * - 가장 큰 빈 정사각형 (그리디하게?)
 * 		- 그럼 일단 maxWidth 랑 maxHeight를 찾아봐야 하나?
 * 		- 가로 세로 '길이'만 찾는다고 해결되는 문제가 아님 -> 정사각형 전체가 비어있는지 확인해야 한다
 * 		- 만약 첫 행이 모두 0이라서 maxWidth를 N으로 잡았다면 잘못된 일
 * 
 * -  한 점에서 퍼져나갈 수 있는 최대 영역을 찾기?
 * 		- 점마다 확인 O(N^2) 부터 이거고
 * 		- 가지치기를 하더라도, 한 점에서 퍼져나갈 수 있는 부분 확인 O(N^2) 라서 안됨
 * 
 * - 누적합
 * 		- 가장 큰 정사각형의 누적합은 0
 * 		- 누적합 배열을 구해두고, 정사각형 크기를 줄여가면서 모든 위치에 대해 누적합이 0인 '영역'이 있는지 찾아야됨
 * 		- 테케 1번을 보면 
 * 			누적합 배열  0 1 1 1 이 되고, 정답 영역은 [1][1] ~ [2][2] 인데 
 * 					1 2 2 3
 * 					2 3 3 4
 * 					2 4 4 6
 * 			그 부분에서 1의 개수가 증가하지 않는다는걸 보는 방법은
 * 			sum[2][2] - sum[0][2] - sum[2][0] == 0 ? 을 확인하는 방법
 * 		- 이걸 brute force 한다면
 * 			- 정사각형 변 길이를 조절 O(N)
 * 			- 좌상단 위치 탐색 O(N^2)
 * 			- 구간 합 구하기 O(1) 으로 간당간당함 (worst case O(N^3))
 * 
 * - DP
 * 		- dp[i][j] : map[i][j] 를 우하단으로 하는 빈 정사각형의 길이
 * 		- 점화식 :
 * 				if(map[i][j]==1) -> dp[i][j]=0; (1에 막혀서 만들 수 없다)
 * 				else             -> dp[i][j] = min(dp[i][j-1], dp[i-1][j], dp[i-1][j-1]) + 1
 * 									왜 max가 아니고 min이냐? 만약 내 옆에 map[][]==1 인 칸이 있다면 정사각형을 연장하지 못하고 다시 카운트 해야 하니까
 * 		- 결과 : dp[][] 중에서 제일 큰 값 찾기 (dp[N-1][N-1]이 아님)
 * 
 * [입력]
 * T
 * N
 * N*N
 * 
 * [출력]
 * #tc 가장큰정사각형한변
 * */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// read input
			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N+1][N+1];
			for (int i=1; i<=N; i++) {
				String line = br.readLine();
				for (int j=1; j<=N; j++) {
					map[i][j] = line.charAt(j-1) - '0';
				}
			}

			// solve
			int result = 0;
			
			// make dp arr
			int[][] dp = new int[N+1][N+1];
			
			// base case: handled by the (N+1)-size array (add padding)
			
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) { 
					
					if(map[i][j]==1) {
						dp[i][j] = 0;
					} else {
						dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1])) + 1;
					}
					
				}
			}
			
			// search max value
			for(int i=1; i<=N; i++) {
				for(int j=1; j<=N; j++) { 
					if(dp[i][j]>result) {
						result = dp[i][j];
					}
				}
			}

			// print output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}
