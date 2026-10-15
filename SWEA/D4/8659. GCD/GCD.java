import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * [문제 정의]
 * - 2개의 숫자 A,B에 대해 GCD() 연산을 실행했을 때 실행되는 % 횟수 K
 * - GCD 연산
 * 		- GCD(a,0) = a
 * 		- GCD(a,b) = GCD(b, a%b)
 * 		- e.g, (5,3) -> (3,2) -> (2,1) -> (1,0)
 * - GCD 연산이 K번 이루어지는 (A,B) 숫자 쌍 중에서
 * 		- A가 가장 작은 조합
 * 		- 만약 가장 작은 A가 여러 개라면, 그 다음으로 B가 가장 작은 조합 선택
 * 
 * [입력]
 * T
 * K
 * 
 * [출력]
 * #tc A B
 * 
 * [풀이]
 * - 문제에서 주어진 GCD 공식 자체가 유클리드 호제법을 이용해 최대공약수를 구하는 방법이다
 * */

public class Solution {

	static int K;
	static long[] result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			K = Integer.parseInt(br.readLine());

			result = new long[2];
			fib(1, 0, 0);

			sb.append('#').append(tc).append(' ').append(result[0]).append(' ').append(result[1]).append('\n');
		}
		System.out.print(sb);
	}

	private static void fib(long A, long B, int count) {

		if (count == K) {
			result[0] = A+B;
			result[1] = A;
			return;
		}

		fib(A+B, A, count+1);
	}

}
