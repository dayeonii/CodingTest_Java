import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		for (int tc = 1; tc <= 10; tc++) {
			br.readLine(); // test case번호
			
			ArrayDeque<Integer> password = new ArrayDeque<>();

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				password.offer(Integer.parseInt(st.nextToken()));
			}

			boolean isFinished = false;
			
			while (!isFinished) {
				
				for(int minus=1; minus<=5; minus++) {
					int number = password.poll() - minus;
					
					if(number<=0) {
						number = 0;
						password.offer(number);
						isFinished=true;
						break;
					}
					
					password.offer(number);
				}
			}
			
			sb.append('#').append(tc).append(' ');
			while(!password.isEmpty()) {
				sb.append(password.poll()).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}

}
