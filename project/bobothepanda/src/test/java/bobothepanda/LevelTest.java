package bobothepanda;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

import model.AbstractMapObject;
import model.Level;
import model.Character;
import model.MovingEnemy;
import model.Position;
import model.Size;
import model.Terrain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LevelTest extends Assert {
	
	Level level;
	Character character;
	List <AbstractMapObject> abstractMapObjects;
	
	@Before
	public void setUp(){
		this.character = new Character(new Position(10f,10f), new Size(10f,10f));
		this.abstractMapObjects = new ArrayList<AbstractMapObject>();
		abstractMapObjects.add(new MovingEnemy(new Position(10f,10f), new Size(10f,10f)));
		abstractMapObjects.add(new Terrain(new Position(10f,10f), new Size(10f,10f)));
		this.level = new Level(abstractMapObjects, character);
		
	}
	
	@Test
	public void testCollisionSameRectangle(){
		Rectangle rec = new Rectangle(10, 10);
		assertFalse(level.collision(rec, rec));
	}
	
	
}
