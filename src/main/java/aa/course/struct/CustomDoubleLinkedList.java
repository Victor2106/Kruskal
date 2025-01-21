package aa.course.struct;

public class CustomDoubleLinkedList {
	private static class Node {
		int neighbor;
		int cost;
		int other;
		Node next;
		Node previous;

		Node(int n, int c, int o) {
			neighbor = n;
			cost = c;
			other = o;
			next = null;
			previous = null;
		}
	}

	private Node head;
	private Node tail;
	private int size;

	private Node current;

	public CustomDoubleLinkedList() {
		head = null;
		tail = null;
		size = 0;
		current = null;
	}

	public void add(int n, int c, int o) {
		Node node = new Node(n, c, o);
		if (head == null) {
			head = node;
			tail = node;
		} else {
			tail.next = node;
			node.previous = tail;
			tail = node;
		}
		size++;
	}

	public void resetIteration() {
		current = head;
	}

	public boolean hasNext() {
		return (current != null);
	}

	public int getNeighbor() {
		return current.neighbor;
	}

	public int getCost() {
		return current.cost;
	}

	public int getOther() {
		return current.other;
	}

	public void next() {
		if (current != null) {
			current = current.next;
		}
	}

	public int getSize() {
		return size;
	}
}