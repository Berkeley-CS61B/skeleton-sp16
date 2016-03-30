import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;

public class TestArrayHeap {
	@Test
	public void testTenThings() {
		ArrayHeap<String> heap = new ArrayHeap<>();
		heap.insert("c", 3);
		heap.insert("i", 9);
		heap.insert("g", 7);
		heap.insert("d", 4);
		heap.insert("a", 1);
		heap.insert("h", 8);
		heap.insert("e", 5);
		heap.insert("b", 2);
		heap.insert("c", 3);
		heap.insert("d", 4);

		List<Double> sortedItems = new ArrayList<>();

		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());
		sortedItems.add(heap.removeMin().priority());

		assertIsSorted("Failed heap test given to you in the main method.", sortedItems);
	}

	public static void assertIsSorted(String msg, List<? extends Comparable> l) {
		Comparable prev = null;
		for (Comparable item : l) {
			if (prev != null && item.compareTo(prev) < 0) {
				fail(msg);
			}
			prev = item;
		}
	}

	public static void main(String[] args) {
		jh61b.junit.TestRunner.runTests(TestArrayHeap.class);	
	}
} 
