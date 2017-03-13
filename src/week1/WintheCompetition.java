package week1;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/8/17.
 */
public class WintheCompetition {

    public static void main(String[] args) throws IOException {

        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int n=in.nextInt();
            long[] a=new long[n];
            for (int i = 0; i < n; i++) {
                a[i]=in.nextLong();
            }
            Arrays.sort(a);
            long cost=0;
            int i=0;
            while (i<a.length &&  cost+a[i]<=300*60){
                cost+=a[i++];
            }
            out.println(i);
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
