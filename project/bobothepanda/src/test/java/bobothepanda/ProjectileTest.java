package bobothepanda;

import java.awt.Rectangle;

import model.Projectile;
import model.Position;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectileTest extends Assert{
	
	private final Position position = new Position(3f,3f);
	private final Size size = new Size(4,4);
	private final Rectangle hitBox = new Rectangle(4,4);
	private Projectile projectile;
	
	@Before
	public void setup() throws Exception{
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
		assertEquals(hitBox, projectile.getHitbox());
	}
}
