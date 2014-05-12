package model;

//import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
import model.Projectile;
import model.Position;

@SuppressWarnings("PMD")
public class Level implements PropertyChangeListener{
	
//	private final Character playerCharacter;
//	private final List <IMapObject> objectList;
	private final PropertyChangeSupport pcs;
	private Projectile projectile;
	
	
	public Level(List <IMapObject> objectList, Character playerCharacter){
//		this.playerCharacter = playerCharacter;
//		this.objectList = objectList;
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
		
		//TODO set gravity etc.	
	}
	public void render(){
		pcs.firePropertyChange("drawProjectile", null, projectile.getPosition());
	}
	
	
	public void checkCollisions() {
//		for(IMapObject o: objectList) {
//			Collision.collision(playerCharacter.getHitbox(), o.getHitbox());
//		}
	}
	
	
}
