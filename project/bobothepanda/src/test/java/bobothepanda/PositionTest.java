package bobothepanda;

import static org.junit.Assert.*;
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
		assertTrue(position.getX() == 3.5f);
	}

	@Test
	public void testGetY() {
		assertTrue(position.getY() == 4.5f);
	}

	@Test
	public void testSetX() {
		position.setX(6.7f);
		assertTrue(position.getX() == 6.7f);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testSetXException(){
		position.setX(-2f);
	}

	@Test
	public void testSetY() {
		position.setY(100.1f);
		assertTrue(position.getY() == 100.1f);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetYException(){
		position.setY(-0.1f);
	}
	@Test (expected = IllegalArgumentException.class)
	public void testIllegalArgumentException(){
		new Position(-1f, 2f);
	}

}
