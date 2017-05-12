package week4;

import java.io.*;
import java.util.*;

/**
 * Created by sherxon on 5/11/17.
 */
/**
* The input ﬁle contains a description of an unweighted directed graph. You are asked to determine whether this graph contains a cycle. If it does, print any of them.

 Input
 The ﬁrst line of the input ﬁle contains two integers N and M (1≤N≤105,0≤M≤105), the number of vertices and edges in the graph, correspondingly. The following M lines contain descriptions of edges of the graph. Each edge is described by a pair of integers – the indices of the source and target vertex, respectively.

 All indices are one-based. The graph may contain loops and multiple edges between the same ordered pair of vertices.

 Output
 If the graph does not contain a cycle, print “NO”.

 Otherwise, print “YES”. In the next line print the vertices which consitute a cycle, in the order along this cycle.


 * */
public class FindCycle {
   static Map<Integer, Set<Integer>> map;
   static Map<Integer, Integer> parent= new HashMap<>();
   static Set<Integer> visited= new HashSet<>();
   static Set<Integer> closedSet= new HashSet<>();
   static Set<Integer> inverti= new HashSet<>();
   static boolean found=false;

    public static void main(String[] args) throws IOException {
        try (PrintWriter out = newOutput()) {
            FastScanner in=newInput();
            int n= in.nextInt();
            map= new HashMap<>(n);
            int m=in.nextInt();

            for (int i = 0; i < m; i++) {
                int v1=in.nextInt();
                int v2=in.nextInt();
                if(v1 == v2)continue;
                if(!map.containsKey(v1))
                    map.put(v1, new HashSet<>());
                map.get(v1).add(v2);
                inverti.add(v2);
            }

            while (visited.size()!=n){
                int start=getRandomStart(n);
                if(start==0 && !found){
                    out.print("YES");
                    out.println();
                    for (Integer integer : map.keySet()) {
                        out.print(integer+ " ");
                    }
                    found=true;
                    break;
                }
                dfs(out, start);
            }
            if(!found)out.print("NO");

        }


    }

    private static void dfs(PrintWriter out, int start) {
       visited.add(start);
       closedSet.add(start);
       if(map.containsKey(start))
        for (Integer nei : map.get(start)) {
            if(closedSet.contains(nei) && !found){
                found=true;
                out.println("YES");
                while (start != nei){
                    out.print(start + " ");
                    start=parent.get(start);
                }
                out.print(start);
                break;
            }
            if(!visited.contains(nei) && !found){
                parent.put(nei, start);
                dfs(out, nei);
            }
        }
        closedSet.remove(start);
    }

    public static Integer getRandomStart(int n) {
        for (int i = 1; i <=n; i++) {
            if(!inverti.contains(i) && !visited.contains(i)){
                inverti.remove(i);
                return i;
            }
        }
        return 0;
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
