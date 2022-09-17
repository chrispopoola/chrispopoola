import java.io.*;
import java.util.StringTokenizer;

public class Solution3 {

    static final int MAX_N = 1000;
    static final int MAX_E = 100000;
//  static final int mod = 100000000; // distances mod 100,000,000

    static int N, edges;

    static final int[] sources = new int[MAX_E];
    static final int[] vertices = new int[MAX_E];
    static final int[] weights = new int[MAX_E];
    
    static int[] answer_1 = new int[MAX_N + 1];
    static int[] answer_2 = new int[MAX_N + 1];
    static double start_1, start_2, time_1, time_2;

    static final int[][] adjArray = new int[MAX_N][MAX_N];
    static final int[] adjArrayLength = new int[MAX_N];

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("input3.txt"));
        StringTokenizer stTokenizer;
        PrintWriter printWriter = new PrintWriter("output3.txt");

        for (int cases = 1; cases <= 10; cases++) {

            stTokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(stTokenizer.nextToken());
            edges = Integer.parseInt(stTokenizer.nextToken());
                
            stTokenizer = new StringTokenizer(reader.readLine());
            for (int i = 0; i < edges; i++) {
                sources[i] = Integer.parseInt(stTokenizer.nextToken());
                vertices[i] = Integer.parseInt(stTokenizer.nextToken());
                weights[i] = Integer.parseInt(stTokenizer.nextToken());
            }

            // Version 1
            start_1 = System.currentTimeMillis();
            answer_1 = BellmanFord1();
            time_1 = (System.currentTimeMillis() - start_1);
            // Verson 2
            start_2 = System.currentTimeMillis();
            answer_2 = BellmanFord2();
            time_2 = (System.currentTimeMillis() - start_2);

            printWriter.println("#" + cases);
            for (int i = 1; i <= N; i++) {
                printWriter.print(answer_1[i]);
                if (i != N)
                    printWriter.print(" ");
                else
                    printWriter.print("\n");
            }
            printWriter.println(time_1);

            for (int i = 1; i <= N; i++) {
                printWriter.print(answer_2[i]);
                if (i != N)
                    printWriter.print(" ");
                else
                    printWriter.print("\n");
            }
            printWriter.println(time_2);
            printWriter.flush();

        }
        reader.close();
        printWriter.close();
    }
    // pseudocode
    // BellmanFord(G, r)
    // for each u∈V
    // du← ∞ dr ← 0
    // for i ←1 to |V|-1
    // for each (u, v) ∈ E
    // --------------------- ❶ if(du +wu,v <dv ) dv ←du +wu,v ;

    static int[] BellmanFord1() {
        for (int i = 1; i <= N; i++) 
            answer_1[i] = 2000;
        
        answer_1[1] = 0;
        
        for (int i = 1; i <= N - 1; i++) {
            for (int j = 0; j < edges; j++) {
                final int source = sources[j];
                final int vertex = vertices[j];
                final int answer = answer_1[source] + weights[j];
                if (answer < answer_1[vertex])
                    answer_1[vertex] = answer;
            }
        }
        return answer_1;
    }
    
    private static class Queue {
        final int N = MAX_N;
        final int[] queue = new int[N];
        int read, write;
        boolean empty;

        public Queue() {
            this.read = 0;
            this.write = 0;
        }

        boolean isEmpty() {
            empty = (read % N) == (write % N);
            return empty;
        }

        void enqueue(int key) {
            queue[write % N] = key;
            write++;
        }

        int remove() {
            final int key = queue[read % N];
            read++;
            return key;
        }

        void clear() {
            read = 0;
            write = 0;
        }

    }

    // // implement using a queue
    static final Queue queue = new Queue();
    static final boolean[] bool = new boolean[MAX_N];
    
    static int[] BellmanFord2() {

        final int startV = 0;

        for (int i = 0; i < N; i++) {
            adjArrayLength[i] = 0;
            answer_2[i] = 2000;
        }
        answer_2[1] = 0;
        answer_2[N] = 2000;

        for (int i = 0; i < edges; i++) {
            final int source = sources[i] - 1;      // start edge

            adjArray[source][adjArrayLength[source]] = i;
            adjArrayLength[source]++;
        }

        queue.clear();
        queue.enqueue(startV);
        bool[startV] = true;

        while (!queue.isEmpty()) {
            int s = queue.remove();
            bool[s] = false;
            final int adj_N = adjArrayLength[s];
            final int up = s + 1;

            // relax
            for (int i = 0; i < adj_N; i++) {
                final int edge = adjArray[s][i];
                final int v1 = vertices[s];
                final int v = v1 - 1;
                final int answer = answer_2[up] + weights[edge];
                
                if (answer_2[v1] > answer) {
                    answer_2[v1] = answer;
                    if (!bool[v]) {
                        queue.enqueue(v);
                        bool[v] = true;
                    }
                }
            }
        }
        return answer_2;
    }
}
