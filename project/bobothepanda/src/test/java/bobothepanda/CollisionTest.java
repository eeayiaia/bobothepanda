package bobothepanda;


import java.util.ArrayList;
import java.util.List;

import model.Collision;
import model.Character;
import model.IMapObject;
import model.MapObject;
import model.ObjectType;
import model.Position;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CollisionTest extends Assert{
	private Collision collision;
	private Character character;
	private IMapObject terrain;
	private final List <IMapObject> list = new ArrayList <IMapObject>();

	@Before
	public void setUp() throws Exception {
		terrain = new MapObject(new Position(1f,1f), new Size(5f,5f), ObjectType.TERRAIN);
		list.add(terrain);
		collision = new Collision(list);
		character = new Character(new Position(3f,3f), collision);
	}

	@Test
	public void testCollidedWith() {
		assertTrue(collision.collidedWith(character.getHitbox()).equals(terrain.getHitbox()));
	}

	@Test
	public void testGetObjectType() {
		collision.collidedWith(character.getHitbox());
		assertSame(collision.getObjectType(),ObjectType.TERRAIN);
	}
	@Test
	public void testGetObjectTypeNull(){
		assertNull(collision.getObjectType());
	}

	@Test
	public void testGetObjectPosition() {
		collision.collidedWith(character.getHitbox());
		assertTrue(collision.getObjectPosition().equals(new Position(1f,1f)));
	}

}
