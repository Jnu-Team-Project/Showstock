import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;


public class AddnewstockTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testAdd1() {
		Addnewstock ns = new Addnewstock();
		
		assertEquals("��ũ�Ƽ�",ns.Searchstock("sz000004"));
	}
	@Test
	public void testAddsheet() 
	{
		Addnewstock ns = new Addnewstock();
		ns.stockname="��������";
		assertEquals("��������",ns.Addsheet("mouse"));
	}

}
