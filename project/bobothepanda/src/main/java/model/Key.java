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
	private PropertyChangeSupport pcs;
	
	public Key(Position position, Size size) {
		super(position, size);
		pcs = new PropertyChangeSupport(this);
		// TODO Auto-generated constructor stub
	}

	public void accept(IVisitor visitor) {
		if(visitor != null) {
			if(visitor.getClass() == Character.class) {
				pcs.firePropertyChange("KEY_PICKED_UP", null, this.getPosition());
			}
			visitor.visit(this);
		}else {
			throw new NullPointerException("null parameter in accept method");
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		if(listener != null) {
			pcs.addPropertyChangeListener(listener);
		}else {
			
		}
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	
	
	public void update(){
		pcs.firePropertyChange("KEY_ANIMATION", null, this.getPosition());
	}
	
}
