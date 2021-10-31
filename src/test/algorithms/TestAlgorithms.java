package test.algorithms;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

import algortihms.Algorithms;

public class TestAlgorithms {

	@Test
	public void factorial() {
		assertEquals(720, Algorithms.factorial(6));
		assertEquals(1, Algorithms.factorial(0));
	}
	
	@Test
	public void factorialRec() {
		assertEquals(720, Algorithms.factorialRec(6));
		assertEquals(1, Algorithms.factorialRec(0));
	}

	@Test
	public void pow() {
		assertEquals(1099511627776L, Algorithms.pow(40));
	}
	
	@Test
	public void powRec1() {
		//assertEquals(1099511627776L, Algorithms.powRec1(40));
	}
	
	@Test
	public void powRec2() {
		assertEquals(1099511627776L, Algorithms.powRec2(40));
	}
	
	@Test
	public void powRec3() {
		assertEquals(1099511627776L, Algorithms.powRec3(40));
	}
	
	@Test
	public void powRec4() {
		assertEquals(1099511627776L, Algorithms.powRec4(40));
	}
	
}
