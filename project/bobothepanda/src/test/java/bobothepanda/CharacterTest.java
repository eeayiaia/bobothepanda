package bobothepanda;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import model.AbstractMapObject;
import model.Gravity;
import model.Key;
import model.Terrain;
import model.Character;
import model.AbstractFixedObject;
import model.IMapObject;
import model.Position;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
public class CharacterTest extends Assert {
	private Character character;
	private Position position = new Position(3f,3f);
	private final Size size = new Size(5f,5f);
	private final int delta = 17;
	
	@Before
	public void setUp() throws Exception {
		final AbstractMapObject terrain = new Terrain(new Position(1f,1f), new Size(5f,5f));
		final List <AbstractMapObject> list = new ArrayList <AbstractMapObject>();
		list.add(terrain);
		character = new Character(position, size);
	}
	
	@Test
	public void testGetPosition() {
		assertEquals(position, character.getPosition());
	}
	
	@Test
	public void testGetSize() {
		assertEquals(size, character.getSize());
	}
	
	@Test
	public void testGetHitbox() {
		final Rectangle hitbox = new Rectangle((int)Math.round(position.getX()),(int)Math.round(position.getY()),
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
		assertEquals(hitbox, character.getHitbox());
	}
	
	@Test
	public void testSetX() {
		position.setX(5f);
		character.setX(position.getX());
		assertEquals(position.getX(), character.getPosition().getX(), 0);
	}
	
	@Test
	public void testSetY() {
		position.setY(5f);
		character.setY(position.getY());
		assertEquals(position.getY(), character.getPosition().getY(), 0);
	}
	
	@Test
	public void testSetPosition() {
		position = new Position(1f,1f);
		character.setPosition(position);
		assertEquals(position, character.getPosition());
	}
	
	@Test(expected = NullPointerException.class)
	public void testSetPositionNull() {
		position = null;
		character.setPosition(position);
	}
	
	@Test
	public void testGetKeyPickedUpFalse() {
		assertEquals(false, character.getKeyPickedUp());
	}
	
	@Test
	public void testGetKeyPickedUpTrue() {
		Key key = new Key(position, new Size(20f,20f));
		character.visit(key);
		assertEquals(true, character.getKeyPickedUp());
	}
	
	@Test
	public void testSetYVelocity() {
		float yVelocity = 2f;
		character.setYVelocity(yVelocity);
		assertEquals(yVelocity, character.getYVelocity(),0);
	}

//	@Test
//	public void testMoveLeft() {
//		character.moveLeft(delta); 
//		assertTrue(0 > character.getXVelocity());
//	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveLeftNegativeDelta() {
		character.moveLeft(-delta);
	}
	
//	@Test
//	public void testMoveRight() {
//		character.moveRight(delta);
//		assertTrue(0 < character.getXVelocity());
//	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveRightNegativeDelta() {
		character.moveRight(-delta);
	}
	
	@Test
	public void testJump() {
		Terrain terrain = new Terrain(position, size);
		terrain.setY(position.getY()+1);
		character.visit(terrain);
		character.jump(delta);
		assertEquals(-1.7f, character.getYVelocity(), 0.0001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testJumpNegativeDelta() {
		character.jump(-delta);
	}
	
	@Test
	public void testJumpNotOnGround() {
		float yVelocity = character.getYVelocity();
		character.jump(delta);
		assertEquals(yVelocity, character.getYVelocity(), 0.0001);
	}
	
	@Test
	public void testApplyGravityYVelocity() {
		Gravity gravity = new Gravity (0.01f);
		float yVelocity = character.getYVelocity();
		float newYVelocity = gravity.getNewVelocity(yVelocity, delta);
		character.applyGravity(delta);
		assertEquals(newYVelocity, character.getYVelocity(), 0.0001);
	}
	
	@Test
	public void testApplyGravityPosition() {
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testApplyGravityNegativeDelta() {
		character.applyGravity(-delta);
	}
	
}
