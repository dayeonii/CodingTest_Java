import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

/*
 * [문제]
 * - 특정 값을 찾기
 * 		- 이 값에 대해서 2진수, 3진수로 기억하고 있다
 * 		- 각 진수별로 '1자리씩만' 틀리게 되어있다
 * - 올바른 값을 10진수로 출력하자
 * 
 * [풀이]
 * - 제한 사항
 * 		- 2진수 자릿수 : 3 ~ 40 -> 2^40 = (2^10)^4 = (10^3)^4 = 10^12 까지
 * 		- 3진수 자릿수 : 3 ~ 40 -> 3^40 = (3^10)^4 = (6*10^)^4 = 1296 * 10^16 = 1.3 * 10^19
 * 		- 만약 값을 10진수로 변환해서 담을 거라면 long 사용 권장
 * - 각 진수 자릿수를 하나씩 바꿔보면서
 * 		- 2진수바꾼거 == 3진수 바꾼거 찾기?
 * 		- 2진수는 최대 40번 바꿔볼 수 있고
 * 		- 3진수는 최대 80번 바꿔볼 수 있음
 * 		- 이 둘을 다 비교해보면 최대 3200번
 * 
 * [입력]
 * T
 * 2진수
 * 3진수
 * 
 * [출력]
 * #tc 올바른값
 * */

public class Solution {
	static String binary;
	static String ternary;
	static long result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			binary = br.readLine();
			ternary = br.readLine();

			result = 0;
			solve();

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void solve() {

		// 2진수의 각 자릿수 바꿔보기
		HashSet<Long> binaries = new HashSet<>();
		int bLen = binary.length();
		for (int i = 0; i < bLen; i++) {

			// String 은 불변객체니까 char[] 으로 바꿔서 각 자릿수를 바꾸기
			char[] copyB = binary.toCharArray();
			copyB[i] = (copyB[i] == '0') ? '1' : '0';
			long dec = convertDec(new String(copyB), 2);
			binaries.add(dec);
		}
		
		
		// 3진수의 각 자릿수 바꿔서 바로 찾아보기
		// 바꿔볼 수 있는 경우가 2가지니까 이중 반복문으로 사용
		int tLen = ternary.length();
		for(int i=0; i<tLen; i++) {
			for(char num='0'; num<='2'; num++) {
				
				if(ternary.charAt(i)==num)
					continue;
				
				char[] copyT = ternary.toCharArray();
				copyT[i] = num;
				
				long dec = convertDec(new String(copyT), 3);
				if(binaries.contains(dec)) {
					result = dec;
					return;
				}
				
			}	
		}

	}

	private static long convertDec(String from, int type) {
		int mulVal = (type == 2) ? 2 : 3;

		long dec = 0;
		int x = 1;

		for (int i = from.length() - 1; i >= 0; i--) {
			int curNum = from.charAt(i) - '0';
			dec += curNum * x;
			x *= mulVal;
		}
		return dec;
	}
}
