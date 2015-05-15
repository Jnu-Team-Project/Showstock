package com.jnu.stock;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.jnu.stock.Getstockinfnow;


public class GetstockinfnowTest 
{
	
	Getstockinfnow stocktest = new Getstockinfnow();
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetinf() 
	{
		
		assertEquals("24.63", (stocktest.getinf("sz000004"))[1]); 
	}
	
	

}
