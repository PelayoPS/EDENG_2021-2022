package tree;

import static org.junit.Assert.*;

import org.junit.Test;

import trees.BSTree;

public class BSTreeTest {

	@Test
	public void testAdd() throws Exception {
		BSTree<Character> a = new BSTree<Character>();

		a.add('b');
		assertEquals ("b(0)--", a.toString());
		a.add('a');
		assertEquals ("b(1)a(0)---", a.toString());
		a.add('d');
		assertEquals ("b(1)a(0)--d(0)--", a.toString());
		a.add('e');
		assertEquals ("b(2)a(0)--d(1)-e(0)--", a.toString());
		a.add('f');
		assertEquals ("b(3)a(0)--d(2)-e(1)-f(0)--", a.toString());
		a.add('c');
		assertEquals ("b(3)a(0)--d(2)c(0)--e(1)-f(0)--", a.toString());
		/*
		 *       b
		 *      / \
		 *     a   d
		 *        / \
		 *       c   e
		 *            \
		 *             f	
		 */
	}
	
	@Test
	public void testSearch() throws Exception {
		BSTree<Character> a = new BSTree<Character>();

		a.add('b');
		assertTrue(a.search('b'));
		assertFalse(a.search('a'));
		a.add('a');
		assertTrue(a.search('a'));
		assertFalse(a.search('d'));
		a.add('d');
		assertTrue(a.search('d'));
		assertFalse(a.search('e'));
		a.add('e');
		assertTrue(a.search('e'));
		assertFalse(a.search('f'));
		a.add('f');
		assertTrue(a.search('f'));
		assertFalse(a.search('c'));
		a.add('c');
		assertTrue(a.search('c'));
		/*
		 *       b
		 *      / \
		 *     a   d
		 *        / \
		 *       c   e
		 *            \
		 *             f	
		 */
	}

}
