package bobothepanda;

import model.Position;
import model.Projectile;

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
	public void testConstructorXLessThanZero(){
		Position pos = new Position(-1f, 1f);
		assertEquals(0f, pos.getX(), 0);
	}
	
	@Test
	public void testConstructorYLessThanZero(){
		Position pos = new Position(1f, -1f);
		assertEquals(0f, pos.getY(), 0);
	}
	
	@Test
	public void testConstructorOtherPosX(){
		Position pos = new Position(position);
		assertEquals(position.getX(), pos.getX(), 0f);
	}
	@Test
	public void testConstructorOtherPosY(){
		Position pos = new Position(position);
		assertEquals(position.getY(), pos.getY(), 0f);
	}
	
	@Test(expected = NullPointerException.class)
	public void testConstructorOtherPosNull(){
			Position pos = null;
			new Position(pos);
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
	@Test
	public void testSetXIllegal(){
		position.setX(-2f);
		assertEquals(position.getX(), 0f, 0);
	}

	@Test
	public void testSetY() {
		position.setY(100.1f);
		assertEquals(position.getY(), 100.1f, 0);
	}
	
	@Test
	public void testSetYIllegal(){
		position.setY(-0.1f);
		assertEquals(position.getY(), 0f, 0);
	}	
	
	@Test
	public void testEqualsNull(){
		assertFalse(position.equals(null));
	}
	
	@Test
	public void testEqualsOtherObj(){
		assertFalse(position.equals(new Object()));
	}
	
	@Test
	public void testEqualsDiferrentX(){
		assertFalse(position.equals(new Position(position.getX() -1f, position.getY())));
	}
	
	@Test
	public void testEqualsDiferrentY(){
		assertFalse(position.equals(new Position(position.getX(), position.getY()- 1f)));
	}
	
	@Test
	public void testHashCode(){
		Position pos = new Position(position);
		assertNotSame(pos, position);
		assertEquals(position.hashCode(), pos.hashCode(), 0f);
	}
}
