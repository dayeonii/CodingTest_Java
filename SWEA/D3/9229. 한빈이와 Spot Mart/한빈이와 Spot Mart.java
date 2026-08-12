import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			int weights[] = new int[N];
			for (int i = 0; i < N; i++) {
				weights[i] = Integer.parseInt(st.nextToken());
			}

			int result = solvingProblem(weights, M);

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static int solvingProblem(int[] weights, int M) {
		// 1. 정렬
		Arrays.sort(weights);

		// 2. 가지치기
		int start = 0;
		int end = weights.length-1;
		for (int i = 0; i < weights.length; i++) {
			if (weights[i] >= M) {
				end = i - 1;
			}
		}

		// 3. 양쪽에서 좁혀오기
		int result = -1;
		while (start < end) {
			// 여유공간이 남으면 가벼운걸 더 늘려보고
			if (weights[start] + weights[end] <= M) {
				if (weights[start] + weights[end] > result) {
					result = weights[start] + weights[end];
				}
				start++;
			} else if (weights[start] + weights[end] > M) {
				end--;
			}
		}

		return result;
	}

}
