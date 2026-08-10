import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append('#').append(testCase).append(' ');

			String str = br.readLine();

			int[] alphabet = new int[26];
			for (int i = 0; i < 26; i++)
				alphabet[i] = 0;
			int alpha_idx = 0;

			for (int i = 0; i < str.length(); i++) {
				alpha_idx = str.charAt(i) - 'a';
				alphabet[alpha_idx]++;

			}

			boolean isNull = true;

			for (int i = 0; i < 26; i++) {
				if (alphabet[i] % 2 == 1) {
					sb.append((char) (i + 'a'));
					isNull = false;
				}
			}
			if (isNull == true) {
				sb.append("Good");
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}

}
