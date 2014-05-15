package model.menu;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import model.Position;

public class Menu {
	private final List<MenuItem> menuItems;
	private final PropertyChangeSupport pcs;
	private Position characterPosition;
	private MenuButton startButton;
	private MenuButton quitButton;
	private MenuState menuState;

	
	public Menu(List <MenuItem> menuItems){
		this.menuItems = menuItems;
		pcs = new PropertyChangeSupport(this);
		for(MenuItem butt:menuItems){
			if("Start".equals(butt.getType())){
				startButton = (MenuButton) butt;
			} else if("Quit".equals(butt.getType())){
				quitButton = (MenuButton) butt;
			} else {
				characterPosition = butt.getPosition();
			}
		}
		menuState = MenuState.UPDATE;
	}
	public void setMenuState(Position cursorPos){
		if(mouseOverQuit(cursorPos)){
			menuState = MenuState.CURSOR_ON_QUIT;
		} else if (mouseOverStart(cursorPos)){
			menuState = MenuState.CURSOR_ON_START;
		} else {
			menuState = MenuState.UPDATE;
		}
	}
	public boolean mouseOverQuit(Position pos){
		final Point point = new Point((int)Math.round(pos.getX()), (int)Math.round(pos.getY()));
		if(quitButton.getHitbox().contains(point)){
			return true;
		} else {
			return false;
		}
	}
	public boolean mouseOverStart(Position pos){
		final Point point = new Point((int)Math.round(pos.getX()), (int)Math.round(pos.getY()));
		if(startButton.getHitbox().contains(point)){
			return true;
		} else {
			return false;
		}
	}
	
	public List <MenuItem> getMenuItems(){
		return menuItems;
	}
	public void setCharacterPosition(Position pos){
		characterPosition = pos;
	}
	@SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
	public void mouseClicked(Position pos){
		final Point point = new Point((int)Math.round(pos.getX()), (int)Math.round(pos.getY()));
		if(quitButton.getHitbox().contains(point)){
			pcs.firePropertyChange(MenuState.QUIT_BUTTON_CLICKED.toString(), true, false);
		} else if(startButton.getHitbox().contains(point)){
			pcs.firePropertyChange(MenuState.START_BUTTON_CLICKED.toString(), true, false);
		}
	}
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	public void update() {
		pcs.firePropertyChange(menuState.toString(), null, null);
	}
	public void startMenu() {
		pcs.firePropertyChange(MenuState.START_UP.toString(), characterPosition, "Character");
		pcs.firePropertyChange(MenuState.START_UP.toString(), startButton.getPosition(), startButton.getType());
		pcs.firePropertyChange(MenuState.START_UP.toString(), quitButton.getPosition(), quitButton.getType());
	}
	
}
