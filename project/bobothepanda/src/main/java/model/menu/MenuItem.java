package model.menu;

import java.beans.PropertyChangeSupport;

import model.Position;
import model.Size;
 /**
  * A class for menuitem without action handling
  * @author elvirajonsson
  *
  */
public class MenuItem extends AbstractMenuItem {
	
	private PropertyChangeSupport pcs;
	
	public MenuItem(Position pos, Size size, String type){
		super(pos,size,type);
		pcs = new PropertyChangeSupport(this);
	}

	@Override
	public void update() {
	//	pcs.firePropertyChange(this.getPosition(), null , this.getType());

	}

}
