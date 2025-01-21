package aa.course.kruskal;

import aa.course.graph.AdjacencyMatrix;

public class KruskalM extends Kruskal {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("Utilisation: java KruskalM <fichier> [fichier_sortie]");
			return;
		}

		String filename = args[0];
		String outFilename = (args.length >= 2) ? args[1] : null;

		AdjacencyMatrix g = new AdjacencyMatrix();
		g.load(filename);
		int n = g.getNbVertices();

		if (n <= 0) {
			System.out.println("Graphe vide ou invalide.");
			return;
		}

		new KruskalM().processGraph(outFilename, n, g.getEdges());
	}
}