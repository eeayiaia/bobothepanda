package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FixedEnemy extends AbstractFixedObject {
	PropertyChangeSupport pcs;
	
	public FixedEnemy(Position position, Size size) {
		super(position, size);
		pcs = new PropertyChangeSupport(this);
	}
	
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	
	
	public void render(){
		pcs.firePropertyChange("SAW_ANIMATION", null, this.getPosition());
	}
}
