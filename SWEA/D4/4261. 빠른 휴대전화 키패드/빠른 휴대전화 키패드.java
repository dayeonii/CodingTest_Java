import java.util.*;
import java.io.*;

/*
 * [문제 정의]
 * - 키패드
 * 		- 1       / 2(abc) / 3(def)
 * 		- 4(ghi)  / 5(jkl) / 6(mno)
 * 		- 7(pqrs) / 8(tuv) / 9(wxyz)
 * - 입력값 S : 1~1000자리의 숫자 (2~9로 이루어짐)
 * - 단어사전 (단어N개) : 모든 단어의 길이 합은 1 000 000 이하
 * - 입력값 S가 들어오면 -> 이 입력값으로 만들 수 있는 사전 안의 단어 개수를 찾기
 * 
 * [입력]
 * T
 * S N
 * N개 단어
 * 
 * [출력]
 * #tc 단어개수
 * 
 * [풀이]
 * - brute force : 숫자 S로 만들 수 있는 모든 문자열을 생성해서 사전과 비교
 * 		- 최대 1000자리, 한 자리당 최대 4가지 알파벳 가능 -> 4^1000 불가능
 * - 그렇다면 모든 가능한 단어를 만들어서 비교하는게 아니라, 반대로 하면?
 * 		- 사전의 각 단어 -> 이 단어가 S로 입력 가능한지 판별
 * - 로직	
 * 		- 단어 길이부터 비교 : 사전 속 단어의 길이 != S의 길이 -> 바로 탈락
 * 		- 사용된 알파벳 비교 : S[i]로 가능한 알파벳 그룹에 사전단어[i]가 속하는가?
 * 							-> 없으면 이 단어 탈락
 * 		- 위 비교를 모두 통과하면 -> 가능한 단어 count++
 * - 예시
 * 		- tc1 : S = 6666 / N = 3 / dict[N] = { tomo, mono, dak }
 * 		- dict[0] = "tomo"
 * 			- 1. compare length
 * 			- (S.len = 4) == (dict[0].len = 4) -> ok
 * 			- 2. compare alphabet
 * 			- S[0] = 6 -> possible 'm' 'n' 'o'
 * 			- dict[0][0] = 't' -> impossible -> fail
 * 		- dict[1] = "mono"
 * 			- 1. compare length
 * 			- (S.len = 4) == (dict[1].len = 4) -> ok
 * 			- 2. compare alphabet
 * 			- dict[1][0] = 'm' -> possible
 * 			- dict[1][1] = 'o' -> possible
 * 			- dict[1][2] = 'n' -> possible
 * 			- dict[1][3] = 'o' -> possible => count++
 * 		- dict[2] = "dak"
 * 			- 1. compare length
 * 			- (S.len = 4) == (dict[2].len = 3) -> fail
 * - 시간복잡도
 * 		- 사전에 들어있는 모든 단어 길이의 합 1 000 000
 * 		- 각 단어의 문자를 한번씩 확인 -> 최대 1 000 000 검사
 * 		- O(1 000 000) 가능
 * 
 * [이슈]
 * - S[j]와 dict[i][j]가 대응하는지 확인하는 방법
 *   (i : 몇번째 단어인가, j:몇번째 글자인가) 
 * */

public class Solution {
	static int N, result;
	static String S;
	static List<String> dict;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			S = st.nextToken();
			N = Integer.parseInt(st.nextToken());

			// input dict
			dict = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				dict.add(st.nextToken());
			}

			// solve
			result = 0;
			solve();

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void solve() {

		for (int i = 0; i < N; i++) {
			String word = dict.get(i);

			// 1. compare length
			if (S.length() != word.length()) {
				continue;
			}

			// 2. compare alpha
			boolean same = true;
			for (int j = 0; j < S.length(); j++) {
				char mappedDigit = convertToDigit(word.charAt(j));
				if (S.charAt(j) != mappedDigit) {
					same = false;
					break;
				}
			}

			// 3. count
			if (same) {
				result++;
			}
		}

	}

	private static char convertToDigit(char charAt) {

		switch (charAt) {
		case 'a':
		case 'b':
		case 'c':
			return '2';
		case 'd':
		case 'e':
		case 'f':
			return '3';
		case 'g':
		case 'h':
		case 'i':
			return '4';
		case 'j':
		case 'k':
		case 'l':
			return '5';
		case 'm':
		case 'n':
		case 'o':
			return '6';
		case 'p':
		case 'q':
		case 'r':
		case 's':
			return '7';
		case 't':
		case 'u':
		case 'v':
			return '8';
		case 'w':
		case 'x':
		case 'y':
		case 'z':
			return '9';
		default:
			return '0';
		}
	}
}
