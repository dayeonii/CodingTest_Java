import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int minLen;
	static boolean isSelected[];
	static Point customers[];
	static Point start, end;

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			StringTokenizer st = new StringTokenizer(br.readLine());
			
			start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			end = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			
			customers = new Point[N];
			for (int i = 0; i < N; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				customers[i] = new Point(x, y);
			}

			minLen = Integer.MAX_VALUE;
			isSelected = new boolean[N];
			perm(0,0,start);
			
			sb.append('#').append(tc).append(' ').append(minLen).append('\n');
		}
		System.out.print(sb);
	}

	private static void perm(int count, int curLen, Point curLoc) {
		// 가지치기
		if(curLen>=minLen) {
			return;
		}
		
		// 종료조건
		if(count==N) {
			curLen += getLength(curLoc.x, curLoc.y, end.x, end.y);
			minLen = Math.min(curLen, minLen);
			return;
		}
		
		// 순열 생성
		for(int i=0; i<N; i++) {
			if(isSelected[i]==false) {
				isSelected[i] = true;
				int len = getLength(curLoc.x, curLoc.y, customers[i].x, customers[i].y);
				perm(count+1, curLen+len, new Point(customers[i].x, customers[i].y));
				isSelected[i] = false;
			}
		}
	}

	private static int getLength(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2) + Math.abs(y1-y2);
	}

}
