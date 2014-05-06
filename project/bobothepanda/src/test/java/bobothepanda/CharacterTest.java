package bobothepanda;

import java.util.ArrayList;
import java.util.List;

import model.Character;
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
	private IMapObject terrain;
	private final List <IMapObject> list = new ArrayList <IMapObject>();
	private Character character;
	Position position;
	
	@Before
	public void setUp() throws Exception {
		position = new Position(3f,3f);
		terrain = new MapObject(new Position(1f,1f), new Size(5f,5f), ObjectType.TERRAIN);
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
}
