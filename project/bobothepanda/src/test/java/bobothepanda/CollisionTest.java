package bobothepanda;


import java.util.ArrayList;
import java.util.List;

import model.Collision;
import model.Character;
import model.AbstractMapObject;
import model.Position;
import model.Size;
import model.Terrain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CollisionTest extends Assert{
	private Collision collision;
	private Character character;
	private final List <AbstractMapObject> list = new ArrayList <AbstractMapObject>();


	@Before
	public void setUp() throws Exception {
		collision = new Collision(list);
		character = new Character(new Position(3f,3f), collision);
	}

	@Test
	public void testCollidedWith(){
		final AbstractMapObject terrain = new Terrain(new Position(1f,1f), new Size(5f,5f));
		list.add(terrain);
		assertTrue(collision.collidedWith(character.getHitbox()).equals(terrain.getHitbox()));
	}
	
	@Test
	public void testCollidedWithNull() {
		assertNull(collision.collidedWith(null));
	}

	@Test
	public void testGetObjectType() {
		final AbstractMapObject terrain = new Terrain(new Position(1f,1f), new Size(5f,5f));
		list.add(terrain);
		collision.collidedWith(character.getHitbox());
		assertSame(terrain, collision.getObjectType());
	}
	@Test
	public void testGetObjectTypeNull(){
		assertNull(collision.getObjectType());
	}

	@Test
	public void testGetObjectPosition() {
		final AbstractMapObject terrain = new Terrain(new Position(1f,1f), new Size(5f,5f));
		list.add(terrain);
		collision.collidedWith(character.getHitbox());
		assertTrue(collision.getObjectPosition().equals(new Position(1f,1f)));
	}
	
	@Test
	public void testGetObjectPositionNull(){
		assertNull(collision.getObjectPosition());
	}
}
