package bobothepanda;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;
import model.ShootingEnemy;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShootingEnemyTest extends Assert {
	private ShootingEnemy shootingEnemy;
	private Size size;
	private Position position;
	private boolean eventReceived;
	
	@Before
	public void setUp() {
		size  = new Size(2f, 2f);
		position = new Position(2f, 2f);
		shootingEnemy = new ShootingEnemy(position, size);
	}
	
	@Test
	public void testFireProjectile(){
		shootingEnemy.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("ADD_PROJECTILE".equals(evt.getPropertyName())) {
			    	eventReceived = true;
			    }	
		   }
		});
		eventReceived = false;
		shootingEnemy.fireProjectile();
		assertTrue(eventReceived);
	}
	
	@Test
	public void testRender(){
		shootingEnemy.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("ENEMY_RIGHT".equals(evt.getPropertyName())) {
			    	eventReceived = true;
			    }	
		   }
		});
		eventReceived = false;
		shootingEnemy.render();
		assertTrue(eventReceived);
	}
	
	
}
