import java.util.Arrays;

/*
 * costs[][] : 간선리스트
 * costs[i][0] 섬과 costs[i][1] 섬을 잇는 다리의 가중치는 costs[i][2]
 * answer : 최소 비용
 * */

class Solution {

	static int[] parents;

	public int solution(int n, int[][] costs) {
		// 0. init
		int answer = 0;
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}

		// 1. 간선 가중치 정렬
		Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

		// 2. 작은 것 부터 선택
		int count = 0;

		for (int[] cost : costs) {
			// 3. 선택한 간선이 n-1개가 된다면 종료
			if (count == n - 1) {
				break;
			}

			int numA = cost[0];
			int numB = cost[1];
			int weight = cost[2];

			// 2-1. costs[i][0] 이랑 costs[i][1]이 같은 집합인가?
			if (!union(numA, numB)) {
				continue;
			}

			// 2-2. 사이클이 안 생긴다면 이것으로 선택
			answer += weight;
			count++;
		}

		return answer;
	}

	private boolean union(int numA, int numB) {
		int rootA = find(numA);
		int rootB = find(numB);

		if (rootA == rootB) {
			return false;
		}

		parents[rootA] = rootB;
		return true;
	}

	private int find(int numA) {
		if (numA == parents[numA]) {
			return numA;
		}
		return parents[numA] = find(parents[numA]);
	}
}