import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			String str = br.readLine();

			int result = 0;
			Deque<Character> c = new ArrayDeque<>();
			Deque<Character> r = new ArrayDeque<>();
			Deque<Character> o = new ArrayDeque<>();
			Deque<Character> a = new ArrayDeque<>();

			boolean isValid = true;

			for (int i = 0; i < str.length(); i++) {
				char current = str.charAt(i);

				switch (current) {
				case 'c':
					c.push(current);
					break;
				case 'r':
					// 'c'의 개수가 'r'의 개수보다 많아야만 올바른 순서임
					if (c.size() > r.size()) {
						r.push(current);
					} else {
						isValid = false;
					}
					break;
				case 'o':
					// 'r'의 개수가 'o'의 개수보다 많아야만 올바른 순서임
					if (r.size() > o.size()) {
						o.push(current);
					} else {
						isValid = false;
					}
					break;
				case 'a':
					// 'o'의 개수가 'a'의 개수보다 많아야만 올바른 순서임
					if (o.size() > a.size()) {
						a.push(current);
					} else {
						isValid = false;
					}
					break;
				case 'k':
					// 'a'에 대기 중인 개구리가 없으면 잘못된 순서
					if (a.size() > 0) {
						result = Math.max(result, c.size());
						a.pop();
						o.pop();
						r.pop();
						c.pop();
					} else {
						isValid = false;
					}
					break;
				default:
					isValid = false;
					break;
				}

				if (!isValid) break;
			}

			// 모든 문자열을 다 돌았는데 완성되지 않고 남아있는 울음소리가 있다면 실패
			// (c, r, o, a 스택 중 하나라도 비어있지 않은 경우)
			if (c.size() != 0 || r.size() != 0 || o.size() != 0 || a.size() != 0) {
				isValid = false;
			}

			sb.append('#').append(tc).append(' ').append(isValid ? result : -1).append('\n');
		}
		System.out.print(sb);
	}
}