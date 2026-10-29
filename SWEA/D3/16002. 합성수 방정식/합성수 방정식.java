import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');

			int N = Integer.parseInt(br.readLine());

			for (int y = 4;; y++) {
				int x = y + N;

				if (isComposite(x) && isComposite(y)) {
					sb.append(x).append(' ').append(y).append('\n');
					break;
				}
			}
		}
		System.out.print(sb);
	}

	private static boolean isComposite(int x) {
		for (int i = 2; i * i <= x; i++) {
			if (x % i == 0) {
				return true;
			}
		}
		return false;
	}
}
