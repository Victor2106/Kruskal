package aa.course.graph;

import aa.course.struct.ListEdges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdjacencyMatrix {
	private int nbVertices;
	private int[][] matrix;

	public AdjacencyMatrix() {
		nbVertices = 0;
		matrix = null;
	}

	public void load(String file) {
		try {
			Scanner sc = new Scanner(new File(file));
			nbVertices = sc.nextInt();
			matrix = new int[nbVertices+1][nbVertices+1];

			while(sc.hasNext()) {
				int x = sc.nextInt();
				if (x == 0) break;
				while(true){
					int y = sc.nextInt();
					if(y == 0) break;
					int c = sc.nextInt();
					int big = Math.max(x,y);
					int small = Math.min(x,y);
					matrix[big][small] = c;
					matrix[small][big] = 0;
				}
			}
			sc.close();
		} catch(FileNotFoundException e){
			e.printStackTrace();
		}
	}

	public int getNbVertices() {
		return nbVertices;
	}

	public ListEdges getEdges() {
		ListEdges la = new ListEdges();
		for (int i = 1; i <= nbVertices; i++) {
			for(int j = 1; j < i; j++) {
				int c = matrix[i][j];
				if(c > 0){
					la.addEdge(i, j, c);
				}
			}
		}
		return la;
	}
}
