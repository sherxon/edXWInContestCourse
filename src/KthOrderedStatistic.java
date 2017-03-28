import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by sherxon on 3/27/17.
 */
public class KthOrderedStatistic {
    static int  n=0;
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int m=in.nextInt();
            int k1=in.nextInt();
            int k2=in.nextInt();
            int A=in.nextInt();
            int B=in.nextInt();
            int C=in.nextInt();
            int a1=in.nextInt();
            int a2=in.nextInt();
            n=m;
            int[] a= new int[n];
            a[0]=a1;
            a[1]=a2;
            int a3;
            for (int i = 2; i < n; i++) {
                a3=A*a1 + B*a2 + C;
                a[i]=a3;
                a1=a2;
                a2=a3;
            }
            int k2th, k1th;
            k2th=k1th=kthLargest(a, k1 - 1, 0, a.length - 1);
            if(k1!=k2) k2th=kthLargest(a, k2 - 1, k1th, a.length - 1);
            Arrays.sort(a, k1th, k2th+1);
            for (int i = k1th; i <=k2th ; i++) {
                   if(i!=k2th)
                       out.print(a[i] + " ");
                   else
                       out.print(a[i]);
            }
        }
    }

    private static int partition( int[] inputArray, int leftPtr,int rightPtr)
    {

        int pivot = inputArray[ThreadLocalRandom.current().nextInt(leftPtr, rightPtr+1)];
        while (leftPtr<rightPtr){
            while (inputArray[leftPtr]<pivot)
                leftPtr++;
            while (inputArray[rightPtr]>pivot)
                rightPtr--;
            if (leftPtr<rightPtr)
            {
                swap(inputArray, leftPtr, rightPtr);

                //Cases which have repeated numbers...
                if (inputArray[leftPtr] == inputArray[rightPtr])
                    leftPtr++;
            }
        }
        return leftPtr;//return any one
    }
    private static int kthLargest(int[] a, int k1, int from, int to ) {
        int storeIndex=partition(a, from, to);
        if(storeIndex==k1)return storeIndex;
        else if (k1 < storeIndex)
            return kthLargest(a, k1, from, storeIndex - 1);
        else
            return kthLargest(a, k1, storeIndex + 1, to);
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
        int min=i;
        if(left<n && a[left]<a[i])min=left;
        if(right<n && a[right]<a[min])min=right;
        if(min!=i){
            int temp=a[i];
            a[i]=a[min];
            a[min]=temp;
            makeHeap(min, a);
        }
    }

    private static int extractMin(int[] a) {
        int min=a[0];
        a[0]=a[n-1];
        n--;
        makeHeap(0, a);
        return min;
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
