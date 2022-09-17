import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

public class Solution3_2 {

/*
   1. If the following command is entered, compilation should be done, and a class named Solution3 must be created before scoring is done.
       javac Solution3.java -encoding UTF8
   2. After compiling, when you enter the following command, your program should normally generate the output file output1.txt for scoring.
       java Solution3
   - Please note that the encoding of the submitted source code must be UTF8.
   - To measure execution time, you can use the time command as follows.
       time java Solution3
   - You can use the timeout command as follows to forcibly terminate the program when a certain time is exceeded.
       timeout 0.5 java Solution3 // Perform 0.5 seconds
       timeout 1 java Solution3 // Perform 1 second
 */

    static final int MAX_N = 1000;
    static final int MAX_E = 100000;
    static  final  int Div =  100000000 ; 
    static  int  N , E ;
    static int[] U = new int[MAX_E], V = new int[MAX_E], W = new int[MAX_E];
    static int[] answer1 = new int[MAX_N + 1];
    static int[] answer2 = new int[MAX_N + 1];

    static double start1, start2;
    static double time1, time2;


    public static void main(String[] args) throws Exception {
		/*
		   Read data from input1.txt in the same folder.
		   Also, the correct answer is output to output1.txt in the same folder.
		 */
        BufferedReader br = new BufferedReader(new FileReader("input3.txt"));
        StringTokenizer stk;
        PrintWriter pw = new PrintWriter("output3-2.txt");

		/*
		   Since we are given 10 test cases, we process each of them.
		 */
        for (int test_case = 1; test_case <= 10; test_case++) {
			/*
			   Each test case is read from standard input.
			   First, the number of vertices and the number of edges are read into N and E, respectively.
			   And the starting point number of each i-th edge is read into U[i], the end point number into V[i], and the weight of the edge into W[i].
			   (0 ≤ i ≤ E-1, 1 ≤ U[i] ≤ N, 1 ≤ V[i] ≤ N)
			 */
            stk = new StringTokenizer(br.readLine());
            N = Integer.parseInt(stk.nextToken());
            E  =  Integer . parseInt (stk . nextToken ());
            stk = new StringTokenizer(br.readLine());
            for (int i = 0; i < E; i++) {
                U[i] = Integer.parseInt(stk.nextToken());
                V[i] = Integer.parseInt(stk.nextToken());
                W[i] = Integer.parseInt(stk.nextToken());
            }

            /* Problem 1-1 */
            start1 = System.currentTimeMillis();
            answer1 = BellmanFord1(1);
            time1 = (System.currentTimeMillis() - start1);

            /* Problem 1-2 */
            start2 = System.currentTimeMillis();
            answer2 = BellmanFord2(1);
            time2 = (System.currentTimeMillis() - start2);

            // Output the answer to output1.txt.
            pw.println("#" + test_case);
            for (int i = 1; i <= N; i++) {
                pw.print(answer1[i]);
                if (i != N)
                    pw.print(" ");
                else
                    pw.print("\n");
            }
            pw.println(time1);

            for (int i = 1; i <= N; i++) {
                pw.print(answer2[i]);
                if (i != N)
                    pw.print(" ");
                else
                    pw.print("\n");
            }
            pw.println(time2);
			/*
			   If you do not execute the code below, when your program is forcibly terminated due to timeout,
			   The output may not actually be written to the file.
			   Therefore, be sure to perform flush() for safety.
			 */
            pw.flush();
        }

        br.close();
        pw.close();
    }

    public  static  int [] BellmanFord1 ( int  r ) {
        int[] d = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            d[i] = 2147482647;
        }
        d[r] = 0;
        for (int i = 1; i < N; i++) {
            for (int k = 0; k < E; k++) {
                int u = U[k], v = V[k], w = W[k];
                if (d[u] + w < d[v]) {
                    d[v] = d[u] + w;
                }
            }
        }
        for (int i = 1; i <= N; i++)
            d[i] = d[i] % 100000000;
        return d;
    }
    // Since the longest loop is a double-overlapping for loop, it repeats N and E times, respectively, so the execution time is bit-theta(NE) - N number of vertices E number of edges

    public  static  int [] BellmanFord2 ( int  r ) {
        int[] d = new int[N + 1];
        int[][] connectMatrix = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            d[i] = 2147482647;
        }
        d[r] = 0;
        for (int i = 1; i <= N; i++) {
            for (int k = 1; k <= N; k++) {
                connectMatrix[i][k] = 1001;
            }
        }
        for (int k = 0; k < E; k++) {
            connectMatrix[U[k]][V[k]] = W[k];
        }
        Boolean[] updateNode = new Boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            updateNode[i] = false;
        }
        updateNode[r] = true;
        int count = 0;
        while (count < N) {
            count = 0;
            for (int i = 1; i <= N; i++) {
                if (updateNode[i]) {
                    updateNode[i] = false;
                    for (int k = 1; k <= N; k++) {
                        int weight = connectMatrix[i][k];
                        if (weight < 1001 && d[i] + weight < d[k]) {
                            d[k] = d[i] + weight;
                            updateNode[k] = true;
                        }
                    }
                } else
                    count++;
            }
        }
        for (int i = 1; i <= N; i++)
            d[i] = d[i] % 100000000;
        return d;
    }

}
