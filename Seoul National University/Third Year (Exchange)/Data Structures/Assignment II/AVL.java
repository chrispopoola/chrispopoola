// AVL Binary Search Tree
// Bongki Moon (bkmoon@snu.ac.kr)
  
public class AVL extends BST {

	private int height;

  	public AVL() { 
		key = null;
		left = null;
		right = null;
		frequency = 0;
		accessCount = 0;
		height = 0;
	}
  
  	public void insert(String key) { 
		if (this.key == null) {
			this.key = key;
			frequency = 1;
		} 
		else if (this.key.compareTo(key) > 0) {
			if (left == null)
				left = new AVL();
			left.insert(key);
			
			if (getHeight(left) - getHeight(right) == 2) {
				if (left.key.compareTo(key) > 0)
					rightTurn();
				else
					leftRightTurn();
			}
			getHeight(this);
		}
		else if (this.key.compareTo(key) < 0) {
			if (right == null)
				right = new AVL();
			right.insert(key);
			
			if (getHeight(left) - getHeight(right) == -2) {
				if (right.key.compareTo(key) < 0)
					leftTurn();
				else 
					rightLeftTurn();
			}
			getHeight(this);
		}
		else 
			frequency++;
	}

	private int getHeight(BST bst) {

		if (bst == null)
			return 0;
		
		int lHeight, rHeight;

		if (right != null && left != null && Math.max(((AVL)left).height, ((AVL)right).height) + 1 == height)
			return height;
		
		lHeight = getHeight(((AVL)bst).left);
		rHeight = getHeight(((AVL)bst).right);
		((AVL)bst).height = Math.max(lHeight, rHeight) + 1;
		return ((AVL)bst).height;
	}

	private void leftTurn() {
		AVL tempLeft = new AVL();
		tempLeft.copy(this);
		AVL rightLeft = (AVL) right.left;

		right.left = null;
		key = right.key;
		frequency = right.frequency;
		accessCount = right.accessCount;
		right = right.right;
		left = tempLeft;
		left.right = rightLeft;
	}

	private void rightTurn() {
		AVL tempRight = new AVL();
		tempRight.copy(this);
		AVL leftRight = (AVL) left.right;

		left.right = null;
		key = left.key;
		frequency = left.frequency;
		accessCount = left.accessCount;
		left = left.left;
		right = tempRight;
		right.left = leftRight;
	}
	
	private void rightLeftTurn() {
		AVL rAVL = (AVL) right;
		rAVL.rightTurn();
		leftTurn();
	}

	private void leftRightTurn() {
		AVL lAVL = (AVL) left;
		lAVL.leftTurn();
		rightTurn();
	}
}

