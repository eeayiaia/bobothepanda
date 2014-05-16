package model.menu;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.Position;
import model.Size;

/**
 * 
 * @author elvirajonsson
 *
 */

public class MenuButton extends MenuItem{
	private final PropertyChangeSupport pcs;
	
	public MenuButton(Position position, Size size, String name){
		super(position,size, name);
		pcs = new PropertyChangeSupport(this);
	}
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}

	
}
