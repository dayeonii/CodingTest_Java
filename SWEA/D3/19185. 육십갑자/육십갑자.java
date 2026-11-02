import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제]
 * - 이름을 알고싶은 년도 Y가 주어지면, 그 년도는 어느 이름인지 반환하기 (총 Q개)
 * - N개의 문자열 s : 첫글자, (N=10)이면 10천간을 나타냄
 * - M개의 문자열 t : 두번째, (M=12)이면 12지지를 나타냄
 * - 문자열 s,t를 훑는 인덱스 (1년마다) 는 동시에 1칸씩 이동한다
 * 		s = {“a”, “b”, “c”}, t = {“d”, “e”, “f”, “g”} 라고 하면
 * 		1년 : ad / 2년 : be / 3년 : cf / ... / 12년 : cg / 13년 : ad
 * 
 * [풀이]
 * - 몇 년마다 처음으로 돌아오는가? : N*M
 * 		즉 1년 : s1+m1 / N*M년 : sn+tm
 * 		새로운 s1+m1은 (N*M)+1년에 돌아옴
 * - 테케에 주어진 육십갑자를 기준으로
 * 		2018 -> 2018 % 60 = 38, 38번째 이름 (musul)
 * 		10천간 ) 38 % 10 -> 8번째 천간 (mu)
 * 		12간지 ) 38 % 12 -> 2번째 지지 (sul)
 * - 이렇게 한 자리씩 계산해도 될 것 같고... 아니면 육십갑자 배열을 싹 만들어서 38번째를 바로 접근해도 될 것 같음
 * 		근데 제약조건에 1 <= N, M <= 20 이니까 그냥 전자의 방법이 나을듯
 * - Y는 1 <= Y <= 10^9 (10억) 이니까 Integer로 해도 okay
 * 
 * [입력]
 * T
 * N M
 * N개의 문자열 s
 * M개의 문자열 t
 * Q
 * Q줄의 Y
 * 
 * [출력]
 * #tc 각 Y마다 해당하는 이름 총 Q개를 공백 구분으로 한 줄에
 * */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			sb.append('#').append(tc).append(' ');

			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			String[] s = new String[N + 1];
			String[] t = new String[M + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				s[i] = st.nextToken();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				t[i] = st.nextToken();
			}

			int Q = Integer.parseInt(br.readLine());

			for (int i = 0; i < Q; i++) {
				int year = Integer.parseInt(br.readLine());

				int first = year % N;
				int second = year % M;
				
				if(first==0)
					first = N;
				if(second==0)
					second = M;

				sb.append(s[first]).append(t[second]).append(' ');
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}
