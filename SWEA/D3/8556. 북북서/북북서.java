import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * [입력]
 * T
 * 방향을나타내는문자열
 * 
 * [출력]
 * #tc 각도(정수는 정수로, 아니면 기약분수로)
 * 
 * [문제]
 * - 북~서 사이의 방향 계산하기
 * - north : a - (90/2^n)
 * - west : a + (90/2^n)
 * - n : 이전에 등장한 north / west 의 총 횟수
 * 
 * [풀이]
 * - 문자열에서 north와 west를 구분하는 방법
 * 		- 문자열 한 번 돌리면서 charAt(i) == 'n' / 'w' 으로 구분
 * 		- 근데 테케를 보니까 뒤에서부터 계산해야 하는듯
 * 		- 북 'h' / 서 't'
 * - 공식 계산하기
 * 		- 시작 초기화
 * 			- north부터 시작 : 각도를 0으로 초기화 (이때 denom은 1으로 초기화해주기, Arithmetic Exception 방지)
 * 			- west부터 시작 : 각도를 90으로 초기화 (numer=90, denom=1)
 * 		- dir을 하나씩 읽어나가기
 * 			- 인덱스 이동 : north 처리를 했으면 5칸 땡겨주고, west 처리를 했으면 4칸 땡겨주기
 * 			- 문제에서 정의된 공식을 실행해줌
 * 		- 분수 형태로 계속 계산하기
 * 			- (a/b) + (c/d) = (ad + bc)/(bd)
 * 			- 반복해주면 커질 수 있으니까 -> 한 번 턴을 돌 때 최대공약수 구해서 약분 처리 해주기 (기약분수로 만들기)
 * 		- 최종 결과 출력
 * 			- 기약 분수 형태 그대로 출력해주면 됨
 * 			- 정수가 되는 경우는 denom==1일때 (기약분수에서 분모가 1이면 정수나눗셈)
 * */

public class Solution {

	static long firstNumerator, firstDenominator, secondNumerator, secondDenominator;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();

			int index = str.length() - 1;
			int n = 0;

			while (index >= 0) {
				char current = str.charAt(index);
				if (current == 'h') { // north

					if (n == 0) {
						firstNumerator = 0;
						firstDenominator = 1;
					} else {

						secondNumerator = 90;
						secondDenominator = 1L << n;

						firstNumerator = (firstNumerator * secondDenominator) - (firstDenominator * secondNumerator);
						firstDenominator = (firstDenominator * secondDenominator);
					}

					gcd(firstNumerator, firstDenominator);

					index -= 5;
					n++;
					
				} else { // west

					if (n == 0) {
						firstNumerator = 90;
						firstDenominator = 1;
					} else {
						secondNumerator = 90;
						secondDenominator = 1L << n;

						firstNumerator = (firstNumerator * secondDenominator) + (firstDenominator * secondNumerator);
						firstDenominator = (firstDenominator * secondDenominator);
					}

					gcd(firstNumerator, firstDenominator);

					index -= 4;
					n++;
				}
			}
			sb.append('#').append(tc).append(' ');
			if (firstDenominator == 1) {
				sb.append(firstNumerator / firstDenominator).append('\n');
			} else {
				sb.append(firstNumerator).append('/').append(firstDenominator).append('\n');
			}
		}
		System.out.print(sb);
	}

	private static void gcd(long numer, long denom) {
		if (denom == 0) {
			firstNumerator /= numer;
			firstDenominator /= numer;
			return;
		}

		gcd(denom, numer % denom);
	}

}