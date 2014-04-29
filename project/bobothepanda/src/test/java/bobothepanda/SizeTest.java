package bobothepanda;
/**
 * Test class for Size
 * @author Elvira Jonsson
 */
import static org.junit.Assert.*;
import model.Size;

import org.junit.Before;
import org.junit.Test;

public class SizeTest {
	private Size size;

	@Before
	public void setUp() throws Exception {
		size = new Size(10f, -9.5f);
	}

	@Test
	public void testGetWidth() {
		assertTrue(size.getWidth() == 10f);
	}

	@Test
	public void testGetHeight() {
		assertTrue(size.getHeight() == 9.5f);
	}

	@Test
	public void testGetArea() {
		assertTrue(size.getArea() == (size.getHeight()*size.getWidth()));
	}

}
