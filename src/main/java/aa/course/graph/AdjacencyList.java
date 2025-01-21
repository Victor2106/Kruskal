package aa.course.graph;

import aa.course.struct.CustomDoubleLinkedList;
import aa.course.struct.ListEdges;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AdjacencyList {
	private int nbVertices;
	private CustomDoubleLinkedList[] adj;

	public AdjacencyList() {
		nbVertices = 0;
		adj = null;
	}

	public void load(String file) {
		try {
			Scanner sc = new Scanner(new File(file));
			nbVertices = sc.nextInt();
			adj = new CustomDoubleLinkedList[nbVertices + 1];
			for (int i = 1; i <= nbVertices; i++){
				adj[i] = new CustomDoubleLinkedList();
			}
			while(sc.hasNext()) {
				int x = sc.nextInt();
				if(x == 0) break;
				while(true) {
					int y = sc.nextInt();
					if (y == 0) {
						break;
					}
					int cost = sc.nextInt();
					int big = Math.max(x, y);

					adj[big].add(y, cost, x);
				}
			}
			sc.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public int getNbVertices() {
		return nbVertices;
	}

	public ListEdges getEdges() {
		ListEdges la = new ListEdges();
		for (int i = 1; i <= nbVertices; i++) {
			adj[i].resetIteration();
			while (adj[i].hasNext()) {
				int v = adj[i].getNeighbor();
				int c = adj[i].getCost();
				int other = adj[i].getOther();
				la.addEdge(other, v, c);
				adj[i].next();
			}
		}
		return la;
	}
}
