import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static String S;
	static boolean result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			
			result = true;

			S = br.readLine();
			int left = 0; int right = S.length()-1;
			
			// 전체에 대한 회문 검사
			checkPalindrome(left, right);
			
			// 처음 (N-1)/2 자리에 대한 회문 검사
			int mid = S.length() / 2;
			checkPalindrome(left, mid-1);
			
			// 마지막 (N-1)/2 자리에 대한 회문 검사
			checkPalindrome(mid+1, right);
			

			sb.append('#').append(tc).append(' ').append(result ? "YES" : "NO").append('\n');
		}
		System.out.print(sb);
	}

	private static void checkPalindrome(int left, int right) {
		
		while(left<right) {
			if(S.charAt(left)!=S.charAt(right)) {
				result = false;
				break;
			}
			left++;
			right--;
		}
	}

}
