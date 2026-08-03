import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {

			int N = Integer.parseInt(br.readLine());
			int count2 = 0, count3 = 0, count5 = 0, count7 = 0, count11 = 0;

			while (N > 1) {
				if (N % 2 == 0) {
					N = N / 2;
					count2++;
				} else if (N % 3 == 0) {
					N = N / 3;
					count3++;
				} else if (N % 5 == 0) {
					N = N / 5;
					count5++;
				} else if (N % 7 == 0) {
					N = N / 7;
					count7++;
				} else if (N % 11 == 0) {
					N = N / 11;
					count11++;
				}
			}

			sb.append("#").append(testCase).append(" ").append(count2).append(" ").append(count3).append(" ")
					.append(count5).append(" ").append(count7).append(" ").append(count11).append("\n");
		}
		System.out.println(sb); 

	}
}
