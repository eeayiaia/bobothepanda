package bobothepanda;

import model.MovingEnemy;
import model.Position;
import model.Size;
import model.Character;

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
}
