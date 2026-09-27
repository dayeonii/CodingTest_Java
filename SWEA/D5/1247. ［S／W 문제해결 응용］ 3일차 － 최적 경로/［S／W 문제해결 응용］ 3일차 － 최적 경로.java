import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int result;
	static List<Point> customers;
	static boolean[] visited;

	static class Point {
		int x, y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static Point company, home;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			customers = new ArrayList<>();
			visited = new boolean[N];

			StringTokenizer st = new StringTokenizer(br.readLine());

			// company
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			company = new Point(x, y);

			// home
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			home = new Point(x, y);

			// customers
			for (int i = 0; i < N; i++) {
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());
				customers.add(new Point(x, y));
			}

			// solve
			result = Integer.MAX_VALUE;
			dfs(0, 0, company);

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void dfs(int count, int length, Point current) {

		if (length > result) {
			return;
		}
		
		if (count == N) {
			length += getLength(current.x, current.y, home.x, home.y);	// last customer -> home
			result = Math.min(result, length);
			return;
		}

		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				Point next = customers.get(i);
				int len = getLength(current.x, current.y, next.x, next.y);
				dfs(count+1, length+len, next);
				visited[i] = false;
			}
		}

	}

	private static int getLength(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

}
