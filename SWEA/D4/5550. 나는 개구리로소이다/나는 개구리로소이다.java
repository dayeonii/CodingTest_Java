import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// read input
			String str = br.readLine();

			// init
			int result = 0;
			int depth = 0;
			Deque<Character> c = new ArrayDeque<>();
			Deque<Character> r = new ArrayDeque<>();
			Deque<Character> o = new ArrayDeque<>();
			Deque<Character> a = new ArrayDeque<>();

			boolean isValid = true;

			// solve
			try {

				for (int i = 0; i < str.length(); i++) {
					char current = str.charAt(i);

					switch (current) {
					case 'c':
						c.push(current);
						break;
					case 'r':
						if (c.size() > r.size())
							r.push(current);
						else
							isValid = false;
						break;
					case 'o':
						if (r.size() > o.size())
							o.push(current);
						else
							isValid = false;
						break;
					case 'a':
						if (o.size() > a.size())
							a.push(current);
						else
							isValid = false;
						break;
					case 'k':
						if (a.size() > 0) {
							result = Math.max(result, c.size());
							a.pop();
							o.pop();
							r.pop();
							c.pop();
						} else
							isValid = false;
						break;
					}
				}

				// not match croak
				if (c.size() != 0 || r.size() != 0 || o.size() != 0 || a.size() != 0)
					result = -1;

				if (!isValid)
					result = -1;

			} catch (NoSuchElementException e) {
				result = -1;
			}

			// print output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}
