
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.eundms.tree.BinaryIndexedTree;

class BinaryIndexedTreeTest {

	@Test
	void testBasicSumQueries() {
		BinaryIndexedTree bit = new BinaryIndexedTree(10);
		for (int i = 1; i <= 10; i++) {
			bit.update(i, i);
		}

		assertEquals(55, bit.query(10)); // 1+2+...+10
		assertEquals(15, bit.query(5));  // 1+2+3+4+5
		assertEquals(5, bit.rangeQuery(2, 3)); // 2+3
	}

	@Test
	void testUpdateAndQuery() {
		BinaryIndexedTree bit = new BinaryIndexedTree(10);
		for (int i = 1; i <= 10; i++) {
			bit.update(i, i); // 1~10
		}

		// update index 5 by +5 → arr[5] = 10
		bit.update(5, 5);

		assertEquals(20, bit.query(5)); // 1+2+3+4+10
		assertEquals(60, bit.query(10)); // 전체 합 = 55 + 5 = 60
	}

	@Test
	void testRangeQuery() {
		BinaryIndexedTree bit = new BinaryIndexedTree(10);
		for (int i = 1; i <= 10; i++) {
			bit.update(i, i); // 1~10
		}

		assertEquals(5, bit.rangeQuery(2, 3)); // 2+3
		assertEquals(12, bit.rangeQuery(3, 5)); // 3+4+5 = 12
		assertEquals(10, bit.rangeQuery(10, 10)); // 10
	}
}

