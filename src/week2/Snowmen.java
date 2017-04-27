package week2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/16/17.
 */
public class Snowmen {

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int n=in.nextInt();
            ListNode[] a= new ListNode[n+1];
            long[] b= new long[n+1];
            int index=in.nextInt();
            int val=in.nextInt();
            a[0]=new ListNode(0);
            a[index+1]=new ListNode(a[0], val);
            b[1]=val;
            long sum=val;
            for (int i = 2; i < n+1; i++) {
                int ii=in.nextInt();
                 val=in.nextInt();
                if(val==0) {
                    a[i]=a[ii].prev;
                    b[i]=b[ii]-a[ii].val;
                } else {
                    a[i]=new ListNode(a[ii], val);
                    b[i]=b[ii]+val;
                }
                sum+=b[i];
            }
            out.println(sum);
        }
    }
    private static class ListNode{
        ListNode prev;
        int val;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(ListNode prev, int val) {
            this.prev = prev;
            this.val = val;
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
        String nextLine(){
            String st="";
            try {
                st=br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return  st;
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
