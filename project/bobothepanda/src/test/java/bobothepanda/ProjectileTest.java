package bobothepanda;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Door;
import model.FixedEnemy;
import model.Projectile;
import model.Character;
import model.Position;
import model.Size;
import model.Terrain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class ProjectileTest extends Assert{
	
	private final Position position = new Position(3f,3f);
	private final Size size = new Size(4,4);
	private Projectile projectile;
	private boolean eventReceived;
	private final static int DELTA = 17;
	
	@Before
	public void setUp() throws Exception{
		projectile = new Projectile(position, size);
	}
	
	@Test
	public void testUpdate() {
		projectile.setPosition(new Position(10f, 10f));
		final float nextPositionX = 10f - 0.25f * DELTA;
		position.setX(nextPositionX);
		projectile.update(DELTA);
		assertEquals(position.getX(), projectile.getPosition().getX(), 0.0001);
	}
	
	@SuppressFBWarnings
	@Test
	public void testUpdateXIsZero() {
		projectile.setPosition(new Position(0f,0f));
		final float nextPositionX = position.getX() - 0.25f * DELTA;
		position.setX(nextPositionX);
		projectile.update(DELTA);
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
	
	@Test
	public void testVisitCharacter() {
		projectile.setX(25f);
		projectile.visit(new Character(position, size));
		assertEquals(position, projectile.getPosition());
	}
	
	@Test
	public void testVisitTerrain() {
		projectile.setX(25f);
		projectile.visit(new Terrain(position, size));
		assertEquals(position, projectile.getPosition());
	}
	
	@Test
	public void testVisitFixedEnemy() {
		projectile.setX(25f);
		projectile.visit(new FixedEnemy(position, size));
		assertEquals(position, projectile.getPosition());
	}
	
	@Test
	public void testVisitDoor() {
		projectile.setX(25f);
		projectile.visit(new Door(position, size));
		assertEquals(position, projectile.getPosition());
	}
}
