import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static boolean result;
	static Room[] rooms;
	static int[] maxMoney;

	static class Room {
		char type;
		int money;
		ArrayList<Integer> others;

		Room(char type, int money, ArrayList<Integer> others) {
			this.type = type;
			this.money = money;
			this.others = others;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		while (true) {

			N = Integer.parseInt(br.readLine());
			// N 입력으로 0이 들어오면 입력 데이터 끝
			if (N == 0) {
				break;
			}

			rooms = new Room[N+1];
			maxMoney = new int[N+1];
			for(int i=0; i<=N; i++) {
				maxMoney[i] = -1;	// init
			}
			result = false;

			// read maze input
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char type = st.nextToken().charAt(0);
				int money = Integer.parseInt(st.nextToken());
				ArrayList<Integer> others = new ArrayList<>();
				while (true) {
					int cur = Integer.parseInt(st.nextToken());
					if (cur == 0)
						break;
					others.add(cur);
				}
				rooms[i] = new Room(type, money, others);
			}

			// 1번 방이 트롤인 경우, 소지금 0이므로 바로 종료
			if (rooms[1].type == 'T') {
				sb.append("No").append('\n');
				continue;
			}

			// solve
			dfs(1, 0);

			// print current test case output
			sb.append(result == true ? "Yes" : "No").append('\n');

		}

		System.out.println(sb);
	}

	private static void dfs(int rIdx, int curMoney) {
		
		// 가지치기
		if(curMoney<=maxMoney[rIdx]) {
			return;
		}
		maxMoney[rIdx] = curMoney;

		// 돈 없어서 못 가는 경우
		if (rooms[rIdx].type == 'T') {
			if (curMoney < rooms[rIdx].money) {
				return;
			}
			// 트롤방을 만났지만 돈이 충분하면 통과 가능
			curMoney -= rooms[rIdx].money;
		}
		
		// 종료 조건
				// 마지막 N번방이 트롤이면, 이때도 돈은 내야되므로 돈을 낼 수 있는 로직을 통과한 뒤에 검사
				if (rIdx == N) {
					result = true;
					return;
				}

		// 레프리 만나는 경우
		if (rooms[rIdx].type == 'L') {
			// 일정량보다 적으면 돈 채워줌
			if (curMoney < rooms[rIdx].money) {
				curMoney = rooms[rIdx].money;
			}
		}

		// 재귀
		for (int nextIdx : rooms[rIdx].others) {
			dfs(nextIdx, curMoney);
		}
	}
}
