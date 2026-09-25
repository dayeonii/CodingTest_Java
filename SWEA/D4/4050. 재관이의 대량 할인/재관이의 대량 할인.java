import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - 옷 3개 -> 제일 저렴한 옷은 free
 * - 옷을 3개씩 짝지으면 할인 가능
 * - 사려는 옷들을 어떻게 짝지으면 할인을 많이 받을 수 있는지 찾기
 * - 사려는 옷의 개수 N : 1 ~ 100 000
 * 
 * [입력]
 * T
 * N
 * N개의 옷 가격
 * 
 * [출력]
 * #tc 지불하는최소금액
 * 
 * [풀이]
 * - N개의 옷을 3개씩 세트로 만드는 모든 경우의 수
 * 		- nC3 * n-3 C 3 * n-6 C 3 * ...
 * 		- N 최대값 100 000 이므로 nC3만 해도 약 (100 000 ^ 3) / 6 = 1.67 * 10^14
 * 		- 완전 탐색 불가능
 * - 최대로 할인받으려면?
 * 		- 정렬 후 쌍으로 묶기
 * 		- [100, 90, 80, 70, 60, 50] 의 옷이 있으면
 * 		- 여기서 할인받을 수 있는 가장 비싼 옷은 80
 * 				- 100, 90이 무료가 되려면 얘네보다 더 비싼 옷이 2개, 1개씩 필요함
 * 				- 따라서 처음으로 할인받을 수 있는 가장 비싼 금액은 80
 * 		- [100,90,80]을 묶고 남은 옷들에게 똑같이 적용
 * */

public class Solution {

	static int N;
	static int[] clothes;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// input
			N = Integer.parseInt(br.readLine());

			clothes = new int[N];
			int total = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				clothes[i] = Integer.parseInt(st.nextToken());
				total += clothes[i];
			}
			
			// sort
			Arrays.sort(clothes);
			
			// count
			int discount = 0;
			for(int i=N-3; i>=0; i-=3) {
				discount += clothes[i];
			}
			
			// output
			sb.append('#').append(tc).append(' ').append(total-discount).append('\n');
		}
		System.out.print(sb);
	}
}
