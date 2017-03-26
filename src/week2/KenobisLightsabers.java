package week2;

import java.io.*;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/17/17.
 */
public class KenobisLightsabers {
   static ListNode head=null;
   static ListNode tail=null;
   static ListNode mid=null;

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int n=Integer.parseInt(in.nextLine());
            int count=0;
            for (int i = 0; i < n; i++) {
                String[] s=in.nextLine().split(" ");
                if(s[0].equals("add")){
                    count++;
                    int k=Integer.parseInt(s[1]);
                    if(head==null)
                        head=mid=tail=new ListNode(k);
                    else{
                        ListNode newNode=new ListNode(k);
                        tail.next=newNode;
                        newNode.prev=tail;
                        tail=tail.next;
                    }
                    if(count%2==0 && count!=2) mid=mid.next;
                }else if(s[0].equals("take")){
                    count--;
                    tail=tail.prev;
                    if(tail==null)head=null;
                    else tail.next=null;

                    if(count%2!=0) mid=mid.prev;
                }else{
                    if(head==null || head.next==null)continue;
                    ListNode newHead=mid.next;
                    mid.next=null; // break the link;
                    newHead.prev=null;
                    tail.next=head;
                    head.prev=tail;
                    head=newHead;
                    ListNode temp=mid;
                    if(count%2==0)mid=tail;
                    else mid=tail.prev;
                    tail=temp;
                }
            }
            out.println(count);
            while (head!=null){
                out.print(head.val + " ");
                head=head.next;
            }
        }
    }
    private static class ListNode{
        ListNode next;
        ListNode prev;

        int val;

        public ListNode(int val) {
            this.val = val;
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
