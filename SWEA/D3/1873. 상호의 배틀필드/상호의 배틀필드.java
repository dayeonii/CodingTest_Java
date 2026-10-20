import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * [입력]
 * T
 * H W
 * H*W map info
 * N
 * length of N info
 * 
 * [출력]
 * #tc map status
 * 
 * [문제]
 * 
 * 문자	의미
 * .	평지(전차가 들어갈 수 있다.)
 * *	벽돌로 만들어진 벽 (파괴o)
 * #	강철로 만들어진 벽 (파괴x)
 * -	물(전차는 들어갈 수 없다.) (포탄 통과 가능)
 * ^	위쪽을 바라보는 전차(아래는 평지이다.)
 * v	아래쪽을 바라보는 전차(아래는 평지이다.)
 * <	왼쪽을 바라보는 전차(아래는 평지이다.)
 * >	오른쪽을 바라보는 전차(아래는 평지이다.)
 *
 * 문자	동작
 * U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
 * D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
 * L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
 * R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
 * S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
 * N개의 문자 명령을 수행하고 난 뒤 맵 상태 출력하기 -> 시뮬레이션
 * 
 * [풀이]
 * - move
 * 		- curCmd에 따라서 방향 전환 (필수)
 * 		- 만약 바로 앞/옆 칸이 평지(.) 이면 이동
 * 		- 이동하고 내가 있던 자리에는 평지로 만들어주기
 * - shoot
 * 		- 현재 전차 방향을 기준으로 벽돌벽/강철벽/맵밖 을 만날 때까지 직진
 * 		- 벽돌벽 만나면 - 벽돌벽 자리 평지로 만들고 스탑 
 * 		- 강철벽/맵밖 만나면 - 스탑(아무일도x)
 * - 전차의 현재 방향과 UDLS 명령어
 * 		- 전차의 현재 방향에 따라서 이동할 때 delta 방향을 바로 매칭해줄 수 있게
 * 		- "^v<>" -> 상하좌우 순서로 delta 배열 선언 {-1,0}, {1,0}, {0,-1}, {0,1}
 * 		- 명령어 입력과의 매칭도 "UDLS" 순서로 인덱스 써서 할 수 있게
 * */

public class Solution {

	static int H, W, N;
	static char[][] map;
	static String command;

	static int curX, curY;
	static int curDir;

	final static int[][] delta = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
	final static String DIR = "^v<>";
	final static String CMD = "UDLR";

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// read map size
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());

			// init map
			map = new char[H][W];
			curX = 0;
			curY = 0;
			curDir = 0;
			for (int i = 0; i < H; i++) {
				String line = br.readLine();
				for (int j = 0; j < W; j++) {
					map[i][j] = line.charAt(j);

					// find start point
					if (DIR.indexOf(map[i][j]) != -1) {
						curX = i;
						curY = j;
						curDir = DIR.indexOf(map[i][j]);
					}
				}
			}

			// read command & play
			N = Integer.parseInt(br.readLine());
			command = br.readLine();

			// simulation
			for (int i = 0; i < N; i++) {
				char curCmd = command.charAt(i);

				// 명령어가 shoot 일 때
				if (curCmd == 'S') {
					shoot();
				}

				// 명령어가 UDLR 이동일 때
				else {
					int changeDir = CMD.indexOf(curCmd);
					move(changeDir);
				}
			}

			// print output
			sb.append('#').append(tc).append(' ');
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append('\n');
			}
		}
		System.out.print(sb);
	}

	private static void move(int changeDir) {

		// 현재 위치에서 방향 바꾸기
		map[curX][curY] = DIR.charAt(changeDir);
		curDir = changeDir;

		// 만약 한 칸 앞이 평지라면 이동
		int nx = curX + delta[curDir][0];
		int ny = curY + delta[curDir][1];
		
		if (canGo(nx,ny) && map[nx][ny] == '.') {
			map[nx][ny] = DIR.charAt(changeDir);
			map[curX][curY] = '.';
			curX = nx;
			curY = ny;
		}

	}

	private static void shoot() {

		int nx = curX + delta[curDir][0];
		int ny = curY + delta[curDir][1];

		while (true) {

			// 맵 밖으로 나가면 멈춤
			if (!canGo(nx, ny)) {
				break;
			}

			// 강철벽을 만나면 멈춤
			if (map[nx][ny] == '#') {
				break;
			}

			// 벽돌벽을 만나면 깨고 멈춤
			if (map[nx][ny] == '*') {
				map[nx][ny] = '.';
				break;
			}

			nx += delta[curDir][0];
			ny += delta[curDir][1];
		}

	}

	private static boolean canGo(int nx, int ny) {
		return nx >= 0 && nx < H && ny >= 0 && ny < W;
	}

}
