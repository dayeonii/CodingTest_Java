import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제]
 * 한 변의 길이가 A 인 큰 정삼각형을
 * 한 변의 길이가 B인 정삼각형으로 나눌 때
 * 나눈 정삼각형의 최소 개수 구하기
 * 
 * [풀이]
 * B는 A의 약수임
 * 
 * 삼각형을 나눌 때 한 층 내려갈 때마다 1, 3, 5, 7, ...
 * 큰 정삼각형의 한 변을 몇 칸으로 나누냐에 따라서 계산
 * 	- 2등분 : 1+3		= 4
 * 	- 3등분 : 1+3+5	= 9
 * 	- 4등분 : 1+3+5+7	= 16
 * 	- n등분 : 공차가 2인 등차수열의 n번째까지의 합
 * 
 * 
 * 공차가 n이고, 첫째항이 a인 등차수열의 n번째 항 : a+(n-1)*d
 * 등차수열의 n번째 항 까지의  : (n *( 첫째항 + 마지막항 )) / 2
 * 마지막 항 대입하면 : ( n * (a + a + (n-1) * d) ) / 2 => (n * (2a + (n-1)d ) / 2
 * 
 * [입력]
 * T
 * A B
 * 
 * [출력]
 * #tc count
 * */

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			long result = 0;

			long n = (long)A / B;
			result = (long)(n * (2 + (n - 1) * 2)) / 2;

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}
}
