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
	private ButtonState buttonState;
	
	public MenuButton(Position position, Size size, String name){
		super(position,size, name);
		pcs = new PropertyChangeSupport(this);
		buttonState = ButtonState.DEFAULT;
	}
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	public void hover(){
		buttonState = ButtonState.HOVER;
	}
	public void clicked(){
		buttonState = ButtonState.CLICKED;
	}
	public void setDefault(){
		buttonState = ButtonState.DEFAULT;
	}
	public void update(){
		pcs.firePropertyChange(buttonState.toString(), true, false);
	}

	
}
