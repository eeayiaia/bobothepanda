package model.menu;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.Position;
import model.Size;

public class StartButton extends AbstractMenuButton {
	private MenuState state;
	private final PropertyChangeSupport pcs;
	
	public StartButton(Position pos, Size size, String type){
		super(pos,size,type);
		state = MenuState.START_DEFAULT;
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	@Override
	public void mouseReleased(int x, int y){
		if(cursorLegal(x,y)){
			state = MenuState.START_BUTTON_RELEASED;
		} else {
			state = MenuState.START_DEFAULT;
		}
	}
	
	@Override
	public void mousePressed(int x, int y){
		if(cursorLegal(x,y)){
			state = MenuState.START_BUTTON_PRESSED;
		} else {
			state = MenuState.START_DEFAULT;
		}
	}
	
	@Override
	public void mouseHovered(int x, int y){
		if(cursorLegal(x,y)){
			state = MenuState.CURSOR_ON_START;
		} else {
			state = MenuState.START_DEFAULT;
		}
	}
	
	public boolean cursorLegal(int x, int y){
		if(this.getHitbox().contains(new Point(x,y))){
			return true;
		}
		return false;
	}

	@Override
	public void update() {
		pcs.firePropertyChange(state.toString(), this.getPosition(), this.getType());

	}


}
