import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제]
 * - 일렬로 놓인 3개의 컵
 * - 그 중 하나에 방울이 들어있음
 * 
 * - 컵을 섞는다
 * 		- 인접한 두 컵을 교환한다 (왼-가운데 or 가운데-오)
 * 		- 동전 앞면 : 왼-가운데 교환
 * 		- 동전 뒷면 : 가운데-오 교환
 * 		- 컵을 섞을 때 방울이 1번 울린다
 * 
 * - 방울 소리가 K번 들렸다 -> 방울이 들어있는 컵이 K번 이동했다 (!= 컵을 섞은 횟수)
 * 
 * - 현재 방울이 있을 확률이 가장 높은 컵의 위치를 구하라
 * 
 * [풀이]
 * - 시뮬레이션?
 * 		- 처음 방울의 위치를 알고 있고
 * 		- 컵은 무조건 인접한 컵 끼리만 스왑된다
 * - K가 컵을 섞은 총 횟수는 아니지만, 결국 K번 방울이 이동했으니까 그것만 따라가면 되지 않을까?
 * 		- 스왑하는 컵 (a,b)가 있고
 * 		- 시작은 방울이 a에 있었고, 방울이 울렸다면 무조건 b에 있음
 * 		- 이 부분만 따라가면?
 * - 확률 : 동전의 앞뒷면 확률만 고려하면 될 듯
 * 
 * [입력]
 * T
 * S K
 * 
 * S는 초기 컵/방울의 위치 (맨왼쪽에 있으면 o.. / 가운데 .o. / 오른쪽 ..o)
 * K는 방울 소리 들은 횟수
 * 
 * [출력]
 * #tc 방울이 있을 확률이 가장 높은 컵 위치 (왼:0, 가운데:1, 오:2)
 * 
 * */

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// read input
			StringTokenizer st = new StringTokenizer(br.readLine());
			String S = st.nextToken();
			int K = Integer.parseInt(st.nextToken());

			// solve
			int startLoc = S.indexOf('o');
			int result = 0;

			// case 1 - K is 0
			if (K == 0) {
				result = startLoc;
			}

			// case 2 - K is not 0
			else {

				// case 2-1 - startLoc is left/right
				if (startLoc != 1) {
					// K is odd -> go to center
					if (K % 2 != 0) {
						result = 1;
					}
					// K is even -> go to left
					else {
						result = 0;
					}
				}

				// case 2-2 - startLoc is center
				else {
					// K is odd -> go to left
					if (K % 2 != 0) {
						result = 0;
					}
					// K is even -> go to center
					else {
						result = startLoc;
					}
				}
			}

			// print output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}
}
