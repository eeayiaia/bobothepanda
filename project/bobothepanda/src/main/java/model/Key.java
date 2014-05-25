package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 
 * @author Oscar Muhr
 *
 */

public class Key extends AbstractCollectibleObject {
	private final PropertyChangeSupport pcs;
	
	public Key(Position position, Size size) {
		super(position, size);
		pcs = new PropertyChangeSupport(this);
	}
	
	//Programme should not run if parameter is null, intentional throwing of nullpointerexception
	@SuppressWarnings("PMD.AvoidThrowingNullPointerException")
	public void accept(IVisitor visitor) {
		if(visitor == null) {
			throw new NullPointerException(this.getClass().toString());
		}else {
			if(visitor.getClass() == Character.class) {
				pcs.firePropertyChange("KEY_PICKED_UP", null, this.getPosition());
			}
			visitor.visit(this);
		}
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
