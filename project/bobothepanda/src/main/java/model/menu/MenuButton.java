package model.menu;

import java.beans.PropertyChangeSupport;

import model.Position;
import model.Size;

/**
 * 
 * @author elvirajonsson
 *
 */

public class MenuButton extends AbstractMenuItem{
	private final String name;
	private final PropertyChangeSupport pcs;
	
	public MenuButton(Position position, Size size, String name){
		super(position,size);
		this.name = name;
		pcs = new PropertyChangeSupport(this);
	}
	public String getName(){
		return name;
	}
	@Override
	public void mouseClicked() {
			pcs.firePropertyChange(name, true, false);
	}
	
}
