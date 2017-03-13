import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/8/17.
 */
public class GenerateTests {

    public static void main(String[] args) throws IOException {

        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int n=in.nextInt();
            int max=0;
            int maxi=0;
            for (int i =n/2; i <=n; i++) {
               int k=0;
                if(i>5000){
                    if(i%360==0) k=numOfDivisors(i);
                } else if(i>=60){
                    if(i%60==0) k=numOfDivisors(i);
                }else if(i>=12){
                    if(i%12==0)k=numOfDivisors(i);
                }else if(i>=6){
                    if(i%6==0)k=numOfDivisors(i);
                }else{
                    k=numOfDivisors(i);
                }

                if(k>max){
                    max=k;
                    maxi=i;
                }
            }
            out.println(n-maxi+1);
        }
    }

    private static int numOfDivisors(int n) {
        int count=1;
        Map<Integer, Integer> map= new HashMap<>();
        int last=2;
        while (n!=1){

            for (int i = last; i <=n; i++) {
                if(n%i==0){
                    if(map.containsKey(i))   map.put(i, map.get(i)+1);
                    else                     map.put(i, 1);
                    n/=i;
                    last=i;
                    break;
                }
            }
        }
        for (Integer integer : map.keySet()) {
            count*=(map.get(integer)+1);
        }
        return count;
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
