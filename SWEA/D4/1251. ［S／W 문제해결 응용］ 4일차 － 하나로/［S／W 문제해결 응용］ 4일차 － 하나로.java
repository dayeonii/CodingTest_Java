import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - N개의 섬을 연결하는 최소 비용 찾기 (mst)
 *      - 비용 : 환경부담세율E  * 각 간선 가중치의 제곱 L^2
 *      - 오버플로우 방지를 위해 간선 총합을 먼저 구한 뒤 세율 곱하기 -> Math.round() 로 정수 반올림
 * 
 * [입력]
 * T
 * N : 섬의 개수 (1~1000)
 * list of X : 각 섬의  x좌표 (0~1 000 000)
 * list of Y : 각 섬의  y좌표 (0~1 000 000)
 * E (double) : 환경 부담 세율 (0.0 ~ 1.0)
 * 
 * [출력]
 * #tc 최소환경부담금(반올림하여 정수로)
 * 
 * [풀이]
 * - 임시 좌표 저장 배열 X[], Y[]
 * 		- 좌표 정보를 받은 뒤, 모든 정점 간 가중치 구하기
 * 
 * - 간선 정보 클래스 Edge
 *      - from : 시작 섬
 *      - to : 도착 섬
 *      - weight : 가중치 (L^2)
 *      
 * - 간선리스트  ArrayList<Edge> edgeList
 
 * - union find를 위한
 *      - int parents[]
 *      - boolean union(a, b)
 *      - int find(a)
 *      
 * - 자료형
 * 		N : int/ 섬의 개수
 * 		E : double/ 환경부담세율 (0.0~1.0)
 * 		result : long/ 최종 출력 결과, 반올림하여 정수로 출력
 * 		totalWeight : long/ 최종 출력을 위한 총 가중치의 합 (L1*E + L2*E + ... ) 한 것과 (L1+L2+...)*E 한 것은 동일함
 * 		X[], Y[] : long/ 입력받아서 임시로 저장할 섬의 좌표, 추후 가중치 계산을 위해 long으로 저장
 * 		Edge { int from, int to, long weight }
 * */

public class Solution {

	// edge class
	static class Edge implements Comparable<Edge> {
		int from;
		int to;
		long weight;

		Edge(int from, int to, long weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge other) {
			return Long.compare(this.weight, other.weight);
		}
	}

	// var
	static int N;
	static double E;
	static long result, totalWeight;
	static long[] X, Y;
	static ArrayList<Edge> edgeList;

	// for union find
	static int[] parents;
	static int count;

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			// read input
			N = Integer.parseInt(br.readLine());

			// init
			init();

			// make graph
			makeGraph();

			// read input E
			E = Double.parseDouble(br.readLine());

			// run mst
			mst();

			// calculate result
			result = Math.round(E * totalWeight);

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void mst() {
		// sort
		Collections.sort(edgeList);

		// for each edge
		for (Edge e : edgeList) {
			// end
			if (count == N - 1) {
				break;
			}

			// isCycle
			if (union(e.from, e.to)) {
				totalWeight += e.weight;
				count++;
			}
		}

	}

	private static boolean union(int from, int to) {
		int rootF = find(from);
		int rootT = find(to);

		if (rootF == rootT) {
			return false;
		}

		parents[rootF] = rootT;
		return true;
	}

	private static int find(int from) {
		if (parents[from] == from) {
			return from;
		}

		return parents[from] = find(parents[from]);
	}

	private static void makeGraph() throws IOException {
		// read input X[]
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			X[i] = Integer.parseInt(st.nextToken());
		}
		// read input Y[]
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			Y[i] = Integer.parseInt(st.nextToken());
		}

		// calculate all of edge
		for (int from = 0; from < N; from++) {
			for (int to = from + 1; to < N; to++) {

				long fromX = X[from];
				long fromY = Y[from];
				long toX = X[to];
				long toY = Y[to];
				long weight = calculateWeight(fromX, fromY, toX, toY);

				edgeList.add(new Edge(from, to, weight));
			}
		}
	}

	private static long calculateWeight(long fromX, long fromY, long toX, long toY) {
		long dx = fromX - toX;
		long dy = fromY - toY;
		return dx * dx + dy * dy;
	}

	private static void init() {
		// island location
		X = new long[N];
		Y = new long[N];

		// result init
		result = 0;
		totalWeight = 0;

		// union find
		parents = new int[N];
		count = 0;
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}

		// edge
		edgeList = new ArrayList<>();

	}

}
