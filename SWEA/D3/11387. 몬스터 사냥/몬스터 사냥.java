import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제]
 * - D : 기본 데미지 (100 ~ 1000)
 * - L : 공격의 레벨 (0 ~ 100)
 * - N : 총 공격 횟수 (1 ~ 100)
 * 
 * - 용->몬 : D 데미지
 * 		- 용사 공격의 레벨 L에 따라서 : 다음 공격의 데미지가 늘어남  D * (1 + n * L%)
 * 			n : 지금까지 몬스터를 때린 횟수 / L은 퍼센티지 계산 (1/100)
 * - 총 N번의 공격을 할 때, 몬스터에게 가해지는 총 데미지 구하기
 * 
 * [입력]
 * T
 * D L N
 * 
 * [출력]
 * #tc 총데미지
 * 
 * [풀이]
 * - tc1 ) D = 100, L = 0, N = 1
 * - 공격
 * 		0번 : 0데미지
 * 		1번 : 100데미지
 * 		-> 종료, 총 데미지 0 + 100
 * - tc2 ) D = 200, L = 12, N = 10
 * - 공격
 * 		0번 : 0데미지
 * 		1번 (n=0, 지금까지 때린 횟수) : 100데미지
 * 		2번 (n=1) : 200 ( 1 + 1 * 0.12 )
 * 		3번 (n=2) : 200 ( 1 + 2 * 0.12 )
 * 		...
 * 		11번 (n=10) : 200 ( 1 + 10 * 0.12 )
 * 		12번 (n=11) : 200 ( 1 + 11 * 0.12 )
 * - 등차수열의 합?
 * 		- 첫번째 항 a1 : D
 * 		- 증가 폭 d : D * L/100
 * - 등차수열 공식
 * 		- 첫번째 항 a, 마지막 항 l, 공차 d, 항의 개수 n
 * 		- Sn = ( n ( a+l ) ) / 2
 * 		- l = a + (n-1)d
 * 			 = ( n ( 2a + (n-1)d ) ) / 2
 * */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int D, L, N;

			// input
			StringTokenizer st = new StringTokenizer(br.readLine());
			D = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			// init
			int result = 0;

			// solve
			int diff = D * L / 100;
			int first = D;
			int last = first + (N - 1) * diff;

			result = (N * (first + last)) / 2;

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}
