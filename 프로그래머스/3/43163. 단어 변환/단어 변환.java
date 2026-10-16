class Solution {
    static int answer;
	static boolean[] visited;

	public int solution(String begin, String target, String[] words) {
		answer = Integer.MAX_VALUE;
		visited = new boolean[words.length];

		// 작성하신 DFS 로직 구현
		dfs(begin, target, words, 0);

		if(answer>words.length) {
			answer = 0;		// no answer
		}
		return answer;
	}

	private void dfs(String begin, String target, String[] words, int count) {
		// 가지치기
		if (answer < count) {
			return;
		}

		// 종료조건
		if (begin.equals(target)) {
			answer = Math.min(answer, count);
		}

		// 재귀
		for(int i=0; i<words.length; i++) { 
			if(!visited[i] & canChange(begin, words[i])) {
				visited[i] = true;
				dfs(words[i], target, words, count+1);
				visited[i] = false;
			}
		}
	}

	private boolean canChange(String begin, String word) {
		int diff = 0;
		
		for(int i=0; i<begin.length(); i++) {
			if(begin.charAt(i) != word.charAt(i)) {
				diff++;
				if(diff>1) {
					return false;
				}
			}
		}
		return true;
	}
}