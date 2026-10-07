import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - N개의 섬을 연결하는 최소 비용 찾기 (mst)
 * 		- 비용 : 환경부담세율E  * 각 간선 가중치의 제곱 L^2
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
 * - 간선 정보 클래스 Edge
 * 		- from : 시작 섬
 * 		- to : 도착 섬
 * 		- weight : 가중치 (L^2)
 * - 간선리스트  edgeList[]

 * - union find를 위한
 * 		- int parents[]
 * 		- boolean union(a, b)
 * 		- int find(a)
 * */

public class Solution {

	static int N;
	static long result;
	static double E;
	static ArrayList<Edge> edgeList;
	static int[] parents;

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
		public int compareTo(Edge o) {
			return Long.compare(this.weight, o.weight);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());

			// init
			init();

			// input islands info
			int[] X = new int[N];
			int[] Y = new int[N];

			StringTokenizer st1 = new StringTokenizer(br.readLine());
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				X[i] = Integer.parseInt(st1.nextToken());
				Y[i] = Integer.parseInt(st2.nextToken());
			}

			// caculate weight
			int idx = 0;
			for (int i = 0; i < N; i++) {
				for (int j = i + 1; j < N; j++) {
					long fromX = X[i];
					long fromY = Y[i];
					long toX = X[j];
					long toY = Y[j];
					long weight = getLength(fromX, fromY, toX, toY);
					edgeList.add(new Edge(i, j, weight));
				}
			}

			E = Double.parseDouble(br.readLine());

			// solve
			long totalWeight = mst();
			result = Math.round(E * totalWeight);

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void init() {
		edgeList = new ArrayList<>();
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	private static long getLength(long fromX, long fromY, long toX, long toY) {
		long dx = fromX - toX;
		long dy = fromY - toY;

		return dx * dx + dy * dy;
	}

	private static long mst() {
		// 0. init
		long total = 0;

		// 1. sort
		Collections.sort(edgeList);

		// 2. for(edgeList)
		int count = 0;
		for (Edge e : edgeList) {
			// 2-1. if cnt == N-1 -> break;
			if (count == N - 1) {
				break;
			}

			// 2-2. if cycle -> continue
			if (!union(e.from, e.to)) {
				continue;
			}

			// 2-3. select edge
			total += e.weight;
			count++;
		}

		return total;
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

	private static int find(int x) {
		if (parents[x] == x) {
			return x;
		}
		return parents[x] = find(parents[x]);
	}

}
