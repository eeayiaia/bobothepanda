package bobothepanda;

import model.Gravity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @Author Oscar Muhr
 */

public class GravityTest extends Assert {
	
	Gravity gravity;
	float velocity;
	int delta;
	
	@Before
	public void setUp() {
		gravity = new Gravity(0.15f);
		delta = 17;
	}
	
	@Test
	public void testGetGravity() {
		assertEquals(gravity.getGravity(), 0.15f, 0);
	}
	
	@Test
	public void testGetNewVelocity() {
		velocity = 0.15f;
		
		assertEquals(gravity.getNewVelocity(velocity, delta), velocity+gravity.getGravity()*delta, 0);
	}
	
	@Test
	public void testGetNewVelocityMaxValue() {
		velocity = 0.51f;
		
		assertEquals(gravity.getNewVelocity(velocity, delta), 0.5f, 0);
	}
	
	@Test
	public void testGetNewYPosition() {
		velocity = 0.2f;
		float yValue = 10f;
		
		assertEquals(gravity.getNewYPosition(yValue, velocity, delta), yValue + velocity * delta + 0.5 * gravity.getGravity() * delta * delta, 0);
	}
}
