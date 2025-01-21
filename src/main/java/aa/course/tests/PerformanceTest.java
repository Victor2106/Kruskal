package aa.course.tests;

import aa.course.graph.AdjacencyList;
import aa.course.graph.AdjacencyMatrix;
import aa.course.kruskal.Kruskal;
import aa.course.struct.ListEdges;

import java.io.File;

public class PerformanceTest {
	public static void main(String[] args) {
		String parentDir = "src/main/java/aa/course/tests";

		String[] subDirs = {"complete", "random", "connected", "nonconnected"};

		for (String sub : subDirs) {
			File folder = new File(parentDir + File.separator + sub);
			if (!folder.exists() || !folder.isDirectory()) {
				System.err.println("Répertoire introuvable: " + folder.getAbsolutePath());
				continue;
			}

			System.out.println("===== Dossier: " + sub + " =====");

			File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));
			if (files == null || files.length == 0) {
				System.out.println("Aucun fichier .txt dans " + folder.getAbsolutePath());
				continue;
			}

			for (File f : files) {
				String filePath = f.getAbsolutePath();
				System.out.println("Fichier: " + f.getName());

				AdjacencyList gl = new AdjacencyList();
				gl.load(filePath);
				int nListe = gl.getNbVertices();

				ListEdges edgesListe = gl.getEdges();

				long startList = System.nanoTime();
				ListEdges mstListe = Kruskal.calculateMST(nListe, edgesListe);
				long endList = System.nanoTime();
				double timeListMs = (endList - startList) / 1_000_000.0;

				AdjacencyMatrix gm = new AdjacencyMatrix();
				gm.load(filePath);
				int nMatrice = gm.getNbVertices();

				ListEdges edgesMatrice = gm.getEdges();

				long startMatrix = System.nanoTime();
				ListEdges mstMatrix = Kruskal.calculateMST(nMatrice, edgesMatrice);
				long endMatrix = System.nanoTime();
				double timeMatrixMs = (endMatrix - startMatrix) / 1_000_000.0;

				System.out.printf("  - n=%d\n", nListe);
				System.out.printf("  - Adjacence liste  : %.3f ms\n", timeListMs);
				System.out.printf("  - Adjacence matrice: %.3f ms\n", timeMatrixMs);
				System.out.println("----------------------------------");
			}
			System.out.println();
		}
	}
}