import static org.junit.Assert.*;

import java.util.Vector;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class DealdialogTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAdddealtosheet() 
	{
		Dealdialog dl = new Dealdialog();
		dl.dealdialogusername = "mouse";
		dl.dealdialogname = "伊利股份";	
		assertEquals("3",dl.Adddealtosheet("2015/3/11","卖出", "4.5", "50", "1","0.3"));
	}
	@Ignore
	@Test(expected = Exception.class)
	public void testGethistorydata2() 
	{
		Dealdialog dl = new Dealdialog();
		dl.dealdialogusername = "mouse";
		dl.dealdialogname = "伊利股份";	
		assertEquals("3",dl.Adddealtosheet("2015/3/11","卖出", "4.5", "50", "1","0.3"));
	}
}
