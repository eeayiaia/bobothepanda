package bobothepanda;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import model.AbstractMapObject;
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
	
	public void testSetY() {
		position.setY(5f);
		character.setY(position.getY());
		assertEquals(position.getY(), character.getPosition().getY(), 0);
	}
	
	public void testSetPosition() {
		position = new Position(1f,1f);
		character.setPosition(position);
		assertEquals(position, character.getPosition());
	}
	
	
//	@Test
	public void testMoveRightFacingRight(){
		character.moveRight(10);
		assertSame(Character.Facing.RIGHT, character.getFacing());
	}
	
//	@Test
	public void testMoveRightCharacterState(){
		character.moveRight(10);
		assertSame(Character.CharacterState.MOVING_RIGHT, character.getCharacterState());
	}
	
//	@Test
	public void testMoveLeftFacingLeft(){
		character.moveLeft(10);
		assertSame(Character.Facing.LEFT, character.getFacing());
	}
	
//	@Test
	public void testMoveLeftCharacterState(){
		character.moveLeft(10);
		assertSame(Character.CharacterState.MOVING_LEFT, character.getCharacterState());
	}
}
