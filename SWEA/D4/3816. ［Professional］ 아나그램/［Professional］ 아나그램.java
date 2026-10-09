import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - 아나그램 : 문자열의 문자를 모두 사용해서 재배열한 것 => 순열
 * - 문자열 S1, S2
 * 		- S2의 부분문자열 중 S1의 아나그램인 것 구하기
 * 
 * [입력]
 * T
 * S1 S2
 * 
 * [출력]
 * #tc countOfAnagram
 * 
 * [풀이]
 * - S1, S2의 길이 : 1 ~ 100 000
 * - S1의 아나그램 개수 : 100000! -> 모두 만들어서 비교할 수 없다
 * - S2의 부분문자열 개수 : 
 * 		- 길이 : S1의 아나그램이어야 한다 -> S1의 길이 인 부분문자열만 찾으면 된다
 * 		- 개수 : S1의 길이를 K, S2의 길이를 N라고 했을 때 N-K+1
 * 		- 부분 문자열을 탐색 : 슬라이딩 윈도우로 한 칸씩 밀어가면 될 듯
 * - S2의 부분문자열이 S1의 아나그램에 속하는지 검사
 * 		- 알파벳 카운팅 배열
 * 		- S1에 속한 알파벳의 개수와, 지금 보고있는 S2의 window 부분에 속한 알파벳의 개수를 비교
 * - 그럼 window를 밀면서 이걸 계속 반복하면 시간복잡도는?
 * 		- 윈도우 이동 : N-K+1 (S2가 더 크니까 약 N번)
 * 		- 1회 이동 시 알파벳 배열 비교 : 26회 상수
 * 		- 총 시간복잡도 O(N)
 * 		- worst case : S2 = 100 000 이므로 가능
 * */

public class Solution {

	static int result;
	static int[] alphaS1, alphaS2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			String s1 = st.nextToken();
			String s2 = st.nextToken();

			// init global vars
			result = 0;
			alphaS1 = new int[26];
			alphaS2 = new int[26];

			// count alphabet in S1
			for (int i = 0; i < s1.length(); i++) {
				int idx = s1.charAt(i) - 'a';
				alphaS1[idx]++;
			}

			// count alphabet in S2's first window
			int size = s1.length();
			for (int i = 0; i < size; i++) {
				int idx = s2.charAt(i) - 'a';
				alphaS2[idx]++;
			}

			// check first window
			if (check())
				result++;

			// next window
			for (int i = size; i < s2.length(); i++) {
				// add new char entering window
				alphaS2[s2.charAt(i) - 'a']++;

				// remove old char leaving window
				alphaS2[s2.charAt(i - size) - 'a']--;

				// check
				if (check())
					result++;
			}

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static boolean check() {
		for (int i = 0; i < 26; i++) {
			if (alphaS1[i] != alphaS2[i]) {
				return false;
			}
		}
		return true;
	}

}
