import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.PrintWriter;

/*
   1. Compile the file with the following command. The class file named Solution4 would be created when you compile the source code.
       javac Solution4.java -encoding UTF8

   2. Run the compiled program with the following command. Output file(output.txt) should be created after the program is finished
       java Solution4

   - The encoding of the source code should be UTF8
   - You can use the 'time' command to measure your algorithm.
       time java Solution4
   - You can also halt the program with the 'timeout' command.
       timeout 0.5 java Solution4
       timeout 1 java Solution4
 */

class Solution4 {
	static final int max_n = 1000;

	static int n, H;
	static int[] h = new int[max_n], d = new int[max_n-1];
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
			/*
			   n is the number of blocks, and H is the max tower height
			   read each height of the block to h[0], h[1], ... , h[n-1]
			   read the heights of the holes to d[0], d[1], ... , d[n-2]
			 */
			stk = new StringTokenizer(br.readLine());
			n = Integer.parseInt(stk.nextToken()); H = Integer.parseInt(stk.nextToken());
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				h[i] = Integer.parseInt(stk.nextToken());
			}
			stk = new StringTokenizer(br.readLine());
			for (int i = 0; i < n-1; i++) {
				d[i] = Integer.parseInt(stk.nextToken());
			}


			/////////////////////////////////////////////////////////////////////////////////////////////
			/*
			   YOUR CODE HERE
			   Save your answer to the variable Answer
			 */
			int N = n;
			int max_height = 10000;
			int []ok = new int[max_height + 1];
			int []consec = new int[max_height + 1];
			int []temp = new int [max_height + 1]; 

			int okN = H + 1; int consecN = H + 1;
			int height, hole, okNext, consecNext;

			// intialising the arrays
			for (int i = 0; i < H + 1; i++) {
				ok[i] = 0;
				consec[i] = 0;
				temp[i] = 0;
			}

			height = h[0]; hole = 0;

			// stacking block 1
			if (height <= H)
				consec[height] += 1;
			
			for (int i = 1; i < N; i++) {

				height = h[i];
				hole = d[i - 1];

				for (int j = 1; j < okN; j++) {
						
					// height after blocks are stacked
					okNext = j + height;
					// height after blocks are stacked (- holes)
					consecNext = j + height - hole;
						
					if (okNext <= H)
						temp[okNext] = (temp[okNext] + ok[j]) % 1000000;
					if (consecNext <= H)
						temp[consecNext] = (temp[consecNext] + consec[j]) % 1000000;
					
						ok[j] = (ok[j] + consec[j]) % 1000000;
					}
					// stack block when it's empty
				temp[height] = (temp[height] + 1) % 1000000;

				for (int j = 1; j < consecN; j++) {
					consec[j] = temp[j];
					temp[j] = 0;
				}
			}
			Answer = 0;
			for (int i = 1; i < okN; i++) 
				Answer = (Answer + ok[i] + consec[i]) % 1000000;
			/////////////////////////////////////////////////////////////////////////////////////////////
			// Answer = 0;


			// Print the answer to output.txt
			pw.println("#" + test_case + " " + Answer);
			// To ensure that your answer is printed safely, please flush the string buffer while running the program
			pw.flush();
		}

		br.close();
		pw.close();
	}
}

