/*
 * [문제 정의]
 * - n개의 양의 정수
 * - 들어온 순서 그대로 덧셈/뺄셈해서 타겟 넘버 만들기
 * - 타겟 넘버를 만들 수 있는 방법의 수 구하기
 * 
 * [입력]
 * numbers
 * target
 * return
 * 
 * [출력]
 * 만들 수 있는 방법의 수
 * 
 * [풀이]
 * - 완전 탐색 필요
 * - n: 2~20개 이므로 각 숫자에 대해서 더하거나 / 빼거나 2가지
 * - worst case : 2^20 -> 가능
 * - dfs
 * */

public class Solution {

	public static int solution(int[] numbers, int target) {
		int answer = 0;
		answer = dfs(0, 0, target, numbers);
		return answer;
	}

	private static int dfs(int count, int currentSum, int target, int[] numbers) {
		// 종료 조건 : count == numbers.length
		// 여기서는 주어진 numbers[] 를 모두 사용해서 가능한 조합을 찾아야 하니까 -> 그 안에서 target bingo 체크
		int endLength = numbers.length;
		int answer = 0;

		if (count == numbers.length) {
			// 타겟 빙고 : currentSum == target -> answer++
			if (currentSum == target) {
				answer++;
			}
			return answer;
		}

		return dfs(count + 1, currentSum + numbers[count], target, numbers) + // 더하던가
				dfs(count + 1, currentSum - numbers[count], target, numbers); // 빼던가
	}

}
