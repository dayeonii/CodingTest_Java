class Solution {
    public int solution(int[][] triangle) {
        
        int[][] dp = new int[triangle.length][];
		for(int i=0; i<triangle.length; i++) {
			dp[i] = new int[i+1];
		}

		dp[0][0] = triangle[0][0];

		for (int i = 1; i < triangle.length; i++) {
			// 왼쪽 가장자리
			dp[i][0] += dp[i - 1][0] + triangle[i][0];

			// 오른쪽 가장자리
			int idx = triangle[i].length - 1;
			dp[i][idx] += dp[i - 1][idx-1] + triangle[i][idx];
		}

		for (int r = 1; r < triangle.length; r++) {
			for (int c = 1; c < triangle[r].length-1; c++) {
				dp[r][c] = Math.max(dp[r - 1][c - 1], dp[r - 1][c]) + triangle[r][c];
			}
		}
		
		int max = -1;
		int dpIdx = dp.length-1;
		for(int j=0; j<dp[dpIdx].length; j++) {
			if(max<dp[dpIdx][j]) {
				max = dp[dpIdx][j];
			}
		}

		return max;
    }
}