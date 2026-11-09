import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제]
 * - 각각의 상태인 점이 몇 개씩 있는지 구하여라
 * 		- 점이 완전히 직사각형 내부에 있음
 * 		- 점이 직사각형 네 변 중 적어도 하나의 위에 있음
 * 		- 점이 완전히 직사각형 외부에 있음
 * 
 * [풀이]
 * - 입력을 받으면서 바로바로 판단하면 되는 것?
 * 		- 내부에 있는 경우
 * 			- (x1 < x < x2) && (y1 < y < y2)
 * 		- 직사각형 변 위에 있는 경우
 * 			- x = x1 | x = x2 | y = y1 | y = y2
 * 		- 외부에 있는 경우 : 내부의 반대 조건 (!)
 * 
 * - 편의상 점의 좌표는 (a,b)로 받겠다
 * 
 * [입력]
 * T
 * x1, y1, x2, y 직사각형 정보
 * N 점의개수
 * N개의 줄에 x y 쌍으로 점 위치
 * 
 * [출력]
 * #tc 내부 위에 외부
 * */

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int inner = 0;
			int outer = 0;
			int on = 0;

			// input rectangle coordinate
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			// input point coordinate
			int N = Integer.parseInt(br.readLine());
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				// 내부에 있는 경우
				if((x1<a && a<x2) && (y1<b && b<y2)) {
					inner++;
					continue;
				} 
				// 변 위에 있는 경우
				else if ( ((y1<=b && b<=y2) && a==x1)
						|| ((y1<=b && b<=y2) && a==x2)
						|| ((x1<a && a<x2) && b==y1)
						|| ((x1<a && a<x2) && b==y2)) {
					on++;
					continue;
				}
				// 외부에 있는 경우
				else {
					outer++;
					continue;
				}
			}

			sb.append('#').append(tc).append(' ').append(inner).append(' ').append(on).append(' ').append(outer)
					.append('\n');
		}
		System.out.print(sb);
	}
}
