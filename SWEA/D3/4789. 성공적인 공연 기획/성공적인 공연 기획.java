import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * [문제]
 * - 기립박수 연쇄 작용을 위하여 중간중간 언제 심어놔야 할까?
 * 
 * - 사람들이 각자 가진 타이밍 : 지금 기립박수를 하고 있는 사람의 수
 * 		- 이 수 이상이 되면 본인도 박수를 치게 됨
 * 
 * - 모든 사람들이 박수를 치게 하기 위해
 * 		- 중간중간 비게 되는 부분을 없애는 고용인들을 넣기
 * 
 * [풀이]
 * - 관리하는 상태값
 * 		- 현재 박수를 치는 인원 : count
 * 		- 현재 필요한 인원 : 문자열의 idx로 사용
 * - 입력 문자열을 받으면서
 * 		- 현재 idx 에서 이 사람이 박수를 쳐야하는데, 아직 모자라면 ( idx > count )
 * 		- 고용인을 그 자리에 심으면 된다
 * - 단순하게 읽는 것 만으로는 안되네...
 * 		- ~~ 한 상황일 때
 * 		- 마지막으로 박수친 사람 (!=0) 과 현재 위치의 격차를 구해야 할 듯
 * 
 * [입력]
 * T
 * 0~9 사이의 문자열 (길이 1001 이하)
 * 
 * - 입력에 대한 설명
 * 		입력이 110011 이면
 * 		기립박수 아무도 안함 (0명) -> 1명이 만족함 박수를 침
 * 		기립박수 1명일때 치는사람 -> 1명
 * 		기립박수 2명 -> 0명이 만족함 (추가적으로 늘어난 인원이 없음)
 * 		기립박수 3명 -> 0명이 만족함 (상동)
 * 		기립박수 4명일때 치는사람 -> 1명있음, 이 사람이 박수치기 위해서는 현재까지 2명에다가 + 추가적인 고용인 2명 필요
 * 		기립박수 5명 -> 1명이 만족 
 * 
 * [출력]
 * #tc 고용해야될사람
 * */

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int result = 0;

			String line = br.readLine();

			int count = 0;

			// 시작하자마자 박수치는 사람들 처리
			int startCnt = line.charAt(0) - '0';
			count += startCnt;

			// 그 다음부터
			for (int i = 1; i < line.length(); i++) {

				// 이번 조건에 만족하면 박수를 칠 사람 수
				int curClap = line.charAt(i) - '0';

				// 만약 조건이 모자라다면
				if (curClap != 0 && count < i) {
					// 모자란만큼 심어주면 된다
					result += (i - count);
					count += (i - count);
				}

				count += curClap;
			}

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}
}
