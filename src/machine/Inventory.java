package machine;

public class Inventory {
	private Node head;
	private final int maxDepth;
	private int currentNumberOfItemsInTheStack;
	
	public Inventory(int maxDepth) {
		this.maxDepth = maxDepth;
	}
	
	private class Node {
		Product product;
		Node next;
		
		public Node(Product product) {
			this.product = product;
		}
	}
	
	public boolean push(Product product) {
		boolean stackIsNotFull = true;
		if(currentNumberOfItemsInTheStack == maxDepth)
			stackIsNotFull = false;
		else {
			currentNumberOfItemsInTheStack++;
			Node previousNode = head;
			head = new Node(product);
			head.next = previousNode;
		}
		return stackIsNotFull;
	}
	
	public void pop() {
		if(currentNumberOfItemsInTheStack != 0) {
			currentNumberOfItemsInTheStack--;
		}
	}
	
	public int getMaxDepth() {
		return maxDepth;
	}
	
	public int getCurrentDepth() {
		return currentNumberOfItemsInTheStack;
	}
	
	public String peekForProductName() {
		if(head == null)
			return "Empty";
		return head.product.getName();
	}
}
