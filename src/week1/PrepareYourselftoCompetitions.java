package week1;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/7/17.
 */
public class PrepareYourselftoCompetitions {

    public static void main(String[] args) throws IOException {

        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int n=in.nextInt();
            int[] a=new int[n];
            int[] b=new int[n];
            for (int i = 0; i < n; i++) a[i]=in.nextInt();
            for (int i = 0; i < n; i++) b[i]=in.nextInt();
            boolean isFiSmall=true;
            boolean isSeSmall=true;
            int sum=0;
            int mindiff=Integer.MAX_VALUE;
            int mini=0;
            for (int i = 0; i < a.length; i++) {
                if(a[i]>=b[i])isFiSmall=false;
                if(a[i]<=b[i])isSeSmall=false;
                sum+=Math.max(a[i], b[i]);
                if(mindiff > Math.abs(a[i]-b[i])){
                    mindiff=Math.abs(a[i]-b[i]);
                    mini=i;
                }
            }
            if(isFiSmall || isSeSmall){
                sum-=Math.max(a[mini],b[mini]);
                sum+=Math.min(a[mini],b[mini]);
            }
            out.println(sum);
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
