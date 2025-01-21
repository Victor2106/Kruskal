package aa.course.struct;

public class MySort {
	public static void sort(ListEdges list) {
		int n = list.getSize();
		Edge[] arr = new Edge[n];

		list.reset();
		for (int i = 0; i < n; i++) {
			arr[i] = list.next();
		}

		quicksort(arr, 0, n - 1);

		list.clear();
		for (Edge a : arr) {
			list.addEdge(a.getSource(), a.getDestination(), a.getWeight());
		}
	}

	private static void quicksort(Edge[] tab, int g, int d) {
		if (g < d) {
			int p = partition(tab, g, d);
			quicksort(tab, g, p - 1);
			quicksort(tab, p + 1, d);
		}
	}

	private static int partition(Edge[] tab, int g, int d) {
		Edge pivot = tab[d];
		int i = g - 1;
		for (int j = g; j < d; j++) {
			if (tab[j].getWeight() <= pivot.getWeight()) {
				i++;
				swap(tab, i, j);
			}
		}
		swap(tab, i + 1, d);
		return i + 1;
	}

	private static void swap(Edge[] tab, int i, int j) {
		Edge tmp = tab[i];
		tab[i] = tab[j];
		tab[j] = tmp;
	}
}