package bobothepanda;

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
	
	MovingEnemy movingEnemy;
	float velocity;
	
	@Before
	public void setUp(){
		movingEnemy = new MovingEnemy(new Position(1f,1f), new Size(10,10));
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
		velocity = movingEnemy.getVelocity();
		movingEnemy.visit(movingEnemy1);
		assertTrue(movingEnemy.getVelocity() < velocity);
	}
	
	
	@Test
	public void testVisitDoor(){
		final float velocity = movingEnemy.getVelocity() * -1;
		movingEnemy.visit(new Door(new Position(1f,1f), new Size(10,10)));
		assertEquals(velocity, movingEnemy.getVelocity(), 0f);
	}
	@Test
	public void testVisitShootingEnemy(){
		final float velocity = movingEnemy.getVelocity() * -1;
		movingEnemy.visit(new ShootingEnemy(new Position(1f,1f), new Size(10,10)));
		assertEquals(velocity, movingEnemy.getVelocity(), 0f);
	}
	@Test
	public void testVisitFixedEnemy(){
		final float velocity = movingEnemy.getVelocity() * -1;
		movingEnemy.visit(new FixedEnemy(new Position(1f,1f), new Size(10,10)));
		assertEquals(velocity, movingEnemy.getVelocity(), 0f);
	}
	@Test
	public void testVisitTerrain(){
		final float velocity = movingEnemy.getVelocity() * -1;
		movingEnemy.visit(new Terrain(new Position(1f,1f), new Size(10,10)));
		assertEquals(velocity, movingEnemy.getVelocity(), 0f);
	}
}
