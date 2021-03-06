package week3;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/27/17.
 */
public class AntiQuickSort {

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int n=in.nextInt();

            boolean x=n%2==0 && n>3;
            int[] a= new int[n];
            int ii=n-1;
            int jj=0;
            int count=1;
            for (int i = 0; i < a.length; i++) {
                if(x){
                    a[ii--]=count++;
                }else{
                    a[jj++]=count++;
                }
                x=!x;
            }
            for (int i = 0; i < a.length; i++) {
                if(i!=a.length-1)
                    out.print(a[i] + " ");
                else
                    out.print(a[i]);
            }
            //out.println(Arrays.toString(a));
        }
    }

    private static void rotate(int[] a, int k) {
        reverse(a, 0, k-1);
        reverse(a, k, a.length - 1);
        //if(a.length%2==0 && a.length>2)
      //  reverse(a, 0, a.length - 1);

        //System.out.println(Arrays.toString(a));


    }

    private static void reverse(int[] a, int lo, int hi) {
        int mid=lo+(hi-lo)/2;
        for (int i = lo; i <=mid; i++) {
            int temp=a[i];
            a[i]=a[hi];
            a[hi]=temp;
            hi--;
        }
    }

    private static class FastScanner {
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
