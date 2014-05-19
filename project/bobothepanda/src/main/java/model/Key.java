package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 
 * @author Oscar Muhr
 *
 */

@SuppressWarnings("PMD")
public class Key extends AbstractCollectibleObject {
	PropertyChangeSupport pcs;
	
	public Key(Position position, Size size) {
		super(position, size);
		pcs = new PropertyChangeSupport(this);
		// TODO Auto-generated constructor stub
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	public void visit(Character character){
		//TODO Remove self
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	
	
	public void update(){
		pcs.firePropertyChange("KEY_ANIMATION", null, this.getPosition());
	}
}
