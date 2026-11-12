import java.util.*;

class Solution {
    public int solution(int N, int number) {
        // dp[i] : N을 i번 썼을 때 만들 수 있는 숫자의 집합
		// N+N, N-N, N*N, N/N, NN
		Set<Integer>[] dp = new HashSet[9];
		for (int i = 0; i < 9; i++) {
			dp[i] = new HashSet<>();
		}

		// 기저값
		int mul = 0;
		for (int i = 1; i < 9; i++) {
			mul = mul * 10 + N;
			dp[i].add(mul);
		}

		dp[2].add(N * 11);
		dp[2].add(N + N);
		dp[2].add(N - N);
		dp[2].add(N * N);
		dp[2].add(N / N);

		// dp 배열 채우기
		for (int i = 3; i < 9; i++) {

			// a+b = i 일 때
			// dp[a]와 dp[b]의 사칙연산 조합을 모두 넣으면 된다
			for (int a = 1; a <= i / 2; a++) {
				int b = i - a;

				for (int A : dp[a]) {
					for (int B : dp[b]) {
						dp[i].add(A + B);
						
						dp[i].add(A - B);
						dp[i].add(B - A);
						
						dp[i].add(A * B);

						if (B != 0)
							dp[i].add(A / B);
						if (A != 0)
							dp[i].add(B / A);
					}
				}
			}
		}

		// number 찾기
		int answer = -1;
		for (int i = 1; i < 9; i++) {
			if (dp[i].contains(number)) {
				answer = i;
				break;
			}
		}

		return answer;
    }
}