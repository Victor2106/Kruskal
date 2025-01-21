package aa.course.struct;

public class Edge {
	private final int source;
	private final int destination;
	private final int weight;

	public Edge(int src, int dest, int cost) {
		this.source = src;
		this.destination = dest;
		this.weight = cost;
	}

	public int getSource() { return source; }
	public int getDestination()   { return destination; }
	public int getWeight()  { return weight; }

	@Override
	public String toString() {
		int x = Math.min(source, destination);
		int y = Math.max(source, destination);
		return "(" + x + " -> " + y + " : " + weight + ")";
	}
}