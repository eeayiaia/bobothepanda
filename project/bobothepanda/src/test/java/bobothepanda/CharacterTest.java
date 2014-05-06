package bobothepanda;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import model.Character;
import model.Character.CharacterState;
import model.Collision;
import model.IMapObject;
import model.MapObject;
import model.ObjectType;
import model.Position;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CharacterTest extends Assert {
	private Collision collision;
	private Character character;
	Position position = new Position(3f,3f);
	
	@Before
	public void setUp() throws Exception {
		IMapObject terrain = new MapObject(new Position(1f,1f), new Size(5f,5f), ObjectType.TERRAIN);
		final List <IMapObject> list = new ArrayList <IMapObject>();
		list.add(terrain);
		collision = new Collision(list);
		character = new Character(position, collision);
	}
	
	@Test
	public void testGetPosition(){
		assertTrue(position.equals(character.getPosition()));
	}
	
	@Test
	public void validateOnGround(){
		assertTrue(character.onGround());
	}
	
	@Test
	public void validateNotOnGround(){
		assertFalse(new Character(new Position(8f, 8f), collision).onGround());
	}
	
	@Test
	public void testGetHitbox(){
		Size size = new Size(18,30);
		Rectangle hitbox = new Rectangle((int)Math.round(position.getX()),(int)Math.round(position.getY()),
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
		assertEquals(hitbox, character.getHitbox());

	}
	
	@Test
	public void testMoveRightwithCollision(){
		Position oldPosition = character.getPosition();
		character.moveRight(100);
		assertEquals(oldPosition, character.getPosition());;
	}
	
	
}
