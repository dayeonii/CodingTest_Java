import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, result;
	static int[] A, B;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// read input N
			N = Integer.parseInt(br.readLine());

			// init
			A = new int[N];
			B = new int[N];
			result = 0;

			// read input A[], B[]
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				A[i] = Integer.parseInt(st.nextToken());
				B[i] = Integer.parseInt(st.nextToken());
			}

			// solve
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					if ( (A[i] > A[j] && B[i] < B[j]) || (A[i] < A[j] && B[i] > B[j]) ) {
						result++;
					}
				}
			}

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}
