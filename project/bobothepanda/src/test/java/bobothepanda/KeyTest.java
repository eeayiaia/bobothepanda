package bobothepanda;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Key;
import model.Character;
import model.Position;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

public class KeyTest extends Assert{

	private Key key;
	private boolean eventReceived;
	
	@Before
	public void setUp(){
		key = new Key(new Position(1f,1f), new Size(10,10));
	}
	
	@Test
	public void testUpdate(){
		key.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("KEY_ANIMATION".equals(evt.getPropertyName())) {
			    	eventReceived = true;
			    }	
		   }
		});
		eventReceived = false;
		key.update();
		assertTrue(eventReceived);
	}
	
	@Test
	public void testAccept(){
		final Character character = new Character(new Position(10f,10f), new Size(10,10));
		key.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("KEY_PICKED_UP".equals(evt.getPropertyName())) {
			    	eventReceived = true;
			    }	
		   }
		});
		eventReceived = false;
		key.accept(character);
		assertTrue(eventReceived);
	}
	@SuppressFBWarnings
	@Test(expected = NullPointerException.class)
	public void testAcceptNull(){
		final Character character = null;
		key.accept(character);
	}
}
