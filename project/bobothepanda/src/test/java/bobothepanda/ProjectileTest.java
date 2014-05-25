package bobothepanda;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Projectile;
import model.Position;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectileTest extends Assert{
	
	private final Position position = new Position(3f,3f);
	private final Size size = new Size(4,4);
	private Projectile projectile;
	private boolean eventReceived;
	private final int delta = 17;
	
	@Before
	public void setUp() throws Exception{
		projectile = new Projectile(position, size);
	}
	
	@Test
	public void testUpdate() {
		projectile.setPosition(new Position(10f, 10f));
		float nextPositionX = 10f - 0.25f * delta;
		position.setX(nextPositionX);
		projectile.update(delta);
		assertEquals(position.getX(), projectile.getPosition().getX(), 0.0001);
	}
	
	@Test
	public void testUpdateXIsZero() {
		projectile.setPosition(new Position(0f,0f));
		float nextPositionX = position.getX() - 0.25f * delta;
		position.setX(nextPositionX);
		projectile.update(delta);
		assertEquals(position.getX(), projectile.getPosition().getX(), 0.0001);
	}
	
	@Test
	public void testRender(){
		projectile.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("PROJECTILE".equals(evt.getPropertyName())) {
				    	eventReceived = true;
			    }	
			}
		});
		eventReceived = false;
		projectile.render();
		assertTrue(eventReceived);
	}
}
