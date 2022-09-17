import java.io.*;
import java.util.StringTokenizer;

public class Solution1 {

    static final int MAX_N = 200;           // max no. of vertices
    static final int MAX_E = 10000;         // max no. of edges

    static int N, edges;

    static final int[] sources = new int[MAX_E];
    static final int[] vertices = new int[MAX_E];
    static final int[] weights = new int[MAX_E];

    static int Answer;
    public static void main(String[] args) throws Exception {

        BufferedReader reader = new BufferedReader(new FileReader("input1.txt"));
        StringTokenizer stTokenizer;
        PrintWriter printWriter = new PrintWriter("output1.txt");
        
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

            int[][][] distance = new int[N+1][N+1][N+1];

            // Implementing Floyd-Warshall

            for (int i = 0; i < N; i++) {

                for (int j = 0; j < N; j++) 
                    distance[i][j][0] = 20000;              // intialize "infinity"
                
                distance[i][i][0] = 0;                      // all edges to self = 0
            }

            for (int i = 0; i < edges; i++) {
                int s = sources[i] - 1;
                int v = vertices[i] - 1;
                distance[s][v][0] = weights[i];
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    for (int k = 0; k < N; k++) {
                        final int dist1 = distance[j][k][i];
                        final int dist2 = distance[j][i][i] + distance[i][k][i];
                        
                        if (dist1 < dist2)
                            distance[j][k][i + 1] = dist1;
                        else
                            distance[j][k][i + 1] = dist2;
                    }
                }
            }

            Answer = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    Answer += distance[i][j][N];
                }
            }

            printWriter.println("#".concat(Integer.toString(cases)).concat(" ").concat(Integer.toString(Answer)));
            printWriter.flush();
        }
        reader.close();
        printWriter.close();
    }
}