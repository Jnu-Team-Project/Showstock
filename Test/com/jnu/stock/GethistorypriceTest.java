package com.jnu.stock;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jnu.stock.Gethistoryprice;


public class GethistorypriceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGethistoryprice() 
	{
		Gethistoryprice gt = new Gethistoryprice();
		String price = gt.Getprice("sh600887","2015/03/8");
		//System.out.print("**"+price+" ");
		assertEquals("0",price);
	}

}
