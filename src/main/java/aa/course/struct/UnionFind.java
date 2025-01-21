package aa.course.struct;

public class UnionFind {
	private int[] parent;
	private int[] rank;

	public UnionFind(int size) {
		parent = new int[size];
		rank = new int[size];
		for (int i = 0; i < size; i++) {
			parent[i] = i;
			rank[i] = 0;
		}
	}

	public int find(int x) {
		if (parent[x] != x) {
			parent[x] = find(parent[x]);
		}
		return parent[x];
	}

	public void fusion(int a, int b) {
		int ra = find(a);
		int rb = find(b);
		if (ra != rb) {
			if (rank[ra] < rank[rb]) {
				parent[ra] = rb;
			} else if (rank[ra] > rank[rb]) {
				parent[rb] = ra;
			} else {
				parent[rb] = ra;
				rank[ra]++;
			}
		}
	}
}