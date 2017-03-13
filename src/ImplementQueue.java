import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/13/17.
 */
public class ImplementQueue {

    class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        ImplementQueue stack= new ImplementQueue();
        FastScanner in = newInput();
        int n=Integer.parseInt(in.nextLine());
        for (int i = 0; i < n; i++) {
            String[] s=in.nextLine().split(" ");
            if(s[0].charAt(0)=='+'){
                stack.push(Integer.parseInt(s[1]));
            }else{
                stack.pop();
            }
        }
        stack.out.flush();
        stack.out.close();
    }
    ListNode tail;
    ListNode root;
    PrintWriter out;
    public ImplementQueue() {
        try {
            out=newOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void push(Integer i){
        if(root==null)root=tail=new ListNode(i);
        else {
            ListNode temp=tail;
            tail.next=new ListNode(i);
            tail=tail.next;
            tail.prev=temp;
        }
    }
    public void pop(){
        out.println(root.val);
        root=root.next;
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
