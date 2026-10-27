import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * [문제]
 * - 알파벳 소문자로 이루어진 N자리 단어 (길이 1~10)
 * - 팰린드롬 : 앞에서부터 읽어도 뒤에서부터 읽어도 같은 글자
 * 			 (e.g. racecar )
 * - 주어진 단어를 '재구성'하여 새로운 단어를 만들고
 * - 그 단어의 '연속된' 부분문자열 중에서 팰린드롬인 것의 개수를 최대화 하기
 * 
 * [풀이]
 * - 단어를 재구성 할 수 있다
 * 		- 단어가 갖고 있는 알파벳 개수의 쌍을 맞춰서 중간 글자 기준으로 최대한 대칭으로 만들면?
 * 		- 연속된 부분문자열이기 때문에 대칭을 맞추는게 제일 빠를 것이라고 생각함
 * - 단어에 나온 알파벳 개수 확인
 * 		- int alphabet[26] 사용
 * 		- 일단 word를 한 글자씩 읽어가면서 알파벳 개수를 카운트
 * 		- 알파벳 개수가 나온 것 중에 2개 이상이고 짝수개인 것 -> 팰린드롬을 만들 수 있다 (양쪽에 배치할 수 있으니까)
 * 		- 알파벳 개수 중 1개인 것이 1글자라면 -> 얘를 중간에 넣으면 됨
 * 		- 알파벳 개수 중 2개 이상이지만 홀수인 것 -> 짝수만큼만 사용하고 나머지 한 글자는 버리게 됨
 * 				- 만약 알파벳 개수 중 1개인것이 0개인 상태에서 이런 애가 나온다면 중간에 넣어줄 수는 있을 듯
 * - 가지 치기?
 * 		- 일단 각 단어의 각 1자리에 대해서는 모두 만족하니까, 자릿수만큼 기본값으로 가지게 됨
 * 		- "aaa" 이 주어져도, "a", "a", "a" 은 각각 따로 보니까 자릿수만큼 카운트
 * - 시간복잡도
 * 		- 주어진 단어를 재배열 하는 가짓수 N! (worst case 10! = 약 360만)
 * 		- 
 * 
 * [입력]
 * T
 * W
 * 
 * [출력]
 * #tc maxCount
 * */

public class Solution {

	static int[] alphabet;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			String word = br.readLine();
			int result = 0;

			int N = word.length();

			alphabet = new int[26];
			for (int i = 0; i < N; i++) {
				int idx = word.charAt(i) - 'a'; // 알파벳 위치
				alphabet[idx]++;
			}

			// 알파벳 개수에 따라서 상한을 각각 더해서 result에 합산
			for (int i = 0; i < 26; i++) {
				result += (alphabet[i] * (alphabet[i]+1) / 2);
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}
