import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		for (int tc = 1; tc <= 10; tc++) {
			
			sb.append('#').append(tc).append(' ');

			int result = 0;

			int length = Integer.parseInt(br.readLine());
			if (length % 2 != 0) {
				br.readLine();
				sb.append(result).append('\n');
				continue;
			}

			Stack<Character> parentheses = new Stack<>();
			String input = br.readLine();
			
			for (int i = 0; i < length; i++) {
				char curToken = input.charAt(i);

				if (curToken == '(' || curToken == '[' || curToken == '{' || curToken == '<') {
					parentheses.push(curToken);
				} else {

					if(parentheses.isEmpty()) {
						result = 0;
						break;
					}
										
					char top = parentheses.pop();
					if( (curToken==')'&&top!='(')||
						(curToken==']'&&top!='[') ||
						(curToken=='}'&&top!='{') ||
						(curToken=='>'&&top!='<')) {
						result = 0;
						break;
					}
					result = 1;
				}
			}
			sb.append(result).append('\n');
		}
		System.out.print(sb);

	}

}
