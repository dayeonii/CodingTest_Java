import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			
			int N = Integer.parseInt(br.readLine());
			int [] check = new int[N];
			int result = 0;
			
			result = countQueenSolution(check, 0);
			
			sb.append('#').append(testCase).append(' ').append(result).append('\n');
		}
		System.out.print(sb);

	}

	private static int countQueenSolution(int[] check, int row) {
		int len = check.length;
		int count = 0;
		
		if (row==len) {
			return 1;
		}
		
		for(int col=0; col<len; col++) {
			if(isPossible(check, row, col)) {
				check[row] = col;
				count += countQueenSolution(check, row+1);
			}
		}
		return count;
	}

	private static boolean isPossible(int[] check, int row, int col) {
		for(int i=0; i<row;i++) {
			// 지금 놓으려는 위치가 같은 열인지
			if(col == check[i]) {
				return false;
			}
			// 지금 놓으려는 위치가 대각선에 겹치는지
			if (Math.abs(row-i) == Math.abs(col-check[i])) {
				return false;
			}
		}
		return true;
	}

}
