import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int result;
	static int arr[][];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());

			arr = new int[N][N];
			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					arr[i][j] = line.charAt(j) - '0';
				}
			}

			result = 0;
			solve();

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void solve() {
		int center = N / 2;
		int len = 0;

		for (int i = 0; i < N; i++) {

			for (int j = center - len; j <= center + len; j++) {
				result += arr[i][j];
			}

			if (i < center) {
				len++;
			} else {
				len--;
			}

		}
	}

}
