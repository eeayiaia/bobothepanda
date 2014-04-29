package bobothepanda;
/**
 * Test class for Size
 * @author Elvira Jonsson
 */
import static org.junit.Assert.*;
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

}
