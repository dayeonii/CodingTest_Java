import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int N, result;
	static int synergies[][];
	static int groupA[], groupB[];
	static boolean selectedA[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			N = Integer.parseInt(br.readLine());
			synergies = new int[N][N];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					synergies[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			groupA = new int[N/2];
			groupB = new int[N/2];
			selectedA = new boolean[N];
						
			result = Integer.MAX_VALUE;
			comb(0,0);

			sb.append('#').append(tc).append(' ').append(result).append('\n');
		}
		System.out.print(sb);
	}

	private static void comb(int count, int start) {
		
		if(count==N/2) {
			// 그룹 생성
			makeGroup();
			// 시너지 구하기
			result = Math.min(getSynergy(), result);
			return;
		}
		
		for(int i=start; i<N; i++) {
			if(selectedA[i]) {
				continue;
			}
			selectedA[i] = true;
			comb(count+1, i+1);
			selectedA[i] = false;
		}
		
	}
	
	private static void makeGroup() {
		int idxA = 0;
		int idxB = 0;
		
		for(int i=0; i<N; i++) {
			if(selectedA[i]) {
				groupA[idxA++] = i;
			} else {
				groupB[idxB++] = i;
			}
		}
	}

	private static int getSynergy() {
		int sumA = 0;
		for(int i=0; i<groupA.length; i++) {
			for(int j=i+1; j<groupA.length; j++) {
				int ingre1 = groupA[i];
				int ingre2 = groupA[j];
				sumA += (synergies[ingre1][ingre2] + synergies[ingre2][ingre1]);
			}
		}
		int sumB = 0;
		for(int i=0; i<groupB.length; i++) {
			for(int j=i+1; j<groupB.length; j++) {
				int ingre1 = groupB[i];
				int ingre2 = groupB[j];
				sumB += (synergies[ingre1][ingre2] + synergies[ingre2][ingre1]);
			}
		}
		return Math.abs(sumA-sumB);
	}

}
