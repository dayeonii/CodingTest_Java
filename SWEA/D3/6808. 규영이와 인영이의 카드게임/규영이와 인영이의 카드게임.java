import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static boolean[] isSelected; // 18개의 전체 카드
	static int[] card1; // 규영이가 선택한 카드
	static int[] card2; // 인영이가 선택한 카드

	static boolean[] visited; // 인영 순열 만들 때 사용하는 방문 배열
	static int[] numbers; // 인영 순열을 만들어서 저장하는 배열

	static int winCount = 0;
	static int loseCount = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());

			isSelected = new boolean[19];
			card1 = new int[10];
			card2 = new int[10];

			// 규영 카드 셋팅
			for (int i = 1; i <= 9; i++) {
				int num = Integer.parseInt(st.nextToken());
				isSelected[num] = true;
				card1[i] = num;
			}

			// 인영 카드 셋팅
			int idx = 1;
			for (int j = 1; j <= 18; j++) {
				if (isSelected[j] == false) {
					card2[idx++] = j;
				}
			}

			// 인영 카드로 순열을 만들고, 게임 결과 비교하기
			winCount = 0;
			loseCount = 0;
			
			visited = new boolean[10];
			numbers = new int[10];
			perm(1);

			// 결과 출력
			sb.append('#').append(tc).append(' ').append(winCount).append(' ').append(loseCount).append('\n');
		}
		System.out.print(sb);
	}

	private static void perm(int count) {
		if (count == 10) {
			// 만들어진 배열을 가지고 규영이와 playGame
			playGame();
			return;
		}

		for (int i = 1; i <= 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				numbers[count] = card2[i];
				perm(count + 1);
				visited[i] = false;
			}
		}
	}

	private static void playGame() {

		int sum1 = 0; // 규영 점수
		int sum2 = 0; // 인영 점수

		for (int round = 1; round <= 9; round++) {
			if (card1[round] > numbers[round]) {
				// 규영 승
				sum1 += card1[round] + numbers[round];
			} else {
				// 인영 승
				sum2 += card1[round] + numbers[round];
			}
		}

		// 라운드를 모두 돌고나면 점수를 비교해서 승/패 가림
		if (sum1 > sum2) {
			winCount++;
		} else {
			loseCount++;
		}

	}
}