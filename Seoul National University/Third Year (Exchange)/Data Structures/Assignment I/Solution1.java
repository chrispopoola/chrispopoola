import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
   1. Compile the file with the following command. The class file named Solution1 would be created when you compile the source code.
       javac Solution1.java -encoding UTF8

   2. Run the compiled program with the following command. Output file(output.txt) should be created after the program is finished
       java Solution1

   - The encoding of the source code should be UTF8
   - You can use the 'time' command to measure your algorithm.
       time java Solution1
   - You can also halt the program with the 'timeout' command.
       timeout 0.5 java Solution1
       timeout 1 java Solution1
 */

class Solution1 {

	/*
	   Since the results cannot be covered with the boundary of int type, use long type for the variable of the result.
	   Overflowed cases will be counted in zero points.
	   We assume that Answer[0] is the number of "a", Answer[1] is the number of "b", Answer[2] is the number of "c".
	*/
	static int n;                           // string length
	static String s;                        // string sequence
	static long[] Answer = new long[3];

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
			s = br.readLine();

			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			   YOUR CODE HERE
			   You can massage string s whatever you want.
			   Save your answer to the variable Answer(long type!)
			 */
			char[] array = s.toCharArray();
			int N = s.length();

			long[][] A = new long[N+1][N+1];
			long[][] B = new long[N+1][N+1];
			long[][] C = new long[N+1][N+1];

			// initialising the arrays
			for (int i = 1; i <= N; i++) {
				if (array[i - 1] == 'a')
					A[i][i] = 1;
				if (array[i - 1] == 'b')
					B[i][i] = 1;
				if (array[i - 1] == 'c')
					C[i][i] = 1;
			}

			for (int k = 1; k <= N - 1; k++) {

				for (int i = 1; i <= N - k; i++) {
					long val_A = 0, val_B = 0, val_C = 0;

					for (int j = i; j <= i + (k - 1); j++) {
						val_A += A[i][j] * C[j + 1][i + k];
						val_A += B[i][j] * C[j + 1][i + k];
						val_A += C[i][j] * A[j + 1][i + k];
					}
					for (int j = i; j <= i + (k - 1); j++) {
						val_B += A[i][j] * A[j + 1][i + k];
						val_B += A[i][j] * B[j + 1][i + k];
						val_B += B[i][j] * B[j + 1][i + k];
					}
					for (int j = i; j <= i + (k - 1); j++) {
						val_C += B[i][j] * A[j + 1][i + k];
						val_C += C[i][j] * B[j + 1][i + k];
						val_C += C[i][j] * C[j + 1][i + k];
					}
					A[i][i + k] = val_A;
					B[i][i + k] = val_B;
					C[i][i + k] = val_C;
				}
			}

			/////////////////////////////////////////////////////////////////////////////////////////////
			Answer[0] = A[1][N];  // the number of a
			Answer[1] = B[1][N];  // the number of b
			Answer[2] = C[1][N];  // the number of c


			// Print the answer to output.txt
			pw.println("#" + test_case + " " + Answer[0] + " " + Answer[1] + " " + Answer[2]);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}
}

