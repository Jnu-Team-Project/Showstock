package com.jnu.stock;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ModifyTableTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testModifyTable() {
		ModifyTable mt = new ModifyTable("mouse",0);
		int[] test = mt.SetTable();
		assertEquals(100,test[0]);
	}
}

