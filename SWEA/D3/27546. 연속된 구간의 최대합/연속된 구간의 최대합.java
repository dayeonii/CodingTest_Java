import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int arr[];
	static long sumArr[];
	static long result;
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			arr = new int[N+1];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 구간합
			sumArr = new long[N+1];
			for (int i = 0; i < N; i++) {
				sumArr[i+1] = sumArr[i] + arr[i];
			}
			
			// 구간합 배열을 돌면서 maximum 찾기
			result = Long.MIN_VALUE;
			for(int i=0; i<N-K+1; i++) {
				long current = sumArr[i+K] - sumArr[i];
				if(result<current) {
					result = current;
				}
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}