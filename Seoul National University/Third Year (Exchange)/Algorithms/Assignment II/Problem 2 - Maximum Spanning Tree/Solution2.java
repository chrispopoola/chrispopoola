import java.io.*;
import java.util.StringTokenizer;

public class Solution2 {

    static final int MAX_N = 20000;
    static final int MAX_E = 40000;

    static int N, edges;

    static int[] sources = new int[MAX_E];
    static int[] vertices = new int[MAX_E];
    static int[] weights = new int[MAX_E];
    static int Answer;
    static Node[] nodes = new Node[MAX_N];

    private static class Node {
        int key, rank;
        Node parent;

        public Node(int key) {
            this.key = key;
            parent = this;
            rank = 0;
        }

        Node find() {
            if (parent != this)
                parent = parent.find();
            return parent;
        }

        static void combine(Node x, Node y) {
            x = x.find();
            y = y.find();

            if (x.rank > y.rank) 
                y.parent = x;
            else {
                x.parent = y;
                if (x.rank == y.rank)
                    y.rank++;
            }
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("input2.txt"));
        StringTokenizer stTokenizer;
        PrintWriter printWriter = new PrintWriter("output2.txt");

        for (int cases = 1; cases <= 10; cases++) {

            stTokenizer = new StringTokenizer(reader.readLine());
            N = Integer.parseInt(stTokenizer.nextToken());
            edges = Integer.parseInt(stTokenizer.nextToken());
            
            stTokenizer = new StringTokenizer(reader.readLine());

            sources = new int[edges];
            vertices = new int[edges];
            weights = new int[edges];

            for (int i = 0; i < edges; i++) {
                sources[i] = Integer.parseInt(stTokenizer.nextToken());
                vertices[i] = Integer.parseInt(stTokenizer.nextToken());
                weights[i] = Integer.parseInt(stTokenizer.nextToken());
            }

            // Implementing Kruskal

            for (int i = 0; i < N; i++)
                nodes[i] = new Node(i);

            int[] sources_s = sources.clone();
            int[] vertices_v = vertices.clone();
            int[] weights_w = weights.clone();

            radixSort(weights, sources, vertices, weights_w, sources_s, vertices_v);

            sources = sources_s;
            vertices = vertices_v;
            weights = weights_w;

            int N_edges = 0;
            int i = edges - 1;
            int weights_Sum = 0;

            while (N_edges < N - 1 && i >= 0) {
                Node node_U, node_V;

                node_U = nodes[sources[i] - 1];
                node_V = nodes[vertices[i] - 1];

                if (node_U.find() != node_V.find()) {
                    Node.combine(node_U, node_V);
                    N_edges++;
                    weights_Sum = weights_Sum + weights[i];
                }
                i--;
            }
            Answer = weights_Sum;

            printWriter.println("#".concat(Integer.toString(cases)).concat(" ").concat(Integer.toString(Answer)));
            printWriter.flush();
        }
        reader.close();
        printWriter.close();
    }

    static int[] radixSort(int[] W, int[] S, int[] V, int[] b_W, int[] b_S, int[] b_V) {

        int[] currentW = W;
        int[] currentV = V;
        int[] currentS = S;

        for (int i = 0; i < 4; i++) {
            int[] count = new int[256];

            for (int j = 0; j < currentW.length; j++) {
                int c_byte = (currentW[j] >> (i << 3)) & 0x00FF; 
                count[c_byte]++;
            }

            for (int j = 1; j < 256; j++) 
                count[j] = count[j] + count[j - 1];
        
            for (int j = currentW.length - 1; j >= 0; j--) {
                int c_byte = (currentW[j] >> (i << 3)) & 0x00FF;
                final int index = (count[c_byte] - 1);
                
                b_W[index] = currentW[j];
                b_V[index] = currentV[j];
                b_S[index] = currentS[j];

                count[c_byte]--;
            }

            int[] temp = currentW;
            currentW = b_W;
            b_W = temp;
            temp = currentS;
            currentS = b_S;
            b_S = temp;
            temp = currentV;
            currentV = b_V;
            b_V = temp;
        }
        
        return currentW;
    }

}

