import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			int A, B, N;
			StringTokenizer st = new StringTokenizer(br.readLine());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());

			int result = countPlus(A, B, N);

			sb.append(result).append('\n');
		}
		System.out.println(sb);
	}

	private static int countPlus(int a, int b, int n) {
		int count = 0;
		while (a <= n && b <= n) {
			if (a < b) {
				a += b;
				count++;
			} else {
				b += a;
				count++;
			}
		}
		return count;
	}
}
