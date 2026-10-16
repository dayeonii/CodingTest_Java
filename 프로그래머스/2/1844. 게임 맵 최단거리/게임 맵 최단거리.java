import java.util.*;
import java.io.*;

class Solution {
    static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	static boolean[][] visited;
	static int result;
	static boolean arrived;

	public int solution(int[][] maps) {
		int answer = -1;
		arrived = false;
		visited = new boolean[maps.length][maps[0].length];

		// 이 부분에 로직을 작성하세요.
		bfs(maps);
		
		if(arrived) {
			answer = result;
		}
		return answer;
	}

	static class Point {
		int x, y;
		int cost;

		Point(int x, int y, int cost) {
			this.x = x;
			this.y = y;
			this.cost = cost;
		}
	}

	private void bfs(int[][] maps) {

		// 큐 생성
		ArrayDeque<Point> queue = new ArrayDeque<>();
		
		// 시작점 처리
		visited[0][0] = true;
		queue.offer(new Point(0,0,1));	// 시작점도 경로 1으로 친다
		
		while(!queue.isEmpty()) {
			// 큐에서 하나 꺼내기
			Point current = queue.poll();
			
			if(current.x==maps.length-1 && current.y ==maps[0].length-1) {
				// 도착함
				result = current.cost;
				arrived = true;
				break;
			}
			
			for(int dir=0; dir<4; dir++) {
				int nx = current.x + delta[dir][0];
				int ny = current.y + delta[dir][1];
				int cost = current.cost;

				if(canGo(nx, ny, maps) && !visited[nx][ny]) {
					queue.offer(new Point(nx, ny, cost+1));
					visited[nx][ny] = true;
				}
			}
			
		}

	}

	private boolean canGo(int x, int y, int[][] maps) {
		return x>=0 && x<maps.length && y>=0 && y<maps[0].length && maps[x][y]==1;
	}
}