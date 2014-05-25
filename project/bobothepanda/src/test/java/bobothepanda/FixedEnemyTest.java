package bobothepanda;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.FixedEnemy;
import model.Position;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FixedEnemyTest extends Assert {
	private FixedEnemy fixedEnemy;
	private boolean eventReceived;
	
	
	@Before
	public void setUp(){
		this.fixedEnemy = new FixedEnemy(new Position(1f,1f), new Size(10,10));
	}

	@Test
	public void testRender(){
		fixedEnemy.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("SAW_ANIMATION".equals(evt.getPropertyName())) {
			    	eventReceived = true;
			    }	
		   }
		});
		eventReceived = false;
		fixedEnemy.render();
		assertTrue(eventReceived);
	}
}
