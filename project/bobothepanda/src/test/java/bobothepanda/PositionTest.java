package bobothepanda;

import model.Position;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PositionTest extends Assert {
	private Position position;

	@Before
	public void setUp() {
		position = new Position(3.5f, 4.5f);
	}
	
	@Test
	public void testGetX() {
		assertEquals(position.getX(), 3.5f, 0);
	}

	@Test
	public void testGetY() {
		assertEquals(position.getY(), 4.5f, 0);
	}

	@Test
	public void testSetX() {
		position.setX(6.7f);
		assertEquals(position.getX(), 6.7f, 0);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testSetXException(){
		position.setX(-2f);
	}

	@Test
	public void testSetY() {
		position.setY(100.1f);
		assertEquals(position.getY(), 100.1f, 0);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetYException(){
		position.setY(-0.1f);
	}
}
