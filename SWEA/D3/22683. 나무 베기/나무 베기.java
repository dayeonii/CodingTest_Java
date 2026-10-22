import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * [문제]
 * - RC카의 이동 : 앞, 왼쪽, 오른쪽
 * 		- 최소한의 '조작' 으로 목적지까지 도달하기
 * 		- 시작은 위를 바라보는 상태로 시작
 * 
 * - N*N 필드에 있는 나무 베기
 * 		- 아빠가 벨 수 있는 나무의 개수는 정해져있음 (K)
 * 		- 차윤이가 목적지까지 도달하기 위한 최소 조작 횟수 구하기
 * - 필드 정보
 * 		- G : 이동 가능한 땅 (빈 땅)
 * 		- T : 나무 (베어진 나무는 . 으로 표기)
 * 		- X : 현재 위치
 * 		- Y : 목적지
 * 
 * [풀이]
 * - 맵의 크기 최대 10*10
 * - 나무 벨 수 있는 횟수 K : 0~5
 * - 완전탐색으로 가능한가?
 * 		- 맵 자체를 탐색 -> 가능
 * 		- 나무를 베는 조합 -> 가능
 * - 그러면 나무를 베는 조합을 '이렇게' 했을 때의 -> 갈 수 있는 경로를 찾아야 할 것 같음
 * 		- 맵을 읽을 때, 트리의 좌표 정보들을 리스트에 저장해서 조합 만들기
 * 
 * [입력]
 * T
 * N K
 * N*N map (공백없이)
 * 
 * [출력]
 * #tc 최소조작횟수 (도달할 수 없으면 -1)
 * */

public class Solution {

	static int N, K, result;
	static int startR, startC, endR, endC;
	static char[][] map;
	static boolean[][][] visited;
	static ArrayList<Integer[]> trees;
	static ArrayList<Integer[]> selectedTrees;

	// 상(0), 우(1), 하(2), 좌(3) - 시계 방향 (상태 변화 용이)
	static int[][] delta = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

	static class Car {
		int r, c;
		int dir; // 현재 바라보고 있는 방향
		int cnt; // 조작 횟수

		Car(int r, int c, int dir, int cnt) {
			this.r = r;
			this.c = c;
			this.dir = dir;
			this.cnt = cnt;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new char[N][N];
			trees = new ArrayList<>();
			selectedTrees = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				String line = br.readLine();
				for (int j = 0; j < N; j++) {
					map[i][j] = line.charAt(j);

					if (map[i][j] == 'X') {
						startR = i;
						startC = j;
					}

					else if (map[i][j] == 'Y') {
						endR = i;
						endC = j;
					}

					else if (map[i][j] == 'T') {
						trees.add(new Integer[] { i, j });
					}
				}
			}

			result = Integer.MAX_VALUE;

			// 나무를 고르는 조합 - K번 벨 수 있다
			// K번을 다 베야 하는게 아니라 0~K번 벨 수 있음
			// 문제에서 나무의 개수에 대한 제약이 나타나있지 않으므로, 나무 개수 < 베는 횟수 가 되지 않도록 제한
			int maxK = Math.min(K, trees.size());
			for (int target = 0; target <= maxK; target++) {
				comb(0, 0, target);
			}

			sb.append('#').append(tc).append(' ').append(result==Integer.MAX_VALUE? -1:result).append('\n');
		}
		System.out.print(sb);
	}

	// count : 현재까지 나무 몇 개 선택했는지
	// start : 나무 선택 인덱스
	private static void comb(int count, int start, int targetK) {
		// 종료 조건 : 나무를 targetK개만큼 선택함
		if (count == targetK) {
			// 선택한 나무들을 베기 (G으로 변경)
			for (Integer[] tree : selectedTrees) {
				map[tree[0]][tree[1]] = 'G';
			}

			// 나무를 벤 상태로 경로 탐색 시작
			int localRes = find();
			if (localRes != -1) {
				result = Math.min(result, localRes);
			}

			// 맵 원상복귀
			for (Integer[] tree : selectedTrees) {
				map[tree[0]][tree[1]] = 'T';
			}

			return;
		}

		// 나무 조합 만들기
		for (int i = start; i < trees.size(); i++) {
			selectedTrees.add(trees.get(i));
			comb(count + 1, i + 1, targetK);
			selectedTrees.remove(selectedTrees.size() - 1); // 추가했던 맨 마지막 것을 지워야 함
		}
	}

	private static int find() {
		// 방문 배열 초기화
		visited = new boolean[N][N][4];
		
		// 큐 생성
		ArrayDeque<Car> queue = new ArrayDeque<>();

		// 시작점 처리 (초기 방향은 위쪽)
		queue.offer(new Car(startR, startC, 0, 0));
		visited[startR][startC][0] = true;

		while (!queue.isEmpty()) {
			Car current = queue.poll();

			// 목적지에 도착한 경우
			if (current.r == endR && current.c == endC) {
				return current.cnt;
			}

			// 좌회전
			int leftDir = (current.dir + 3) % 4;
			if (!visited[current.r][current.c][leftDir]) {
				visited[current.r][current.c][leftDir] = true;
				queue.offer(new Car(current.r, current.c, leftDir, current.cnt + 1));
			}

			// 우회전
			int rightDir = (current.dir + 1) % 4;
			if (!visited[current.r][current.c][rightDir]) {
				visited[current.r][current.c][rightDir] = true;
				queue.offer(new Car(current.r, current.c, rightDir, current.cnt + 1));
			}

			// 직진
			int nr = current.r + delta[current.dir][0];
			int nc = current.c + delta[current.dir][1];

			if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
				// 이동하려는 칸이 가능한 땅인 경우
				if (map[nr][nc] != 'T' && !visited[nr][nc][current.dir]) {
					visited[nr][nc][current.dir] = true;
					queue.offer(new Car(nr, nc, current.dir, current.cnt + 1));
				}
			}
		}
		return -1;
	}
}
