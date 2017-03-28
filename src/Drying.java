import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/27/17.
 */
public class Drying {

    static  int n=0;
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            n=in.nextInt();
            //int[] a= new int[n];
            PriorityQueue<Integer> q= new PriorityQueue<>(n, (a,b)->b.compareTo(a));
            for (int i = 0; i < n; i++) {
                q.add(in.nextInt());
            }
            int time=0;
            int k=in.nextInt();
            if(q.size()==1){
                out.print((int)Math.ceil(q.peek()*1.0/k));
            }else{
                /*Arrays.sort(a);
                int n=a.length-1;
                int max;
                while (a[n]-time>0){
                    a[n]-=(k-1);
                    time++;
                    Arrays.sort(a);
                }*/
                //heapify(a);
                int max;
                while (q.peek()-time>0){
                    q.add(q.poll()-(k-1));
                    /*a[0]-=(k-1);
                    makeHeap(0, a);*/
                    //System.out.println(Arrays.toString(a));
                    time++;
                }
                out.print(time);
            }

        }
    }
    private static void swap(int[] a, int i, int j) {
        int temp=a[i];
        a[i]=a[j];
        a[j]=temp;
    }

    private static void heapify(int[] a) {

        for (int i = (n-1)/2; i >=0 ; i--) {
             makeHeap(i, a);
        }
    }

    private static void makeHeap(int i, int[] a) {
        int left=2*i+1;
        int right=2*i+2;
        int max=i;
        if(left<n && a[left]>a[i])max=left;
        if(right<n && a[right]>a[max])max=right;
        if(max!=i){
            int temp=a[i];
            a[i]=a[max];
            a[max]=temp;
            makeHeap(max, a);
        }
    }

    private static int extractMax(int[] a) {
        int min=a[0];
        a[0]=a[n-1];
        n--;
        makeHeap(0, a);
        return min;
    }
    private static int getMax(int[] a) {
        return a[0];
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
