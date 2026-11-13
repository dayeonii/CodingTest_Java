import java.util.*;

class Solution {
    
    static String[] answer;
    
    public String[] solution(String[][] tickets) {
        found = false;
        
        int len = tickets.length;
		boolean[] used = new boolean[len];

		List<String> result = new ArrayList<>();
		
		Arrays.sort(tickets, (a,b)-> {
			int cmp = a[0].compareTo(b[0]);
			
			// 출발지가 같으면 도착지 기준으로 사전 정렬
			if(cmp==0) {
				return a[1].compareTo(b[1]);
			}
			
			return cmp;
		});

		result.add("ICN");
		dfs("ICN", tickets, used, result, 0);

		return answer;
    }
    
    static boolean found;

	private static void dfs(String curLoc, String[][] tickets, boolean[] used, List<String> result, int count) {

		int len = tickets.length;
		if (count == len) {
			answer = result.toArray(new String[0]);
			found = true;
			return;
		}

		for(int i=0; i<len; i++) {

			// 이미 사용한 항공권이면 패스
			if (used[i]) {
				continue;
			}

			// 현재 와 다음 출발지가 다르면 패스
			if (!curLoc.equals(tickets[i][0])) {
				continue;
			}

			// 재귀
			used[i] = true;
			result.add(tickets[i][1]);
			dfs(tickets[i][1], tickets, used, result, count + 1);
            if(found) return;
			used[i] = false;
			result.remove(result.size() - 1);

		}

	}
}