import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(new BufferedInputStream(System.in, 1 << 16));
        StringBuilder sb = new StringBuilder();

        int tc = nextInt(in);
        while (tc-- > 0) {
            int n = nextInt(in);
            int[] deg = new int[n + 1];

            // 간선을 읽으면서 양 끝 정점의 차수만 센다
            for (int i = 0; i < n - 1; i++) {
                deg[nextInt(in)]++;
                deg[nextInt(in)]++;
            }

            // 답 = 차수가 2를 넘는 만큼의 합
            long ans = 0;
            for (int v = 1; v <= n; v++) {
                if (deg[v] > 2) ans += deg[v] - 2;
            }
            sb.append(ans).append('\n');
        }
        System.out.print(sb);
    }

    // 빠른 정수 입력 (N 최대 30만이라 Scanner는 느림)
    private static int nextInt(DataInputStream in) throws IOException {
        int ret = 0, b = in.read();
        while (b < '0') b = in.read();
        while (b >= '0') {
            ret = ret * 10 + (b - '0');
            b = in.read();
        }
        return ret;
    }
}