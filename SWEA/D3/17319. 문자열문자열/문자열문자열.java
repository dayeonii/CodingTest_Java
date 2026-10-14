import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		start:
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');

				
			int N = Integer.parseInt(br.readLine());
			String S = br.readLine();
				
			if (N % 2 != 0) {
				sb.append("No").append('\n');
				continue;
			}

			int half = N / 2;
			
			for (int i = 0; i < half; i++) {
				if (S.charAt(i) != S.charAt(i + half)) {
					sb.append("No").append('\n');
					continue start;
				}
			}

			sb.append("Yes").append('\n');
		}
		System.out.print(sb);
	}

}
