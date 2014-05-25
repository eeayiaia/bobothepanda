package bobothepanda;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Door;
import model.FixedEnemy;
import model.MovingEnemy;
import model.Position;
import model.ShootingEnemy;
import model.Size;
import model.Character;
import model.Terrain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MovingEnemyTest extends Assert {
	
	private MovingEnemy movingEnemy;
	private float initialVelocity;
	private float reverseInitialVelocity;
	private boolean eventReceived;
	
	@Before
	public void setUp(){
		movingEnemy = new MovingEnemy(new Position(1f,1f), new Size(10,10));
		initialVelocity = movingEnemy.getVelocity();
		reverseInitialVelocity = initialVelocity * -1;
	}
	
	@Test
	public void testRenderMovingLeft(){
		movingEnemy.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("ENEMY_MOVING_LEFT".equals(evt.getPropertyName())) {
			    	eventReceived = true;
			    }	
		   }
		});
		eventReceived = false;
		movingEnemy.visit(new Door(new Position(1f,1f), new Size(10,10)));
		movingEnemy.render();
		assertTrue(eventReceived);
	}
	
	@Test
	public void testRenderMovingRight(){
		movingEnemy.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("ENEMY_MOVING_RIGHT".equals(evt.getPropertyName())) {
			    	eventReceived = true;
			    }	
		   }
		});
		eventReceived = false;
		movingEnemy.render();
		assertTrue(eventReceived);
	}
	
	@Test
	public void testUpdate(){
		final float oldX = movingEnemy.getPosition().getX();
		movingEnemy.update(1000);
		assertNotEquals(oldX, movingEnemy.getPosition().getX());
	}
	
	@Test
	public void testVisitCharacterFromAbove(){
		Character character = new Character(new Position(0f,0f), new Size(11f,11f));
		movingEnemy.visit(character);
		assertTrue(movingEnemy.isDead());
	}	
	
	@Test
	public void testVisitCharacterFromBelow(){
		Character character = new Character(new Position(10f,10f), new Size(11f,11f));
		movingEnemy.visit(character);
		assertFalse(movingEnemy.isDead());
	}
	
	
	@Test
	public void testReverseVelocity(){
		MovingEnemy movingEnemy1 = new MovingEnemy(new Position(1f,1f), new Size(10,10));
		movingEnemy.visit(movingEnemy1);
		assertTrue(movingEnemy.getVelocity() < initialVelocity);
	}
	
	
	@Test
	public void testVisitDoor(){
		movingEnemy.visit(new Door(new Position(1f,1f), new Size(10,10)));
		assertEquals(reverseInitialVelocity, movingEnemy.getVelocity(), 0f);
	}
	@Test
	public void testVisitShootingEnemy(){
		movingEnemy.visit(new ShootingEnemy(new Position(1f,1f), new Size(10,10)));
		assertEquals(reverseInitialVelocity, movingEnemy.getVelocity(), 0f);
	}
	@Test
	public void testVisitFixedEnemy(){
		movingEnemy.visit(new FixedEnemy(new Position(1f,1f), new Size(10,10)));
		assertEquals(reverseInitialVelocity, movingEnemy.getVelocity(), 0f);
	}
	@Test
	public void testVisitTerrain(){
		movingEnemy.visit(new Terrain(new Position(1f,1f), new Size(10,10)));
		assertEquals(reverseInitialVelocity, movingEnemy.getVelocity(), 0f);
	}
}
