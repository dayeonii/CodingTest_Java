import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - N개의 도시 (1 ~ 300 000)
 * - 관문을 통한 이동 제한 거리 D ( 1 ~ N )
 * - 어느 도시에 관문을 설치하면, 그 도시와 거리 D 이하인 다른 도시에 대해 이동 가능
 * 		- 설치한 도시 -> 다른 도시
 * 		- 다른 도시 -> 설치한 도시   둘 다 이동할 수 있다
 * - 도시 간 거리
 * 		- 도시i와 도시i+1 거리는 1
 * - 모든 도시에 대해 '직접적으로' 이동 가능하도록 추가로 설치해야 하는 관문 개수 구하기
 * 		- 직접적으로 이동 한다
 * 
 * [입력]
 * T
 * N D
 * 도시의 관문 정보 (1번 도시 ~ N번 도시)
 * 
 * [출력]
 * #tc 추가로설치할관문개수
 * 
 * [풀이]
 * - 직접적으로 연결되어 있어야 한다는 의미?
 * 		- 테케1번 N=6, D=2, [1 0 0 0 0 1]
 * 		- 3번 도시에 문을 설치하면 모든 도시에 이동은 가능함
 * 		- 그러나 6번 도시가 4,5번을 제외한 다른 도시에 가려면 한 번에 갈 수 없고
 * 		- 4,5번을 '거쳐서' 가야한다 -> 간접적으로 이동
 * 		- 6번 도시 또한 직접적으로 이동을 하기 위해선 4번이나 5번에 추가로 설치해주면 된다
 * - N개의 도시에 대해서 D 간격마다 관문이 설치되어 있어야 한다
 * 		- cities[] 를 돌면서 설치되어야 하는 간격마다 설치되어 있는지 점검
 * 		- 시간복잡도 : O(N^D) -> 안됨
 * - N의 worst case가 300 000 이니까
 * 		- O(NlogN) 또는 O(N)가 되는 로직을 사용해야 한다
 * - O(N) : cities[] 를 한 번 읽으면서 끝낼 수 있을까? greedy 하게
 * 		- 도시 1번부터 읽으면서, 관문이 있어야 할 자리에 없다면 -> 설치
 * 		- 직접적인 연결을 위해서 지켜야 하는 것 : D 간격마다 설치가 되어있어야 한다
 * 
 * - point! 0번과 N+1번 도시는 관문이 설치되어 있음이 보장된다
 * 		- 이게 의미하는 바는?
 * */

public class Solution {

	static int N, D;
	static int result;
	static int[] cities;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			D = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			cities = new int[N];
			for (int i = 0; i < N; i++) {
				cities[i] = Integer.parseInt(st.nextToken());
			}
			
			result = 0;

			int idx;
			int distance = 0;
			for (idx = 0; idx < N; idx++) {
				if (cities[idx] == 0) {
					distance++;
					if (distance == D) {
						cities[idx] = 1;
						result++;
						distance = 0;
					}
				} else {
					distance = 0;
				}
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}
}
