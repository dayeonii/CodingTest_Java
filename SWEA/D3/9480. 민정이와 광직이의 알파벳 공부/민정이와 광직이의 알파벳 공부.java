import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
 * [문제 정의]
 * - 광직이가 알고 있는 단어 N개 (1~15)
 * 		- 단어 하나의 길이는 1~100
 * 		- 중복되는 단어 없음
 * 		- 모두 소문자로 이루어짐
 * - N개단어 중 몇 개를 골라서 하나의 세트로 만든다
 * 		- 이 세트는 26개의 알파벳 소문자를 모두 포함해야 한다
 * - 최대한 서로 다른 세트를 많이 만들어야 한다
 * 		- 몇 개의 세트를 만들 수 있는지 개수 세기
 * 
 * [입력]
 * T
 * N
 * 단어 N개가 한줄에 하나씩
 * 
 * [출력]
 * #tc 단어세트개수
 * 
 * [풀이]
 * - 모든 알파벳이 사용되었는가?
 * 		- boolean alpha[26]
 * - 만들 수 있는 세트 개수 (부분집합 구하기)
 * 		- 각 단어마다 포함한다 / 아니다
 * 		- 단어 개수 최대 15개 -> 2^15
 * 		- 아무 단어도 고르지 않는 경우 빼면 (2^15) - 1 = 32767
 * 		- 충분히 가능하다
 * - 부분집합 하나를 완성했을 때
 * 		- alpha[26] 이 모두 true인가 -> result++
 * */

public class Solution {

	static int N, result;
	static boolean[] alphabet;
	static boolean[] isSelectedWord;
	static List<String> alreadySet;
	static List<String> wordSet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			// input already know word
			alreadySet = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				alreadySet.add(br.readLine());
			}

			// solve
			result = 0;
			isSelectedWord = new boolean[N];
			subset(0);

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void subset(int idx) {

		if (idx == N) {
			// make subset
			init();
			for (int i = 0; i < N; i++) {
				if (isSelectedWord[i]) {
					wordSet.add(alreadySet.get(i));
				}
			}
			// check all alphabets
			if (hasAllAlphabets()) {
				result++;
			}
			return;
		}

		// include
		isSelectedWord[idx] = true;
		subset(idx+1);
		
		// exclude
		isSelectedWord[idx] = false;
		subset(idx+1);

	}

	private static void init() {
		alphabet = new boolean[26];
		wordSet = new ArrayList<>();
	}

	private static boolean hasAllAlphabets() {
		for(int i=0; i<wordSet.size(); i++) {
			String word = wordSet.get(i);
			for(int j=0; j<word.length(); j++) {
				int alphaIdx = word.charAt(j) -'a';
				alphabet[alphaIdx] = true;
			}
		}
		
		for(int i=0; i<26; i++) {
			if(alphabet[i]==false) {
				return false;
			}
		}
		return true;
	}

}
