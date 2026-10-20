import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/* 
 * [문제]
 * - 사용자 2명의 이동 궤적이 주어졌을 때, 두 사용자의 충전량 합의 최대값 찾기
 * 
 * - 주의할 점 : 행열이 반대임
 * 
 * - 사용자의 이동
 * 		- 1초마다 행동
 * 		- 0(이동x) / 1(상) / 2(우) / 3(하) / 4(좌)
 * 		- 두 사용자가 동시에 같은 자리에 올 수 있음
 * 		- 시작점
 * 			- 사용자 A는 좌상단에서 시작
 * 			- 사용자 B는 우하단에서 시작
 * 
 * - BC 정보
 * 		- 좌표 X,Y
 * 		- 충전 범위 C
 * 		- 처리량 P : 1초에 충전할 수 있는 양
 * 
 * - 충전하는 양
 * 		- 사용자의 위치가 BC로부터 C거리 이하일 때
 * 		- 거리 : 유클리드 거리 ( distance = |사용자x-충전x| + |사용자y=충전y| )
 * 		- 한 위치에서 2개 이상의 BC에 접속 가능할 때 -> 하나를 선택해서 접속
 * 		- 한 BC에 두 명이 접속할 때 -> 충전양 1/2로 분배
 * 		  (만약 BC1의 처리량이 100이고, 사용자 A/B가 같이 접속했다면 -> 각각 1초마다 50씩 충전)
 * 
 * [풀이]
 * - 시뮬레이션인가?
 * 		- 사용자의 이동 경로, 즉 매 초마다 위치하는 곳은 정해져있음
 * 		- 그렇다면, 매 초마다 위치한 곳에서 사용자가 할 수 있는 것은?
 * 			- 범위 안에 들어가지 못함 -> 아무것도 못함
 * 			- 한 개의 범위 안에 들어가있음 -> 그 bc에서 충전하면 됨
 * 			- 두 개 이상의 범위 안에 들어가있음
 * 				- 혼자 들어가있음 -> 더 큰 값으로 충전
 * 				- 같이 들어가있음
 * 					- 상대방도 한 개의 범위라면 -> 상대방과 다른 곳에서 충전
 * 					- 상대방도 두 개의 범위라면 -> 각자 더 최대화 할 수 있는 곳에서 충전... 이 기준이 뭘까?
 * 							- 같은 BC를 1/2 한 값 과 다른 BC에서 온전히 충전하는 값을 비교할 수 있을까?
 * - 답은 '두 사람의' 충전량 총합 이기 때문에
 * 		- 만약 두 사람이 같은 BC에서 충전한다 = 따로 계산해줄 필요 없이 해당 BC의 충전량 자체를 더해주면 됨
 * 
 * 
 * [입력]
 * T
 * 이동시간 M BC개수 A
 * 사용자 A의 이동 정보 (M개의 숫자)
 * 사용자 B의 이동 정보 (M개의 숫자)
 * BC의 정보 (A개의 줄, X Y 충전범위C 처리량P)
 * 
 * [출력]
 * #tc 충전량합의최댓값
 * */

public class Solution {

	final static int SIZE = 10; // 맵의 크기
	static int M, A, result;
	// 방향 반대임을 유의
	static int[][] delta = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } }; // 제자리상우하좌

	static Point curA, curB;
	static int[] moveA, moveB;

	static Charger[] bcList;

	static class Point {
		int x;
		int y;

		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Charger {
		Point loc;
		int c;
		int p;

		Charger(Point loc, int c, int p) {
			this.loc = loc;
			this.c = c;
			this.p = p;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			result = 0;

			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());

			// 사용자의 이동 정보
			moveA = new int[M + 1];
			moveB = new int[M + 1];

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveA[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= M; i++) {
				moveB[i] = Integer.parseInt(st.nextToken());
			}

			// 각 사용자 시작점 초기화
			curA = new Point(1, 1);
			curB = new Point(SIZE, SIZE);

			// BC의 정보
			bcList = new Charger[A];
			for (int i = 0; i < A; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int p = Integer.parseInt(st.nextToken());
				bcList[i] = new Charger(new Point(x, y), c, p);
			}

			// 이동하기
			// time==0 일 때는 시작점에서 가만히 있는 것으로 처리 - 이때도 충전할 수 있는지 검사해야 하기 때문
			for (int time = 0; time <= M; time++) {
				// 사용자의 이동 처리
				if (time >= 1) {
					move(time);
				}

				// 각 사용자의 현재 자리에서 충전할 수 있는 BC 찾기
				findBC();
			}

			// 결과 출력
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void findBC() {

		// 각 사용자가 충전할 수 있는 BC를 기록
		ArrayList<Integer> canA = new ArrayList<>();
		ArrayList<Integer> canB = new ArrayList<>();

		for (int b = 0; b < A; b++) {
			Charger curBC = bcList[b];

			int disA = Math.abs(curA.x - curBC.loc.x) + Math.abs(curA.y - curBC.loc.y);
			int disB = Math.abs(curB.x - curBC.loc.x) + Math.abs(curB.y - curBC.loc.y);

			if (disA <= curBC.c) {
				canA.add(b);
			}

			if (disB <= curBC.c) {
				canB.add(b);
			}
		}

		// 둘 다 충전할 수 없는 상태라면 바로 다음 무브
		if (canA.isEmpty() && canB.isEmpty()) {
			return;
		}

		// 둘 중 한 쪽만 충전할 수 있는 경우
		if (canA.isEmpty()) {
			int maxB = 0;
			for (int b : canB) {
				maxB = Math.max(maxB, bcList[b].p);
			}
			result += maxB;
			return;
		}
		if (canB.isEmpty()) {
			int maxA = 0;
			for (int a : canA) {
				maxA = Math.max(maxA, bcList[a].p);
			}
			result += maxA;
			return;
		}

		// 사용자가 각각 1개씩 BC를 쓸 수 있다면
		if (canA.size() == 1 && canB.size() == 1) {
			// 그 BC가 동일하다면 - BC의 총량 자체를 더해주면 됨
			if (canA.get(0) == canB.get(0)) {
				result += bcList[canA.get(0)].p;
			}

			// BC가 동일하지 않다면 - 각각 총량을 더해주면 됨
			else {
				result += bcList[canA.get(0)].p;
				result += bcList[canB.get(0)].p;
			}

			return;
		}

		// 둘 다 1개 이상 쓸 수 있고, 최소 한 쪽은 2개 이상인 경우
		// A가 선택한 것과 B가 선택한 것의 조합을 구해서 계산 - max값으로
		// 선택한 게 다르다면 각각 더하고
		// 선택한 게 동일하면 총량 하나로 더하고
		int max = 0;
		for (int a : canA) {
			for (int b : canB) {

				if (a == b) {
					max = Math.max(max, bcList[a].p);
				} else {
					int sum = bcList[a].p + bcList[b].p;
					max = Math.max(max, sum);
				}
			}
		}

		// 최고 조합 찾은 것으로 result에 더해주기
		result += max;

	}

	private static void move(int t) {
		int dirA = moveA[t];
		int dirB = moveB[t];

		// 이동
		// 행 열 방향이 반대임을 기억
		curA.x += delta[dirA][0];
		curA.y += delta[dirA][1];

		curB.x += delta[dirB][0];
		curB.y += delta[dirB][1];
	}

}
