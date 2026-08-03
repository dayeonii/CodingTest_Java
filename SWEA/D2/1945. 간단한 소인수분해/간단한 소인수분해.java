import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {

			sb.append("#").append(testCase).append(" ");

			int N = Integer.parseInt(br.readLine());
			int[] primeFactors = { 2, 3, 5, 7, 11 };
			int[] counts = new int[primeFactors.length];

			for (int i = 0; i < primeFactors.length; i++) {
				while (N%primeFactors[i]==0) {
					counts[i]++;
					N /= primeFactors[i];
				}
				sb.append(counts[i]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);

	}
}
