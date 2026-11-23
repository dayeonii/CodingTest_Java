import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, F;
	static int result;
	static char[][] map;

	static int[][] ver;
	static int[][] hor;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		F = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = line.charAt(j);
			}
		}

		ver = new int[N][M - 1];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M - 1; j++) {
				if (map[i][j] == map[i][j + 1]) {
					if (map[i][j] == '#')
						ver[i][j] = 0;
					else
						ver[i][j] = 2;
				} else {
					ver[i][j] = 1;
				}
			}
		}

		hor = new int[N - 1][M];
		for (int i = 0; i < N - 1; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == map[i + 1][j]) {
					if (map[i][j] == '#')
						hor[i][j] = 0;
					else
						hor[i][j] = 2;
				} else {
					hor[i][j] = 1;
				}
			}
		}

		for (int j = 0; j < M - 1; j++) {
			int first = -1, last = -1;
			int total = 0;

			for (int i = 0; i < N; i++) {
				if (ver[i][j] == 1)
					total++;
				if (ver[i][j] == 0) {
					if (first == -1)
						first = i;
					last = i;
				}
			}

			if (first == -1) {
				result += Math.min(total, F);
				continue;
			}
			
			int a = 0;
			for(int i=0; i<first; i++) {
				if(ver[i][j]==1) a++;
			}
			int b=0;
			for(int i=last+1; i<N; i++) {
				if(ver[i][j]==1) b++;
			}
			
			result += Math.min(a, F) + (total-a-b) + Math.min(b, F);
		}

		for (int i = 0; i < N - 1; i++) {
			int first = -1, last = -1;
			int total = 0;

			for (int j = 0; j < M; j++) {
				if (hor[i][j] == 1)
					total++;
				if (hor[i][j] == 0) {
					if (first == -1)
						first = j;
					last = j;
				}
			}

			if (first == -1) {
				result += Math.min(total, F);
				continue;
			}
			
			int a = 0;
			for(int j=0; j<first; j++) {
				if(hor[i][j]==1) a++;
			}
			int b=0;
			for(int j=last+1; j<M; j++) {
				if(hor[i][j]==1) b++;
			}
			
			result += Math.min(a, F) + (total-a-b) + Math.min(b, F);
		}

		sb.append(result);

		System.out.print(sb);
	}
}
