package bobothepanda;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
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

@SuppressWarnings("PMD.TooManyMethods")//Everything that should be tested, is tested
public class LevelTest extends Assert {
	
	private Level level;
	private Character character;
	private boolean eventReceived;
	
	@Before
	public void setUp(){
		this.character = new Character(new Position(10f,10f), new Size(10f,10f));
		final List <AbstractMapObject> abstractMapObjects = new ArrayList<AbstractMapObject>();
		abstractMapObjects.add(new MovingEnemy(new Position(10f,10f), new Size(10f,10f)));
		abstractMapObjects.add(new Terrain(new Position(10f,10f), new Size(10f,10f)));
		this.level = new Level(abstractMapObjects, character);
		
	}
	
	@Test
	public void testCollisionSameRectangle(){
		final Rectangle rec = new Rectangle(10, 10);
		assertFalse(level.collision(rec, rec));
	}
	
	@Test
	public void testCollisionDifferentRectanglesIntersecting(){
		final Rectangle rec1 = new Rectangle(10,10);
		final Rectangle rec2 = new Rectangle(10, 11);
		assertTrue(level.collision(rec1, rec2));
	}
	
	@Test
	public void testCollisionNull(){
		assertFalse(level.collision(null, null));
	}
	
	@Test
	public void testCollisionOneNull(){
		final Rectangle rec1 = new Rectangle(10,10);
		assertFalse(level.collision(rec1, null));
	}
	
	
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	@Test
	public void testUpdate(){
		final List<AbstractMapObject> list = new ArrayList<AbstractMapObject>();
		final MovingEnemy movingEnemy = new MovingEnemy(new Position(0f,0f), new Size(10f,10f)); 
		list.add(movingEnemy);
		list.add(new Terrain(new Position(15f,15f), new Size(10f,10f)));
		final Level level1 = new Level(list, character);
		final float initialVelocity = movingEnemy.getVelocity();
		level1.update();
		//if there is no collision, the movingEnemy should NOT have reversed its velocity
		assertEquals(initialVelocity, movingEnemy.getVelocity(), 0f);
	}
	
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	@Test
	public void testCheckCollisionsNoCollisions(){
		final List<AbstractMapObject> list = new ArrayList<AbstractMapObject>();
		final MovingEnemy movingEnemy = new MovingEnemy(new Position(0f,0f), new Size(10f,10f)); 
		list.add(movingEnemy);
		list.add(new Terrain(new Position(15f,15f), new Size(10f,10f)));
		final Level level1 = new Level(list, character);
		final float initialVelocity = movingEnemy.getVelocity();
		level1.checkCollisions();
		//if there is no collision, the movingEnemy should NOT have reversed its velocity
		assertEquals(initialVelocity, movingEnemy.getVelocity(), 0f);
	}
	
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	@Test
	public void testCheckCollisionsWithCollisions(){
		final List<AbstractMapObject> list = new ArrayList<AbstractMapObject>();
		final MovingEnemy movingEnemy = new MovingEnemy(new Position(10f,10f), new Size(10f,10f)); 
		list.add(movingEnemy);
		list.add(new Terrain(new Position(7f,7f), new Size(10f,10f)));
		final Level level1 = new Level(list, character);
		final float initialVelocity = movingEnemy.getVelocity();
		level1.checkCollisions();
		//if there is  collision, the movingEnemy should have reversed its velocity
		assertNotEquals(initialVelocity, movingEnemy.getVelocity(), 0f);
	}
	
	@Test
	public void testPropertyChangeLoadLevel(){
		final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(level);
		level.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("LOAD_LEVEL".equals(evt.getPropertyName())) {
			    	eventReceived = true;
			    }	
		   }
		});
		eventReceived = false;
		pcs.firePropertyChange("LOAD_LEVEL", null, null);
		assertTrue(eventReceived);
	}
	
	@Test
	public void testPropertyChangeReLoadLevel(){
		final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
		pcs.addPropertyChangeListener(level);
		level.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("RELOAD_LEVEL".equals(evt.getPropertyName())) {
			    	eventReceived = true;
			    }
		   }
		});
		eventReceived = false;
		pcs.firePropertyChange("RELOAD_LEVEL", null, null);
		assertTrue(eventReceived);
	}
	
	
	
}
