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
	}
	
	@Test
	public void testGetGravity() {
		assertEquals(gravity.getGravity(), 0.15f, 0);
	}
	
	@Test
	public void testGetNewVelocity() {
		velocity = 0.15f;
		delta = 17;
		
		assertEquals(gravity.getNewVelocity(velocity, delta), velocity+gravity.getGravity()*delta, 0);
	}
	
}
