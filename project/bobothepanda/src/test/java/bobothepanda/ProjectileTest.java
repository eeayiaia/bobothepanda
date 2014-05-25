package bobothepanda;

import java.awt.Rectangle;
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
	
	@Before
	public void setUp() throws Exception{
		projectile = new Projectile(position, size);
	}
	
	@Test
	public void testGetPosition(){
		assertEquals(position, projectile.getPosition());
	}
	
	@Test
	public void testGetSize(){
		assertEquals(size, projectile.getSize());
	}
	
	@Test
	public void testGetHitbox(){
		final Rectangle hitbox = new Rectangle((int)Math.round(position.getX()),(int)Math.round(position.getY()),
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
		assertEquals(hitbox, projectile.getHitbox());
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
