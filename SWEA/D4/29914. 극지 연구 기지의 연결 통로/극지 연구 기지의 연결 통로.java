import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * [입력]
 * T
 * N M
 * N*M지도  (1은 기지, 0은 빈공간)
 * 
 * [출력]
 * #tc 통로길이최소값
 * 
 * [문제]
 * - 모든 구역을 연결할 수 있는 최소 통로 길이 구하기
 * 		- 한 연구 기지에서 다른 모든 연구기지로 갈 수 있으면 연결된 것으로 본다 (간접 연결O)
 * 		- 완성된 모습 : 하나의 집합에 속해있어야 함
 * - 통로의 제약
 * 		- 통로 하나는 길이 2 이상의 일직선 모양
 * 		- 같은 연구기지로 되돌아오는 통로는 없어야 한다 (사이클이 없어야 한다)
 * 		- 통로와 통로는 교차할 수 있다	
 * 		- 통로와 기지는 교차할 수 없다
 * - 모든 구역을 연결할 수 없는 경우는 -1을 출력한다
 * 
 * [풀이]
 * - mst 구하기 : 비용 == 통로의 길이
 * - union-find를 사용하기 위해 필요한 것
 * 		- union(a,b) : 트리 a와 b를 합친다. 합칠 수 있으면 true, 없으면 false
 * 		- find(a) : 트리a의 대표자를 찾는다
 * 		- parent[] : 각 노드가 속한 트리의 대표자를 기록
 * - Edge class
 * 		- int from, to, cost
 * 				from : 시작 노드
 * 				to : 도착 노드
 * 				cost : 시작 노드에서 도착 노드로 가는 비용 (통로의 길이)
 * - PriorityQueue<Edge> : mst를 만들기 위한 엣지리스트 (정렬)
 * 		- Edge class에서 comparable 구현, cost가 작은 것 부터 정렬
 * 		- 엣지리스트를 만드는 방법
 * 			- map[i][j]>1 인 곳에서 4방으로 뻗었을 때 (연구기지 번호가 2부터 시작)
 * 			- 다른 기지에 도착하면 그것을 엣지 하나로 본다
 * - 기지에 번호를 매길 것인가? (서로 다른 기지임을 구분하기 위함)
 * 		- 번호를 매기면 기지의 개수도 구할 수 있음
 * 		- bfs를 사용
 * - MST 구하기
 * 		- 엣지리스트를 최소 비용인 것부터 정렬한다
 * 		- 리스트에서 하나를 골랐을 때, from 과 to가 같은 집합인지 확인한다 ( union(from, to) == true 로 한 번에 처리)
 * 			- 같은 집합이 아니라면 연결시켜 준다 (합친다)
 * 		- 그 다음 엣지를 보고 반복한다
 * 		- 선택한 엣지 개수가 가 노드개수 - 1 이면 완료됨
 * */

public class Solution {

	static int result;
	static int N, M;
	static int[][] map;

	static int[] parent;
	static ArrayList<Edge> edgeList;

	static int areaIdx;
	static boolean[][] visited;
	static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	static class Edge implements Comparable<Edge> {
		int from, to, cost;

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge other) {
			return Integer.compare(this.cost, other.cost);
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// init
//			result = Integer.MAX_VALUE;
			result = 0;
			map = new int[N][M];
			visited = new boolean[N][M];
			edgeList = new ArrayList<>();

			// read map input & numbering area
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// separate area - 완료 확인
			areaIdx = 2;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] == 1) {
						bfs(i, j);
						areaIdx++;
					}
				}
			}
			int areaCnt = areaIdx - 2;

			// make edgeList
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j] != 0) {
						makeEdgeList(i, j);
					}
				}
			}

			// make mst
			parent = new int[areaIdx];
			for (int i = 2; i < areaIdx; i++) {
				parent[i] = i;
			}

			Collections.sort(edgeList);

			int edgeCnt = 0;

			for (Edge e : edgeList) {
				if (union(e.from, e.to)) {
					result += e.cost;
					edgeCnt++;
				}

				if (edgeCnt == areaCnt - 1) {
					break;
				}
			}
			
			// check all area connected
			if(edgeCnt!=areaCnt-1) {
				result = -1;
			}

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static boolean union(int from, int to) {
		int rootF = find(from);
		int rootT = find(to);

		if (rootF == rootT) {
			return false;
		}

		parent[rootF] = rootT;
		return true;
	}

	private static int find(int node) {
		if (parent[node] == node) {
			return node;
		}
		return parent[node] = find(parent[node]);
	}

	private static void makeEdgeList(int x, int y) {
		int curIdx = map[x][y];

		for (int dir = 0; dir < 4; dir++) {
			int nx = x + delta[dir][0];
			int ny = y + delta[dir][1];
			int cost = 0;

			while (canGo(nx, ny)) {

				// 같은 기지 내 구역임
				if (map[nx][ny] == curIdx) {
					break;
				}

				// 통로를 깔 수 있음
				if (map[nx][ny] == 0) {
					cost++;
				}

				// 다른 기지에 도착함 - 엣지 추가
				if (map[nx][ny] > 1 && map[nx][ny] != curIdx) {
					// 통로의 최소 길이는 2부터
					if (cost >= 2) {
						edgeList.add(new Edge(curIdx, map[nx][ny], cost));
						break;
					}
				}

				nx += delta[dir][0];
				ny += delta[dir][1];
			}

		}

	}

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	private static void bfs(int startX, int startY) {

		ArrayDeque<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(startX, startY));
		visited[startX][startY] = true;
		map[startX][startY] = areaIdx;

		while (!queue.isEmpty()) {
			Point current = queue.poll();
			int curX = current.x;
			int curY = current.y;

			for (int dir = 0; dir < 4; dir++) {
				int nx = curX + delta[dir][0];
				int ny = curY + delta[dir][1];

				if (canGo(nx, ny) && !visited[nx][ny] && map[nx][ny] != 0) {
					queue.offer(new Point(nx, ny));
					map[nx][ny] = areaIdx;
					visited[nx][ny] = true;
				}
			}
		}
	}

	private static boolean canGo(int nx, int ny) {
		return nx >= 0 && nx < N && ny >= 0 && ny < M;
	}

}
