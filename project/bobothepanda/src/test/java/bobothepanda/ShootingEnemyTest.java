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
	private boolean eventReceived;
	
	@Before
	public void setUp() {
		shootingEnemy = new ShootingEnemy(new Position(2f, 2f), new Size(2f, 2f));
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
