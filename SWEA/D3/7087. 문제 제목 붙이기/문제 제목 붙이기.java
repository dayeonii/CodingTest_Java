import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * [문제 정의]
 * - 사용할 수 있는 제목 N개 (1~100)
 * - 제목의 첫 글자에 알파벳 A to Z가 한 번씩 사용되어야 함
 * - 이렇게 만들어진 제목들 중, A to Z에서 빠진 부분이 있다면, 그 글자 이후로부터는 사용하지 않음
 * 		- e.g. Air, Dad, Ear, Blue, Ace -> A B C(없음) D
 * 				-> A와 B로 시작하는것만 사용 가능함 (3개 중 2개만/ A로 시작하는건 2개이므로 둘 중 하나)
 * - 제목들이 주어질 때, 사용할 수 있는 최대 개수 구하기
 * - 제목 문자열의 구성
 * 		- 1~30자
 * 		- 대문자 알파벳으로 시작
 * 		- 영어, 숫자, 언더바(_)
 * 
 * [입력]
 * T
 * N
 * titles of N line
 * 
 * [출력]
 * #tc count
 * 
 * [풀이]
 * - 제목은 무조건 대문자 알파벳으로 시작 -> 첫 글자 기준으로 정렬
 * 		- 문자를 숫자로 'A' - 'A' = 0
 * - 각 대문자마다 해당하는 제목의 개수들
 * 		- int []
 * - 생각해보니까 카운팅 배열을 쓰면 정렬을 할 필요가 없네
 * 		- 일단 제목들을 입력 받으면서 카운팅 배열을 채우고
 * 		- 다 채운 뒤에, 카운팅배열에 처음으로 count[i] == 0 이면, 그 이후부터는 사용불가
 * 		- 사용할 수 있는 제목의 최대 개수는 i개 (0-base index)
 * */

public class Solution {
	static int N, result;
	static int[] count;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			count = new int[26];

			for (int i = 0; i < N; i++) {
				String title = br.readLine();
				int start = title.charAt(0) - 'A';
				count[start]++;
			}

			result = 26;	// if all alphabets are present
			for (int i = 0; i < 26; i++) {
				if (count[i] == 0) {
					result = i;
					break;
				}
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}
}
