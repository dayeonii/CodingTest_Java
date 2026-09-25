import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * [문제 정의]
 * - N개의 주차 공간 : 1~100
 * - 모든 주차 공간이 비어있는 상태에서 시작함
 * 		- 차량이 도착했을 때
 * 			- 비어있는 공간이 있다 -> 가능한 공간 중 번호가 가장 작은 곳에 주차
 * 			- 비어있는 공간이 없다 -> 공간이 생길 때 까지 대기
 * 			- 대기중인 차량들은 새치기 하지 않는다
 * - 주차 요금
 * 		- 차량의 무게 * 해당 주차 공간의 단위 무게당 금액
 * 		- 이용시간은 고려하지 않음
 * - 오늘 주차장을 이용하는 M대의 챠량 : 1~2000
 * 		- 들어오고 나가는 순서를 알고 있음
 * 		- 오늘의 총 수입을 계산
 * - 1번부터 M번까지 모든 차량은 한번씩 입차/출차를 한다
 * - 대기중인 차량이 그냥 돌아가는 경우는 없다
 * 
 * [입력]
 * T
 * N M
 * N개의 줄에 주차 공간의 단위 무게당 금액
 * M개의 줄에 차량의 무게
 * 2M개의 줄에 차량 출입순서 (양수: 입차 / 음수: 출차)
 * 
 * [출력]
 * #tc 총수입
 * 
 * [풀이]
 * - 모든 경우의 수에서 선택하는 문제인가? no
 * - 주어진 차량 입출차 순서를 그대로 시뮬레이션
 * 
 * - 입차
 * 		- 빈 공간이 있으면 : 가장 작은 번호에 주차
 * 		- 빈 공간이 없으면 : 대기 큐 삽입
 * - 출차	
 * 		- 주차했던 공간을 비움
 * 		- 대기 큐에 대기중인 차량이 있으면 주차시킴
 * - 요금 계산
 * 		- 이용시간을 고려하지 않으므로, 주차하자마자 계산
 * 		- 총수입 += 차량 무게 * 단위 무게 당 요금
 * - 상태 관리
 * 		- 대기 차량 : Queue<Intger> waitCars
 * 		- 주차 공간
 * 			- 주차할 때 가장 작은 번호부터 채우기 위해서 : 빈 공간들을 우선순위 큐로 관리
 * 		- 해당 차량이 주차한 위치 : int parkedAt[M+1] (1-based)
 * 			- parkedAt[1] = 3 : 1번 차량은 3번 공간에 주차
 * 		- 주차 공간별 요금 : int cost[N+1]
 * 		- 차량별 무게 : int weight[M+1]
 * 		- 총수입 : int result
 * 		- 입출차 순서 : 읽는대로 바로 처리해주면 됨
 * */

public class Solution {

	static int N, M;
	static int result;
	static int parkedAt[], cost[], weight[];
	static PriorityQueue<Integer> emptySpace;
	static ArrayDeque<Integer> waitCars;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// input cost
			cost = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				cost[i] = Integer.parseInt(br.readLine());
			}

			// input car weight
			weight = new int[M + 1];
			for (int i = 1; i <= M; i++) {
				weight[i] = Integer.parseInt(br.readLine());
			}

			// init
			result = 0;
			parkedAt = new int[M + 1];
			waitCars = new ArrayDeque<>();

			emptySpace = new PriorityQueue<>();
			for (int i = 1; i <= N; i++) {
				emptySpace.add(i);
			}

			// input in-out order
			for (int i = 0; i < 2 * M; i++) {
				int car = Integer.parseInt(br.readLine());

				if (car > 0) {
					if (!emptySpace.isEmpty()) {
						int space = emptySpace.poll();
						parkedAt[car] = space;

						result += cost[space] * weight[car];
					} else {
						waitCars.add(car);
					}
				} else {
					int carNum = Math.abs(car);
					int space = parkedAt[carNum];
					parkedAt[carNum] = 0;
					
					if(!waitCars.isEmpty()) {
						int waitCar = waitCars.poll();
						parkedAt[waitCar] = space;
						result += cost[space] * weight[waitCar];
					} else {
						emptySpace.add(space);
					}
				}
			}

			// output
			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

}
