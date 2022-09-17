import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
   1. Compile the file with the following command. The class file named Solution1 would be created when you compile the source code.
       javac Solution1.java -encoding UTF8

   2. Run the compiled program with the following command. Output file(output1.txt) should be created after the program is finished
       java Solution1

   - The encoding of the source code should be UTF8
   - You can use the 'time' command to measure your algorithm.
       time java Solution1
   - You can also halt the program with the 'timeout' command.
       timeout 0.5 java Solution1
       timeout 1 java Solution1
 */

class Solution1_2 {
	static final int MAX_N = 200;
	static final int MAX_E = 10000;

	static int N, E;
	static int[] U = new int[MAX_E], V = new int[MAX_E], W = new int[MAX_E];
	static int Answer;

	public static void main(String[] args) throws Exception {
		/*
		   Read the input from input.txt
		   Write your answer to output.txt
		 */
		BufferedReader br = new BufferedReader(new FileReader("input1.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output1-2.txt");  // Tumi change this to say "output1.txt"

		for (int test_case = 1; test_case <= 2; test_case++) {

			stk = new StringTokenizer(br.readLine());
			N = Integer.parseInt(stk.nextToken()); E = Integer.parseInt(stk.nextToken());
			
            /////////////////////////////////////////////////////////////////////////////////////////////
			/*
			   YOUR CODE HERE
			   Save your answer to the variable Answer
			 */

			ArrayList[] adjlist = new ArrayList[N+1];		
			for(int i = 1; i <= N; i++)
			{
				adjlist[i] = new ArrayList();
			}
			
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < E; i++) {
				U[i] = Integer.parseInt(stk.nextToken());
				V[i] = Integer.parseInt(stk.nextToken());
				W[i] = Integer.parseInt(stk.nextToken());
				
				adjlist[U[i]].add(i);		
			} 
			
			int sol[][][] = new int[N+1][N+1][N+1];			
			
			for(int i=1;i<=N;i++)			
			{
				for(int j=1;j<=N;j++)		
				{
					sol[0][i][j] = 20000000;		
				}
				sol[0][i][i] = 0;			
				for(int j=0;j<adjlist[i].size();j++)	
				{
					int adjvertex = V[(int) adjlist[i].get(j)];
					int weight = W[(int) adjlist[i].get(j)];
					sol[0][i][adjvertex] = weight;
				}
			}
			
			for(int k=1;k<=N;k++)		
			{
				for(int i=1;i<=N;i++)
				{
					for(int j=1;j<=N;j++)
					{
						int minimum = (sol[k-1][i][j] < (sol[k-1][i][k] + sol[k-1][k][j])) ? sol[k-1][i][j] : (sol[k-1][i][k] + sol[k-1][k][j]);
						sol[k][i][j] = minimum;
					}
				}
			}	
			
			Answer = 0;
			for(int i=1;i<=N;i++)
			{
				for(int j=1;j<=N;j++)
				{
					Answer += sol[N][i][j]; 
				}
			}
            /////////////////////////////////////////////////////////////////////////////////////////////

            // Print the answer to output1.txt
			pw.println("#" + test_case + " " + Answer);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}
}