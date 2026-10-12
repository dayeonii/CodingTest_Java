import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - 두 기차 간의 거리 D (1~1000)
 * - 기차 A의 시속 A (1~100)
 * - 기차 B의 시속 B (1~100)
 * - 파리의 속력 F (1~100)
 * 
 * [입력]
 * T
 * D A B F
 * 
 * [출력]
 * #tc flyDistance
 * 
 * [풀이]
 * - 기차가 만날 때 까지 걸리는 시간동안 파리는 계속 움직이고 있다
 * - 거리 = 시간 * 속력
 * - 기차는 같은 시간 동안 움직이다가 충돌 - 동시에 끝나게 되는 거니까
 * 		- t 시간동안 이동하다가 충돌한다고 했을 때
 * 		- A 기차가 움직인 거리 : A*t
 * 		- B 기차가 움직인 거리 : B*t
 * 		- 두 거리의 합 = 처음 두 기차 간의 거리 D
 * - 식을 정리하면
 * 		- (A+B) * t = D
 * 		- t = D / (A+B)
 * 		- t를 구해서
 * 		- 파리의 속력 F * t 를 해주면 파리가 움직인 거리 나옴
 * */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int D = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int F = Integer.parseInt(st.nextToken());

			double time = (double)D / (A + B);
			double result = time * F;

			sb.append('#').append(tc).append(' ').append(String.format("%.10f", result)).append('\n');
		}
		System.out.print(sb);
	}

}
