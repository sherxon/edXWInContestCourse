package week3;

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
      //  linearSort();
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
            boolean alleqaual=true;
            a[0]=a1;
            a[1]=a2;
            int a3;
            alleqaual=a1<=a2;
            for (int i = 2; i < n; i++) {
                a3=A*a1 + B*a2 + C;
                alleqaual=alleqaual && a3>=a2;
                a[i]=a3;
                a1=a2;
                a2=a3;

            }
            int k2th=k2-1;
            int k1th=k1-1;
            if(!alleqaual){
                //shuffle(a);
                k2th=k1th=select(a, 0, a.length - 1, k1 - 1);
                if(k1!=k2) {
                    k2th = select(a, k1th, a.length - 1, k2 - 1);
                    Arrays.sort(a, k1th, k2th + 1);
                }
            }

           // solveByLinearSort(a);

            for (int i = k1th; i <=k2th ; i++) {
                out.print(a[i] + " ");
            }
        }
    }

    private static void shuffle(int[] a) {
        ThreadLocalRandom r= ThreadLocalRandom.current();
        int temp;
        int ran;
        for (int i = 0; i < a.length; i++) {
            ran=r.nextInt(a.length-1);
            temp=a[i];
            a[i]=a[ran];
            a[ran]=temp;
        }
    }


    static int[] solveByLinearSort(int[] nums) {
        final int R = (1 << 8);
        final int bitmask = R - 1;
        int[] aux = new int[nums.length];
        for (int i = 0; i < 4; i++) {
            int[] count = new int[R + 1];
            for (int num : nums) {
                int c = (num >>> (i * 8)) & bitmask;
                count[c + 1]++;
            }
            for (int r = 0; r < R; r++) count[r + 1] += count[r];
            if (i == 3) {
                int shift1 = count[R] - count[R/2];
                int shift2 = count[R/2];
                for (int r = 0; r < R/2; r++)
                    count[r] += shift1;
                for (int r = R/2; r < R; r++)
                    count[r] -= shift2;
            }
            for (int num : nums) {
                int c = (num >>> (i * 8)) & bitmask;
                aux[count[c]++] = num;
            }
            System.arraycopy(aux, 0, nums, 0, nums.length);
        }
        return nums;
    }


    private static int kthLargest(int[] a, int k1, int from, int to ) {
        int storeIndex=from;
        int pivot=  pivot(a, from, to);
        swap(a, pivot, to);
        for (int i = from; i <to; i++) {
            if(a[i]<a[to]){
                swap(a, storeIndex, i);
                storeIndex++;
            }
        }
        swap(a, storeIndex, to);
        if(storeIndex==k1)return storeIndex;
        else if (k1 < storeIndex)
            return kthLargest(a, k1, from, storeIndex - 1);
        else
            return kthLargest(a, k1, storeIndex + 1, to);
    }

    private static int partition5(int[] a, int lo, int hi) {
        Arrays.sort(a, lo, hi + 1);
        return (hi + lo) / 2;
    }
    private static int pivot(int[] a, int left, int right) {
        if (right - left < 5) {
            return partition5(a, left, right);
        }

        // otherwise move the medians of five-element subgroups to the first n/5 positions
        for (int i = left; i <= right; i += 5) {
            // get the median of the i'th five-element subgroup
            int subRight = i + 4;
            if (subRight > right) {
                subRight = right;
            }

            int median5 = partition5(a, i, subRight);

            swap(a, median5, (int) (left + Math.floor((i - left) / 5d)));
        }

        // compute the median of the n/5 medians-of-five
        return select(a,
                (int) (left + Math.ceil((right - left) / 5d) - 1),
                (int) (left + (right - left) / 10d),
                left);
    }
    private static int partition(int[] a, int lo, int hi, int pivotIndex) {
        swap(a, lo, pivotIndex);
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {
            while (less(a[++i], v) && i != hi) { }
            while (less(v, a[--j]) && j != lo) { }
            if (j <= i) break;
            swap(a, i, j);
        }
        swap(a, j, lo);
        return j;
    }

    private static boolean less(int v, int i) {
        return v<i;
    }

    private static int select(int[] nums, int lo, int hi, int k) {
        while (lo < hi) {
            int pivotIndex = pivot(nums, lo, hi);
            int j = partition(nums, lo, hi, pivotIndex);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                return j;
            }
        }
        return lo;
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
