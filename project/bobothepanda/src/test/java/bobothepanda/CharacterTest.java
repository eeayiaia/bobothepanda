package bobothepanda;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Door;
import model.FixedEnemy;
import model.Gravity;
import model.Key;
import model.MovingEnemy;
import model.Projectile;
import model.Terrain;
import model.Character;
import model.Position;
import model.Size;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

//Supressed PMD warnings because the class is big from being
//a test class. 
@SuppressWarnings({"PMD.GodClass", "PMD.ExcessivePublicCount", "PMD.TooManyMethods"})
public class CharacterTest extends Assert {
	private Character character;
	private Position position = new Position(3f,3f);
	private final Size size = new Size(5f,5f);
	private final static int DELTA = 17;
	private boolean eventRecieved;
	private final static String RELOAD_LEVEL = "RELOAD_LEVEL";
	
	@Before
	public void setUp() throws Exception {
		character = new Character(position, size);
	}
	
	@Test
	public void testGetPosition() {
		assertEquals(position, character.getPosition());
	}
	
	@Test
	public void testGetSize() {
		assertEquals(size, character.getSize());
	}
	
	@Test
	public void testGetHitbox() {
		final Rectangle hitbox = new Rectangle((int)Math.round(position.getX()),(int)Math.round(position.getY()),
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
		assertEquals(hitbox, character.getHitbox());
	}
	
	@Test
	public void testSetX() {
		position.setX(5f);
		character.setX(position.getX());
		assertEquals(position.getX(), character.getPosition().getX(), 0);
	}
	
	@Test
	public void testSetY() {
		position.setY(5f);
		character.setY(position.getY());
		assertEquals(position.getY(), character.getPosition().getY(), 0);
	}
	
	@Test
	public void testSetPosition() {
		position = new Position(1f,1f);
		character.setPosition(position);
		assertEquals(position, character.getPosition());
	}
	
	//Intention is to see what happens when
	//a null object is used as parameter.
	@SuppressWarnings("PMD.NullAssignment")
	@Test(expected = NullPointerException.class)
	public void testSetPositionNull() {
		position = null;
		character.setPosition(position);
	}
	
	@Test
	public void testGetKeyPickedUpFalse() {
		assertFalse(character.isKeyPickedUp());
	}
	
	@Test
	public void testGetKeyPickedUpTrue() {
		final Key key = new Key(position, new Size(20f,20f));
		character.visit(key);
		assertTrue(character.isKeyPickedUp());
	}
	
	@Test
	public void testLevelComplete() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("LOAD_LEVEL".equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.levelComplete();
		assertTrue(eventRecieved);
	}
	
	@Test
	public void testSetYVelocity() {
		final float yVelocity = 2f;
		character.setYVelocity(yVelocity);
		assertEquals(yVelocity, character.getYVelocity(),0);
	}
	
	@Test
	public void testUpdateAfterMovementNameCorrect() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("MOVING_RIGHT".equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.moveRight(DELTA);
		character.update();
		assertTrue(eventRecieved);
	}
	
	@Test
	public void testUpdateAfterMovementValueCorrect() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if(character.getPosition().equals(evt.getNewValue())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.moveRight(DELTA);
		character.update();
		assertTrue(eventRecieved);
	}
	
	@Test
	public void testUpdateNoMovementNameCorrect() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("RIGHT".equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.update();
		assertTrue(eventRecieved);
	}
	
	@Test
	public void testUpdateNoMovementValueCorrect() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if(character.getPosition().equals(evt.getNewValue())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.update();
		assertTrue(eventRecieved);
	}

	@Test
	public void testMoveLeft() {
		character.moveLeft(DELTA); 
		assertTrue(0 > character.getXVelocity());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveLeftNegativeDelta() {
		character.moveLeft(-DELTA);
	}
	
	@Test
	public void testMoveRight() {
		character.moveRight(DELTA);
		assertTrue(0 < character.getXVelocity());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMoveRightNegativeDelta() {
		character.moveRight(-DELTA);
	}
	
	@Test
	public void testJump() {
		character.visit(new Terrain(position, size));
		character.jump(DELTA);
		assertEquals(-1.7f, character.getYVelocity(), 0.0001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testJumpNegativeDelta() {
		character.jump(-DELTA);
	}
	
	
	//Suppress FBWarnings as the method works as intended
	//and should always call character.jump()
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	@Test
	public void testJumpNotOnGround() {
		final float yVelocity = character.getYVelocity();
		character.jump(DELTA);
		assertEquals(yVelocity, character.getYVelocity(), 0.0001);
	}
	
	@Test
	public void testJumpEvent() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("jump".equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.visit(new Terrain(position, size));
		character.jump(DELTA);
		assertTrue(eventRecieved);
	}
	
	//Suppress FBWarnings as the method works as intended
	//and should always call character.applyGravity()
	@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
	@Test
	public void testApplyGravityYVelocity() {
		final Gravity gravity = new Gravity (0.01f);
		final float yVelocity = character.getYVelocity();
		final float newYVelocity = gravity.getNewVelocity(yVelocity, DELTA);
		character.applyGravity(DELTA);
		assertEquals(newYVelocity, character.getYVelocity(), 0.0001);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testApplyGravityNegativeDelta() {
		character.applyGravity(-DELTA);
	}
	
	@Test
	public void testDie() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if(RELOAD_LEVEL.equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.die();
		assertTrue(eventRecieved);
	}
	
	@Test
	public void testVisitFixedEnemy() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if(RELOAD_LEVEL.equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.visit(new FixedEnemy(position, size));
		assertTrue(eventRecieved);
	}
	
	@Test
	public void testVisitMovingEnemy() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if(RELOAD_LEVEL.equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.visit(new MovingEnemy(position, size));
		assertTrue(eventRecieved);
	}
	
	@Test
	public void testVisitProjectile() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if(RELOAD_LEVEL.equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.visit(new Projectile(position, size));
		assertTrue(eventRecieved);
	}
	
	@Test
	public void testVisitKeyEvent() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("key".equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.visit(new Key(position, size));
		assertTrue(eventRecieved);
	}
	
	@Test
	public void testVisitKeyBoolean() {
		character.visit(new Key(position, size));
		assertTrue(character.isKeyPickedUp());
	}
	
	@Test
	public void testVisitDoorKeyPickedUp() {
		character.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
			    if("LOAD_LEVEL".equals(evt.getPropertyName())) {
			    	eventRecieved = true;
			    }	
		   }
		});
		eventRecieved = false;
		character.visit(new Key(position, size));
		character.visit(new Door(position, size));
		assertTrue(eventRecieved);
	}
}
