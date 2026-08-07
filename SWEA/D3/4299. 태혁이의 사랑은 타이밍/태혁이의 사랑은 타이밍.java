import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	
	static final int START_DAY = 11;
	static final int START_HOUR = 11;
	static final int START_MINUTE = 11;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int hour = Integer.parseInt(st.nextToken());
			int minute = Integer.parseInt(st.nextToken());
			
			int start = convertToMinutes(START_DAY, START_HOUR, START_MINUTE);
			int end = convertToMinutes(day, hour, minute);
			
			int result = calculateTime(start, end);
			sb.append('#').append(testCase).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static int calculateTime(int start, int end) {
		// 시작날짜보다 과거면 -1
		if (start>end)
			return -1;
		
		return end-start;
	}

	private static int convertToMinutes(int day, int hour, int minute) {
		int totalMinutes = 0;
		
		// 날짜는 아직 오늘이 다 지나지 않았기 때문에 전날으로 계산
		totalMinutes += (day-1) * 1440;
		totalMinutes += hour * 60;
		totalMinutes += minute;
		
		return totalMinutes;
	}

}
