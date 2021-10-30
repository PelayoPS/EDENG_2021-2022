package test;

import static org.junit.Assert.*;

import org.junit.Test;

import trees.BSTree;

public class BSTreeTest {

	@Test
	public void testAdd() throws Exception {
		BSTree<Character> a = new BSTree<Character>();

		a.add('b');
		assertEquals ("b--", a.toString());
		a.add('a');
		assertEquals ("ba---", a.toString());
		a.add('d');
		assertEquals ("ba--d--", a.toString());
		a.add('e');
		assertEquals ("ba--d-e--", a.toString());
		a.add('f');
		assertEquals ("ba--d-e-f--", a.toString());
		a.add('c');
		assertEquals ("ba--dc--e-f--", a.toString());
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
