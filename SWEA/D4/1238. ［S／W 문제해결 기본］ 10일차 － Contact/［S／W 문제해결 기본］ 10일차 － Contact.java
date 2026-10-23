import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * [문제]
 * - 비상연락망 (방향그래프) 과, 시작 당번 (시작점) 이 주어졌을 때, '가장 나중에 연락을 받게 되는 사람' 중 번호가 가장 큰 사람 찾기
 * - 시작 당번은 다자 간 통화로 동시에 연락을 취한다
 * 		- 그래프의 간선 가중치에 대한 처리가 필요 없음 (가중치가 모두 1으로 동일한 그래프)
 * - 당번은 1번부터 특정번호까지 모두 있지 않을 수도 있다
 * 
 * [입력]
 * 테케10개
 * 데이터길이 시작점
 * from, to, from, to, ...
 * 
 * [출력]
 * #tc 가장마지막에받는사람
 * 
 * [풀이]
 * - 리스트 정보 관리 : 인접리스트 사용 (실제 연결된 사람만 타고 들어가서 보면 되니까, 인접 행렬보다 나음)
 * - 시작점에서부터 도달할 수 있는 마지막까지 찾기
 * 		- 동시에 퍼져나가는 그림이니까 bfs
 * - 정점 정보 관리
 * 		- 범위 : 1 ~ 100
 * 		- 중간중간 비어있는 정점이 있을 수 있음 -> 정점이 총 몇 개 인지는 from, to 정보를 다 읽어봐야 안다
 * 		- 그러면 그냥 [100+1] 으로 만들어두고 (정점 번호 1-based), 간선 정보를 받자
 * - bfs
 * 		- 큐 생성
 * 		- 시작점 넣기 & 방문
 * 
 * 		- 큐가 빌 때까지
 * 			- 하나 꺼내서
 * 			- 이 정점에서 갈 수 있는 다른 정점들을 찾기
 * 				- 아직 방문하지 않았다면
 * 					- 큐에 넣고 방문 처리
 * 					- 마지막에 도달한 번호 갱신 -> 이렇게 하면 안돼! 마지막에 본 애들 중 가장 큰 번호를 봐야하니까
 * 	- 마지막에 도달한 번호 갱신하기
 * 		- 단계(깊이)를 저장해서, bfs 종료 후에
 * 		- max 단계 중 정점 번호가 제일 큰 것을 찾기
 * 		- visited 배열에 단계를 저장해보자
 * */

public class Solution {

	final static int MAX_SIZE = 100;

	static int N, start, result;
	static int[] visited;
	static ArrayList<Integer>[] edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		for (int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());

			// init
			result = 0;
			visited = new int[MAX_SIZE + 1];
			edgeList = new ArrayList[MAX_SIZE + 1];
			for(int i=0; i<MAX_SIZE+1; i++) {
				edgeList[i] = new ArrayList<>();
			}

			// read input edge info
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				edgeList[from].add(to);
			}

			// run bfs
			bfs();

			// find last one
			int maxLen = 0;
			for (int i = 1; i <= MAX_SIZE; i++) {
				maxLen = Math.max(maxLen, visited[i]);

				if (visited[i] == maxLen) {
					result = Math.max(i, result);
				}
			}

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void bfs() {
		// 큐 생성
		ArrayDeque<Integer> queue = new ArrayDeque<>();

		// 시작점 넣기 & 방문
		queue.offer(start);
		visited[start] = 1;

		// 큐가 빌 때까지
		while (!queue.isEmpty()) {
			// 하나 꺼내서
			int current = queue.poll();

			// 이 정점에서 갈 수 있는 다른 정점들을 찾기
			for (int e : edgeList[current]) {
				// 아직 방문하지 않았다면
				if (visited[e] == 0) {
					// 큐에 넣고 방문 처리
					queue.offer(e);
					visited[e] = visited[current] + 1;
				}
			}

		}
	}
}
