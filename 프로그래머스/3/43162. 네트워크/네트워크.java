import java.util.*;
import java.io.*;

class Solution {
    static boolean[] visited;

	public int solution(int n, int[][] computers) {
		int answer = 0;
		visited = new boolean[n];

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				bfs(computers, i);
				answer++;
			}
		}

		return answer;
	}

	private void bfs(int[][] computers, int startIdx) {
		// generate Queue
		ArrayDeque<Integer> queue = new ArrayDeque<>();

		// add start point
		queue.offer(startIdx);
		visited[startIdx] = true;

		while (!queue.isEmpty()) {
			int current = queue.poll();

			for (int j = 0; j < computers.length; j++) {
				if (current == j)
					continue;

				if (computers[current][j] == 1 && !visited[j]) {
					visited[j] = true;
					queue.offer(j);
				}
			}
		}

	}
}