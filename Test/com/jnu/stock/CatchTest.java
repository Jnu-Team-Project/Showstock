package com.jnu.stock;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jnu.stock.Catch;


public class CatchTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() 
	{
		Catch cat = new Catch();
		String expect="4.31";
		System.out.print(cat.Gethistoryprice("sh601398","2015/03/05"));
		assertEquals(expect,cat.Gethistoryprice("sh601398","2015/03/05"));
	}
}
