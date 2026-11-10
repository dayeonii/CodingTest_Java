import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [문제]
 * - 1번부터 N번까지의 집이 있음
 * 		- i번 집은 시작점으로부터 x_i 만큼 떨어져있다
 * 
 * - K개의 발전소를 설치
 * 		- 전선의 길이를 최소로 하는 순열 찾기
 * 		- 공급량은 상관없음, 오로지 집과 발전소의 거리만 고려
 * 
 * [풀이]
 * - 제한사항
 * 		- K의 범위 1 ~ 100 000
 * 		- K를 가지고 순열 -> K!
 * 		- 12! 부터 터지는데 가지치기로 되나?? -> 전혀 안될 듯
 * - 집을 K개의 뭉텅이로 나누는 방법
 * 		- 한 뭉텅이 전체에 전선이 깔린다고 생각하고 뭉텅이시작~뭉텅이끝 전선길이로 구하면 되고
 * 		- dfs?
 * 		- 뭉텅이로 나누기 위해서는 경계를 K-1개 정해주면 된다
 * 			- K C K-1 -> 이것 또한 안됨
 * - 이 문제에서 필요한 알고리즘의 시간복잡도
 * 		- worst case 100 000 이니까 O(K) 아니면 O(KlogK) 정도
 * 
 * - 그리디하게
 * 		- K+1 개의 구간을 나누는 건 맞는 것 같은데
 * 		- 테케 그리면서 드라이런 해보니까
 * 		- 집 간격이 '가장 큰 것부터' 없애주고, 남은 간격을 더하면 됨
 * 
 * [입력]
 * T
 * N K
 * N개의 정수 (집의 위치, 공백으로 구분)
 * 
 * [출력]
 * #tc 전선길이최솟값
 * */

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int result = 0;

			// read input
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			int[] houses = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				houses[i] = Integer.parseInt(st.nextToken());
			}

			// calculate distance between houses
			int[] distance = new int[N - 1];
			int idx = 0;
			for (int i = 1; i < N; i++) {
				distance[idx++] = houses[i] - houses[i - 1];
			}

			// sort distance
			Arrays.sort(distance);
			int disLen = distance.length;

			// remove top K-1 distance
			int count = 0;
			for (int i = disLen - 1; i >= 0; i--) {
				if (count == K - 1) {
					break;
				}
				distance[i] = 0;
				count++;
			}

			// sum of remain distance
			for (int i = 0; i < disLen; i++) {
				result += distance[i];
			}

			// print output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}
}
