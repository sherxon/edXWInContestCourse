import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/7/17.
 */
public class CreateaTeam {
    public static void main(String[] args) throws IOException {

        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int[][] a= new int[3][3];
            for (int i = 0; i < a.length; i++)
                for (int j = 0; j < a[i].length; j++)
                    a[i][j]=in.nextInt();
            long sum=0;
            long max=-1;
            for (int i = 0; i < a.length; i++) {
                for (int j = 0; j < a.length; j++) {
                    if(j==i)continue;
                    for (int k = 0; k < a.length; k++) {
                        if(k==i || k==j)continue;
                        sum=a[2][k] * a[2][k] + a[1][j] * a[1][j] + a[0][i] * a[0][i];
                        max=Math.max(sum,max);
                    }
                }
            }
            out.println(Math.sqrt(max));
        }
    }


    static class FastScanner {
        static BufferedReader br;
        static StringTokenizer st;

        FastScanner(File f) {
            try {
                br = new BufferedReader(new FileReader(f));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        public FastScanner(InputStream f) {
            br = new BufferedReader(new InputStreamReader(f));
        }
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
        double nextDoulbe() {
            return Double.parseDouble(next());
        }
    }

    static FastScanner newInput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new FastScanner(new File("input.txt"));
        } else {
            return new FastScanner(System.in);
        }
    }
    static PrintWriter newOutput() throws IOException {
        if (System.getProperty("JUDGE") != null) {
            return new PrintWriter("output.txt");
        } else {
            return new PrintWriter(System.out);
        }
    }

}
