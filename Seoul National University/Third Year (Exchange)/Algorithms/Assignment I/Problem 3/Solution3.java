import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
   1. Compile the file with the following command. The class file named Solution3 would be created when you compile the source code.
       javac Solution3.java -encoding UTF8

   2. Run the compiled program with the following command. Output file(output.txt) should be created after the program is finished
       java Solution3

   - The encoding of the source code should be UTF8
   - You can use the 'time' command to measure your algorithm.
       time java Solution3
   - You can also halt the program with the 'timeout' command.
       timeout 0.5 java Solution3
       timeout 1 java Solution3
 */

class Solution3 {
	static final int max_n = 100000;

	static int n;
	static int[][] A = new int[3][max_n];
	static int Answer;

	public static void main(String[] args) throws Exception {
		/*
		   Read the input from input.txt
		   Write your answer to output.txt
		 */
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer stk;
		PrintWriter pw = new PrintWriter("output.txt");

		for (int test_case = 1; test_case <= 10; test_case++) {
			
			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken());
			for (int i = 0; i < 3; i++) {
				stk = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					A[i][j] = Integer.parseInt(stk.nextToken());
				}
			}


			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			   YOUR CODE HERE
			   Save your answer to the variable Answer
			 */
			int N = n, max1, max2, max3;
			// storing the maximum values for each pattern
			int []OAX = new int[N + 1];
			int []OXA = new int[N + 1];
			int []AOX = new int[N + 1];
			int []AXO = new int[N + 1];
			int []XOA = new int[N + 1];
			int []XAO = new int[N + 1];

			OAX[0] = 0; OXA[0] = 0; AOX[0] = 0; AXO[0] = 0; XOA[0] = 0; XAO[0] = 0;

			for (int i = 1; i <= N; i++) {
				OAX[i] = Math.max(AXO[i-1], XOA[i-1]) + A[0][i-1] - A[2][i-1];
				OXA[i] = Math.max(AOX[i-1], XAO[i-1]) + A[0][i-1] - A[1][i-1];
				AOX[i] = Math.max(OXA[i-1], XAO[i-1]) + A[1][i-1] - A[2][i-1];
				AXO[i] = Math.max(XOA[i-1], OAX[i-1]) + A[2][i-1] - A[1][i-1];
				XOA[i] = Math.max(OAX[i-1], AXO[i-1]) + A[1][i-1] - A[0][i-1];
				XAO[i] = Math.max(OXA[i-1], AOX[i-1]) + A[2][i-1] - A[0][i-1];
			}
			max1 = Math.max(OAX[N], OXA[N]);
			max2 = Math.max(AOX[N], AXO[N]);
			max3 = Math.max(XOA[N], XAO[N]);
			
			/////////////////////////////////////////////////////////////////////////////////////////////
			Answer = Math.max(Math.max(max1, max2), max3);


			// Print the answer to output.txt
			pw.println("#" + test_case + " " + Answer);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}
}

