import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * Created by sherxon on 3/15/17.
 */
public class PostfixNotation {
    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in = newInput();
            int n=Integer.parseInt(in.nextLine());
            Stack<Integer> stack=new Stack<>();
            String[] s=in.nextLine().split(" ");
            for (int i = 0; i < s.length; i++) {

                if(s[i].length()==1 && !Character.isDigit(s[i].charAt(0))){
                    char c=s[i].charAt(0);
                    if(c=='+')stack.push(stack.pop()+stack.pop());
                    else if(c=='*')stack.push(stack.pop()*stack.pop());
                    else if(c=='-')stack.push((stack.pop()-stack.pop())*-1);
                }else{
                    stack.add(Integer.parseInt(s[i]));
                }
            }
            out.println(stack.peek());
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
