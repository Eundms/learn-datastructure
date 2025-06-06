import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.eundms.tree.SegmentTree;

public class SegmentTreeTest {

	@Test
	void testInitialQuery() {
		long[] arr = {1, 2, 3, 4, 5};
		SegmentTree st = new SegmentTree(arr);

		assertEquals(15, st.query(0, 4));  // 전체 합
		assertEquals(6, st.query(0, 2));   // 1 + 2 + 3
		assertEquals(9, st.query(1, 3));   // 2 + 3 + 4
		assertEquals(5, st.query(4, 4));   // 단일 원소
	}

	@Test
	void testUpdateAndQuery() {
		long[] arr = {1, 2, 3, 4, 5};
		SegmentTree st = new SegmentTree(arr);

		// arr[2] = 10으로 변경
		st.update(2, 10, arr);

		assertEquals(22, st.query(0, 4));  // 1 + 2 + 10 + 4 + 5
		assertEquals(13, st.query(0, 2));  // 1 + 2 + 10
		assertEquals(19, st.query(2, 4));  // 10 + 4 + 5
		assertEquals(10, st.query(2, 2));  // 단일 값
	}

}
