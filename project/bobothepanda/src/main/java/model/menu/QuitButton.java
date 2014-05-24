package model.menu;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import model.Position;
import model.Size;

public class QuitButton extends AbstractMenuButton {
	private MenuState state;
	private final PropertyChangeSupport pcs;
	
	public QuitButton(Position pos, Size size, String type){
		super(pos, size, type);
		state = MenuState.QUIT_DEFAULT;
		pcs = new PropertyChangeSupport(this);
	}
	
	@Override
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public boolean cursorLegal(int x, int y){
		if(this.getHitbox().contains(new Point(x,y))){
			return true;
		}
		return false;
	}
	
	@Override
	public void mouseReleased(int x, int y){
		if(cursorLegal(x,y)){
			state = MenuState.QUIT_BUTTON_RELEASED;
		} else {
			state = MenuState.QUIT_DEFAULT;
		}
	}
	
	@Override
	public void mousePressed(int x, int y){
		if(cursorLegal(x,y)){
			state = MenuState.QUIT_BUTTON_PRESSED;
		} else {
			state = MenuState.QUIT_DEFAULT;
		}
	}
	
	@Override
	public void mouseHovered(int x, int y){
		if(cursorLegal(x,y)){
			state = MenuState.CURSOR_ON_QUIT;
		} else {
			state = MenuState.QUIT_DEFAULT;
		}
	}

	@Override
	public void update() {
		pcs.firePropertyChange(state.toString(), this.getPosition(), this.getType());
		
	}
}
