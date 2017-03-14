import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/13/17.
 */
public class QueuewithMinimum {
    PrintWriter out;
    Queue<Long> queue= new LinkedList<>();
    LinkedList<Long> mins= new LinkedList<>();

    public QueuewithMinimum() {
        try {
            out=newOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        QueuewithMinimum minimum= new QueuewithMinimum();
        FastScanner in = newInput();
        int a=-983814828;
        int n=Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            String[] s=in.nextLine().split(" ");
            if(s[0].charAt(0)=='+'){
                minimum.push(Long.parseLong(s[1]));
            }else if(s[0].charAt(0)=='?'){
                minimum.min();
            }else{
                minimum.pop();
            }
        }
        minimum.out.flush();
        minimum.out.close();
    }

    private void pop() {
        long last=queue.poll();
       //out.println(last);
       if(mins.peek().compareTo(last)==0)mins.poll();
    }

    private void min() {
        out.println(mins.peek());
    }

    private void push(long i) {
        queue.add(i);
       if(mins.isEmpty() ||  mins.peekLast().compareTo(i)<=0)
           mins.add(i);
        else if(mins.peekLast().compareTo(i)>0){
           mins.removeLast();
           mins.add(i);
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
