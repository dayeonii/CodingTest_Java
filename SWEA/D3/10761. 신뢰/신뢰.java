import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N;
	static int bluePosition;
	static int orangePosition;
	static int time;

	static Queue<Command> commands;

	static class Command {
		char robot;
		int position;

		Command(char robot, int position) {
			this.robot = robot;
			this.position = position;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			// input commands
			commands = new ArrayDeque<>();
			for (int i = 0; i < N; i++) {
				char r = st.nextToken().charAt(0);
				int p = Integer.parseInt(st.nextToken());
				commands.offer(new Command(r, p));
			}

			// init state
			orangePosition = 1;
			bluePosition = 1;
			time = 0;

			// simulate
			solve();

			// output
			sb.append('#').append(tc).append(' ').append(time).append('\n');
		}
		System.out.print(sb);
	}

	private static void solve() {

		while (!commands.isEmpty()) {
			Command current = commands.peek();

			int orangeNextP = findNextPosition('O');
			int blueNextP = findNextPosition('B');

			// orange
			if (current.robot == 'O') {
				if (orangePosition == orangeNextP) {
					// click
					commands.poll();
				} else {
					// move one
					orangePosition = moveOne(orangePosition, orangeNextP);
				}
			} else {
				if (orangeNextP != -1) {
					// move one
					orangePosition = moveOne(orangePosition, orangeNextP);
				} else {
					// do nothing
				}
			}

			// blue
			if (current.robot == 'B') {
				if (bluePosition == blueNextP) {
					// click
					commands.poll();
				} else {
					// move one
					bluePosition = moveOne(bluePosition, blueNextP);
				}
			} else {
				if (blueNextP != -1) {
					// move one
					bluePosition = moveOne(bluePosition, blueNextP);
				} else {
					// do nothing
				}
			}

			time++;
		}

	}

	private static int moveOne(int currentP, int nextP) {
		// move left
		if (currentP < nextP) {
			currentP++;
		}
		// move right
		else if (currentP > nextP) {
			currentP--;
		}
		return currentP;
	}

	private static int findNextPosition(char robot) {
		for (Command c : commands) {
			if (c.robot == robot) {
				return c.position;
			}
		}
		return -1;
	}

}
