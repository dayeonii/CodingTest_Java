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
		int halfIdx = (len % 2 == 0) ? len / 2 : len / 2 + 1;

		String[] result = new String[len];
		String[] firstArr = Arrays.copyOfRange(arr, 0, halfIdx);
		String[] secondArr = Arrays.copyOfRange(arr, halfIdx, len);

		int j = 0;
		for (int i = 0; i < len; i += 2) {
			result[i] = firstArr[j];
			if (j < secondArr.length) {
				result[i + 1] = secondArr[j];
			}
			j++;
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
