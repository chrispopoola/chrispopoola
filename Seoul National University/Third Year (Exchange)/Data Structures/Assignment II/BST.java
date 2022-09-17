// (Nearly) Optimal Binary Search bst
// Bongki Moon (bkmoon@snu.ac.kr)

import java.util.*;

public class BST { // Binary Search bst implementation

	protected boolean NOBSTified = false;
  	protected boolean OBSTified = false;
	
	protected String key;
	protected BST left, right;
	protected int frequency, accessCount;

	private static ArrayList<BST> nodeArray;
	private static int[][] roots;

  	public BST() { 
		key = null;
		frequency = 0;
		accessCount = 0;
		left = null;
		right = null;
	}
	// get required element from BST bst
	private interface IF {
		int get(BST bst);
	}

	private int traverse(IF IF, BST bst) {
		int lSum, rSum, current;
		if (bst.left == null)
			lSum = 0;
		else
			lSum = traverse(IF, bst.left);
		
		current = IF.get(bst);

		if (bst.right == null)
			rSum = 0;
		else
			rSum = traverse(IF, bst.right);

		return lSum + rSum + current;
	}

  	public int size() {
		return traverse(bst -> 1, this);
	}

  	public void insert(String key) { 

		if (this.key == null) {
			this.key = key;
			frequency = 1;
		}
		else if (this.key.compareTo(key) > 0) {
			if (left == null)
				left = new BST();
			left.insert(key);
		}
		else if (this.key.compareTo(key) < 0) {
			if (right == null)
				right = new BST();
			right.insert(key);
		}
		else 
			frequency++;
	}

  	public boolean find(String key) {
		accessCount++;

		if (this.key.compareTo(key) > 0)
			return left != null && left.find(key);
		else if (this.key.compareTo(key) < 0)
			return right != null && right.find(key);
		return true;
	}

  	public int sumFreq() { 
		return traverse(bst -> bst.frequency, this);
	}

  	public int sumProbes() { 
		return traverse(bst -> bst.accessCount, this);
	}

  	public int sumWeightedPath() { 
		return traverse(BST::sumFreq, this);
	}

  	public void resetCounters() { 
		traverse(bst -> {	bst.accessCount = 0; 
							bst.frequency = 0; 
							return 0;
							}, this);
	}

  	public void nobst() { // Set NOBSTified to true.

		int N = size();
		nodeArray = new ArrayList<>();
		roots = new int[N+1][];

		int[] sumFrequency = new int[N+1];
		
		BST tempBST = new BST();
		tempBST.copy(this);

		nodeArray.add(null);
		traverse(bst -> {	nodeArray.add(bst);
							return 0;
						}, tempBST);
		
		sumFrequency[0] = 0;

		for (int i = 1; i <= N; i++) {
			roots[i] = new int[i+2];
			sumFrequency[i] = nodeArray.get(i).frequency + sumFrequency[i-1];
		}
		nobst(sumFrequency, 1, N);
		build(1, N);

		BST NOBST = nodeArray.get(roots[N][1]);
		copy(NOBST);
		NOBSTified = true;
	}
	private void nobst(int[] sumFrequency, int left, int right) {

		if (left > right)
			return;
		int lSum, rSum;
		int i = left;
		while (i <= right) {
			lSum = sumFrequency[i-1] - sumFrequency[left-1];
			rSum = sumFrequency[right] - sumFrequency[i];

			if (i == right || Math.abs(lSum - rSum) <= Math.abs(lSum - rSum + nodeArray.get(i).frequency + nodeArray.get(i+1).frequency))
				break;
			i++;
		}

		roots[right][left] = i;
		nobst(sumFrequency, left, i-1);
		nobst(sumFrequency, i+1, right);

	}

	public void copy(BST bst) {
		key = bst.key;
		frequency = bst.frequency;
		accessCount = bst.accessCount;
		left = bst.left;
		right = bst.right;
	}
  	
	public void obst() {  // Set OBSTified to true.
		int N = size();
		nodeArray = new ArrayList<>();
		roots = new int[N+1][];
		

		int[][] costs = new int[N+1][];
		int[][] sumFrequency = new int[N+1][];
		nodeArray.add(null);

		BST tempBST = new BST();
		// copy BST into tempBST to be used for building OBST
		tempBST.copy(this);

		// put every BST in nodeArray (in order)
		traverse(bst -> {	nodeArray.add(bst);
							return 0;
							}, tempBST);
		
		for (int high = 0; high <= N; high++) {
			sumFrequency[high] = new int[high+2];
			roots[high] = new int[high+2];
			costs[high] = new int[high+2];

			int costSum = (high == 0) ? 0 : nodeArray.get(high).frequency;

			sumFrequency[high][high] = costSum;
			costs[high][high] = costSum;
			roots[high][high] = high;

			for (int low = high-1; low > 0; low--) {
				costSum = costSum + nodeArray.get(low).frequency;
				sumFrequency[high][low] = costSum;
			}
		}

		for (int r = 2; r <= N; r++) {

			int leftB, rightB;
			for (int l = r-1; l > 0; l--) {
				leftB = roots[r-1][l];
				rightB = roots[r][l+1];
				int minCost = Integer.MAX_VALUE;
				int cost, index = l;

				for (int i = leftB; i <= rightB; i++) {
					cost = costs[i-1][l] + costs[r][i+1] + sumFrequency[r][l];

					if (cost < minCost) {
						minCost = cost;
						index = i;
					}
				}

				costs[r][l] = minCost;
				roots[r][l] = index;
			}
		}
		// now we use the optimal roots to build OBST
		build(1, N);
		BST OBST = nodeArray.get(roots[N][1]);
		// copy OBST into current BST
		copy(OBST);
		OBSTified = true;
	}

	private BST build(int left, int right) {
		if (left > right)
			return null;
		BST root;
		int index;
		index = roots[right][left];
		root = nodeArray.get(index);

		if (root == null)
			return null;
		
		root.right = build(index+1, right);
		root.left = build(left, index-1);

		return root;
	}

  	public void print() { 

		traverse(bst -> {
					System.out.println("[" + bst.key + " : " + bst.frequency + " : " + bst.accessCount + "]");
					return 0;
					}, this);
	}

	// public static void main (String[] args) {
	// BST BST = new BST();

    // // a b c d b c d c d c
    // BST.insert("a");
    // BST.insert("b");
    // BST.insert("c");
    // BST.insert("d");
    // BST.insert("b");
    // BST.insert("c");
    // BST.insert("d");
    // BST.insert("c");
    // BST.insert("d");
    // BST.insert("c");

    // BST.find("a");
    // BST.find("b");
    // BST.find("c");
    // BST.find("d");

    // BST.print();
    // System.out.println(BST.size());
	// }
}
