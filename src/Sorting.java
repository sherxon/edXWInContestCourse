import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/25/17.
 */
public class Sorting {
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int n=in.nextInt();
            int[] a=new int[n];
            for (int i = 0; i < n; i++) {
                a[i]=in.nextInt();
            }
            mergeSort(a, out);
            for (int i = 0; i < a.length; i++) {
                out.print(a[i] + " ");
            }
        }
    }

    private static void mergeSort(int[] a, PrintWriter out) {
        int[] b= new int[a.length];
        mergeSort(a, 0, a.length-1, b, out);
    }

    private static void mergeSort(int[] a, int lo, int hi, int[] b, PrintWriter out) {
        if(lo>=hi)return;

        int mid=lo+(hi-lo)/2;
        mergeSort(a, lo, mid, b, out);
        mergeSort(a, mid+1, hi, b, out);
        merge(a, lo, mid, hi, b, out);

    }

    private static void merge(int[] a, int lo, int mid, int hi, int[] b, PrintWriter out) {
        for (int i = lo; i <=hi; i++) {
            b[i]=a[i];
        }
        int i=lo;
        int j=mid+1;
        for (int k = lo; k <=hi; k++) {
            if(i>mid)a[k]=b[j++];
            else if(j>hi)a[k]=b[i++];
            else if(b[i]<b[j])a[k]=b[i++];
            else a[k]=b[j++];
        }
        out.println(lo+1 + " " + (hi+1) + " " + a[lo] + " " + a[hi]);
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
