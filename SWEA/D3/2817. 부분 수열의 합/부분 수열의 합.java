import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/*
 * [문제]
 * - N개의 자연수에서 최소 1개 이상을 선택하여 그 합이 K가 되는 경우의 수 찾기
 * 		- N : 1~20
 * 		- K : 1~1000
 * 		- 수열의 원소 : 1~100
 * 
 * [입력]
 * T
 * N K
 * N개의 자연수 수열 (공백으로 구분)
 * 
 * [출력]
 * #tc 경우의수
 * 
 * [풀이]
 * - 부분집합 만들기 : 2^N -1 (공집합 제외)
 * 		2^20 -> 10 ^ 6 (약 100만)
 * - 부분집합을 하나씩 만들 때마다 K가 되는 경우 찾기
 * - 시도
 * 		- N개의 수열은 모두 자연수로 이루어져 있음 (음수가 없다)
 * 		- 현재 보는 원소가 K보다 크거나 같으면 패스
 * 		- 그냥 처음에 정렬해서 잘라내야겠다 => !!! 인덱스가 달라져서 맞추기 어렵네
 * - 가지치기
 * 		- sum을 매개변수로 전달해서 바로바로 확인하고 가지치기 할 수 있도록 하기
 * */

public class Solution {

	static int N, K;
	static int result;
	static List<Integer> arr;
	static boolean[] selected;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr.add(Integer.parseInt(st.nextToken()));
			}

			// subsuet
			result = 0;
			selected = new boolean[N];
			subset(0,0);

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void subset(int idx, int sum) {
		// pruning
		if(sum>K) {
			return;
		}
		
		if(sum==K) {
			result++;
			return;
		}
		
		// base line
		if(idx==N) {
			return;
		}
		
		// recursive
		selected[idx] = true;
		subset(idx+1, sum+arr.get(idx));
		
		selected[idx] = false;
		subset(idx+1, sum);
	}
}
