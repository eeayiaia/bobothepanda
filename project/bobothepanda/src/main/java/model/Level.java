package model;

//import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;
@SuppressWarnings("PMD")
public class Level implements PropertyChangeListener{
	
//	private final Character playerCharacter;
//	private final List <IMapObject> objectList;
	private final PropertyChangeSupport pcs;
	
	
	public Level(List <IMapObject> objectList, Character playerCharacter){
//		this.playerCharacter = playerCharacter;
//		this.objectList = objectList;
		this.pcs = new PropertyChangeSupport(this);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	public void propertyChange(PropertyChangeEvent evt) {
		if("loadNewLevel".equals(evt.getPropertyName())){
			System.out.println("in level");
			pcs.firePropertyChange("loadNewLevel", true, false);
		}
	}
}
