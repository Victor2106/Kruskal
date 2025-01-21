package aa.course.struct;

public class ListEdges {
	private static final int CAPACITY_INT = 10;
	private Edge[] tab;
	private int size;
	private int indexLecture;

	public ListEdges() {
		tab = new Edge[CAPACITY_INT];
		size = 0;
		indexLecture = 0;
	}

	private void expand() {
		Edge[] nouveau = new Edge[tab.length * 2];
		for (int i = 0; i < size; i++){
			nouveau[i] = tab[i];
		}
		tab = nouveau;
	}

	public void addEdge(int debut, int fin, int cout) {
		if(size == tab.length) {
			expand();
		}
		tab[size++] = new Edge(debut, fin, cout);
	}

	public void reset() {
		indexLecture = 0;
	}

	public boolean hasNext() {
		return indexLecture < size;
	}

	public Edge next() {
		return tab[indexLecture++];
	}

	public int getSize() {
		return size;
	}

	public int totalWeight() {
		int somme = 0;
		for(int i = 0; i < size; i++){
			somme += tab[i].getWeight();
		}
		return somme;
	}

	public void clear() {
		size = 0;
		indexLecture = 0;
	}

	public Edge getHas(int i) {
		return tab[i];
	}
}