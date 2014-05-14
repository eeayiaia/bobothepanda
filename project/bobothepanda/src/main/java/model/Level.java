package model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import model.Projectile;
import model.Position;

@SuppressWarnings("PMD")
public class Level implements PropertyChangeListener{
	
	private final Character playerCharacter;
	private final PropertyChangeSupport pcs;
	private Projectile projectile;
	private final List <Terrain> blockingObjects;
	private final List <LethalEnemy> staticEnemies;
	private final List <MovingLethalEnemy> movingEnemies;
	private Key key;
	
	
	public Level(List <Terrain> blockingObjects, List <LethalEnemy> staticEnemies, List <MovingLethalEnemy> movingEnemies, Key key, Character playerCharacter){
		this.playerCharacter = playerCharacter;
		this.blockingObjects = blockingObjects;
		this.staticEnemies = staticEnemies;
		this.movingEnemies = movingEnemies;
		this.key = key;
		projectile = new Projectile(new Position(392.0f, 470.0f), new Size(4,4));
		this.pcs = new PropertyChangeSupport(this);
		
	}

	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	public void propertyChange(PropertyChangeEvent evt) {
		if("loadLevel".equals(evt.getPropertyName())){
			pcs.firePropertyChange("loadLevel", null, null);
		}else if("reloadLevel".equals(evt.getPropertyName())){
			pcs.firePropertyChange("reloadLevel", null, null);
		}
	}
	
	//TODO göra om både update och render, alternativt ta bort render
	public void update(int delta){
		projectile.update(delta);
		checkCollisions();
		//TODO set gravity etc.	
	}
	public void render(){
		pcs.firePropertyChange("drawProjectile", null, projectile.getPosition());
	}
	
	/**
	 * Loops through all objects on the map and checks if any collisions has occured
	 * then takes action accordingly.
	 */
	public void checkCollisions() {
		Collision.collision(playerCharacter.getHitbox(), key.getHitbox());
		for(LethalEnemy e: staticEnemies) {
			if(Collision.collision(playerCharacter.getHitbox(), e.getHitbox())) {
				e.doCollision(playerCharacter);
			}
		}
		for(MovingLethalEnemy e: movingEnemies) {
			if(Collision.collision(playerCharacter.getHitbox(), e.getHitbox())) {
				e.doCollision(playerCharacter);
			}
			for(Terrain o: blockingObjects) {
				if(Collision.collision(e.getHitbox(), o.getHitbox())) {
					o.doCollision(e);
				}
			}
		}
		for(Terrain o: blockingObjects) {
			if(Collision.collision(playerCharacter.getHitbox(), o.getHitbox())) {
				o.doCollision(playerCharacter);
			}
		}
	}
	
	
}
