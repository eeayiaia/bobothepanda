package model.menu;

import java.awt.Point;
import java.beans.PropertyChangeListener;
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
	public void mouseClicked(Position position) {
		final Point point = new Point((int)Math.round(position.getX()), (int)Math.round(position.getY()));
		if(this.getHitbox().contains(point)){
			pcs.firePropertyChange(name, true, false);
		}
	}
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
}
