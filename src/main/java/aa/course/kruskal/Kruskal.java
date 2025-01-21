package aa.course.kruskal;

import aa.course.struct.Edge;
import aa.course.struct.ListEdges;
import aa.course.struct.MySort;
import aa.course.struct.UnionFind;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Kruskal {
	public static ListEdges calculateMST (int nbVertices, ListEdges edges) {
		MySort.sort(edges);
		UnionFind uf = new UnionFind(nbVertices + 1);
		ListEdges result = new ListEdges();

		edges.reset();
		while (edges.hasNext()) {
			Edge a = edges.next();
			int ra = uf.find(a.getSource());
			int rb = uf.find(a.getDestination());
			if (ra != rb) {
				result.addEdge(a.getSource(), a.getDestination(), a.getWeight());
				uf.fusion(ra, rb);
			}
		}
		return result;
	}

	protected void processGraph(String outFilename, int n, ListEdges edges) {
		long debut = System.nanoTime();
		ListEdges mst = calculateMST(n, edges);
		long fin = System.nanoTime();

		UnionFind ufComp = new UnionFind(n + 1);
		mst.reset();
		while (mst.hasNext()) {
			Edge e = mst.next();
			ufComp.fusion(e.getSource(), e.getDestination());
		}

		ListEdges[] components = new ListEdges[n + 1];
		for (int i = 0; i <= n; i++) {
			components[i] = new ListEdges();
		}
		edges.reset();
		while (edges.hasNext()) {
			Edge e = edges.next();
			int rep = ufComp.find(e.getSource());
			components[rep].addEdge(e.getSource(), e.getDestination(), e.getWeight());
		}

		boolean connexe = (mst.getSize() == n - 1);

		StringBuilder sb = new StringBuilder();
		sb.append(connexe ? "LE GRAPHE EST CONNEXE\n" : "LE GRAPHE N'EST PAS CONNEXE\n");
		sb.append("Cout total: ").append(mst.totalWeight()).append("\n\n");

		int componentIndex = 1;
		for (int i = 1; i <= n; i++) {
			if (components[i].getSize() > 0) {
				sb.append("Composante ").append(componentIndex++).append(" :\n");
				components[i].reset();
				while (components[i].hasNext()) {
					sb.append("  ").append(components[i].next().toString()).append("\n");
				}
				sb.append("\n");
			}
		}

		sb.append("Temps: ").append((fin - debut) / 1_000_000.0).append(" ms\n");

		if (outFilename != null) {
			try (PrintWriter pw = new PrintWriter(new FileWriter(outFilename))) {
				pw.write(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println(sb.toString());
		}
	}
}