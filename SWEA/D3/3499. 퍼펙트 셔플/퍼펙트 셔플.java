import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			int N = Integer.parseInt(br.readLine());

			String[] arr = inputArray(br, N);

			String[] result = shuffleCard(arr);

			sb.append('#').append(testCase).append(' ');
			printArray(sb, result);

		}
		System.out.println(sb);
	}

	private static void printArray(StringBuilder sb, String[] arr) {
		for (int i = 0; i < arr.length; i++) {
			sb.append(arr[i]).append(' ');
		}
		sb.append('\n');

	}

	private static String[] shuffleCard(String[] arr) {
		int len = arr.length;
		int halfIdx = (len + 1) / 2;

		String[] result = new String[len];

		int idx = 0;
		for (int i = 0; i < halfIdx; i++) {
			result[idx++] = arr[i];
			if (i + halfIdx < len) {
				result[idx++] = arr[i + halfIdx];
			}
		}

		return result;
	}

	private static String[] inputArray(BufferedReader br, int N) throws IOException {
		String[] arr = new String[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = st.nextToken();
		}
		return arr;
	}

}
