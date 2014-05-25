package bobothepanda;
/**
 * Test class for Size
 * @author Elvira Jonsson
 */
import model.Position;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SizeTest extends Assert {
	private Size size;

	@Before
	public void setUp() throws Exception {
		size = new Size(10f, -9.5f);
	}

	@Test
	public void testGetWidth() {
		assertEquals(size.getWidth(), 10f, 0);
	}

	@Test
	public void testGetHeight() {
		assertEquals(size.getHeight(), 9.5f, 0);
	}

	@Test
	public void testGetArea() {
		assertEquals(size.getArea(), size.getHeight()*size.getWidth(), 0);
	}
	
	@Test
	public void testHashCode(){
		Size size1 = new Size(size);
		assertNotSame(size1, size);
		assertEquals(size.hashCode(), size1.hashCode(), 0f);
	}
	
	@Test
	public void testEqualsNull(){
		assertFalse(size.equals(null));
	}
	
	@Test
	public void testEqualsOtherObj(){
		assertFalse(size.equals(new Object()));
	}
	
	@Test
	public void testEqualsDiferrentHeight(){
		assertFalse(size.equals(new Size(size.getWidth(), size.getHeight() -1f)));
	}
	
	@Test
	public void testEqualsDiferrentWidth(){
		assertFalse(size.equals(new Size(size.getWidth() -1f, size.getHeight())));
	}
	
	@Test
	public void testEquals(){
		assertTrue(size.equals(new Size(size)));
	}
	
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	@Test(expected = NullPointerException.class)
	public void testConstructorNull(){
			Size size1 = null;
			Size size2 = new Size(size1);
	}
	
	
}
