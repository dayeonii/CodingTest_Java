/*
 * [문제 정의]
 * - 주혁이가 구매한 복권 M장 (1~1000) (복권 번호)
 * - 복권 당첨 번호 N개 (1~100)		  (당첨 번호)
 * - 각 복권은 8자리
 * 		- 숫자 혹은 *
 * 		- *은 모든 숫자와 매치됨 (a.k.a 조커 카드)
 * - 한 당첨 번호는 하나의 복권에만 당첨됨
 * 		- e.g. ***7**** 와 2017****은 20170101 둘 다 매치됨 -> 이런게 없도록 당첨 번호가 구성됨
 * - 주혁이가 받을 수 있는 총 당첨금 구하기
 * 
 * [입력]
 * T
 * N M
 * 당첨번호 당청금
 * 구매한 복권
 * 
 * [출력]
 * #tc totalPrice
 * 
 * [풀이]
 * - 구매한 복권 각각에 대해 N개의 당첨 번호를 비교
 * - 하나의 복권에 여러 개의 당첨번호가 매칭되는 일이 없으므로
 * - 현재 확인 중인 복권에서 당첨 -> 바로 다음 복권으로 이동
 * - 당첨 번호들은 스킵하면 안됨
 * 		- 구매한 복권에 중복이 있을 수 있다
 * - 시간복잡도
 * 		- 당첨 번호 100개
 * 		- 구매 복권 1000개
 * 		- 길이 8
 * 		- 구매 복권 1개 당 최대 100개의 당첨 번호 8자리 비교
 * 		- O(N*M*8)
 * 		- 최악의 경우 800 000 -> 가능
 * - 당첨금 계산	
 * 		- 복권 하나 당 최대 당청금 1 000 000
 * 		- 복권 최대 개수 1 000
 * 		- 최대 상금 1 000 000 000 -> int 안에 되긴 함
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, M;
	static int result;
	static int[] prices;
	static String[] winningNumbers, myLotteries;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// read input
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// init
			winningNumbers = new String[N];
			prices = new int[N];
			myLotteries = new String[M];
			result = 0;

			// read input arr
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				winningNumbers[i] = st.nextToken();
				prices[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				myLotteries[i] = st.nextToken();
			}

			// solve
			for (int i = 0; i < M; i++) {

				for (int j = 0; j < N; j++) {
					boolean isAllMatched = true;
					
					for (int k = 0; k < 8; k++) {
						char lotto = myLotteries[i].charAt(k);
						char winning = winningNumbers[j].charAt(k);
						
						if (!isMatch(lotto, winning)) {
							isAllMatched = false;
							break;
						}
					}
					
					if(isAllMatched) {
						result += prices[j];
						break;
					}
				}
			}

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static boolean isMatch(char lotto, char winning) {
		return lotto == winning || winning == '*';
	}

}
