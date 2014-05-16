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
	public boolean mouseOnQuit(int x, int y){
		final Point point = new Point(x,y);
		if(quitButton.getHitbox().contains(point)){
			return true;
		} else {
			return false;
		}
	}
	public boolean mouseOnStart(int x, int y){
		final Point point = new Point(x,y);
		if(startButton.getHitbox().contains(point)){
			return true;
		} else {
			return false;
		}
	}
	
	public List <MenuItem> getMenuItems(){
		return menuItems;
	}
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	//Should run in game loop
	public void update() {
		pcs.firePropertyChange(menuState.toString(), null, null);
	}
	public void startMenu() {
		pcs.firePropertyChange(MenuState.START_UP.toString(), characterPosition, "Character");
		pcs.firePropertyChange(MenuState.START_UP.toString(), startButton.getPosition(), startButton.getType());
		pcs.firePropertyChange(MenuState.START_UP.toString(), quitButton.getPosition(), quitButton.getType());
	}
	
	public void mousePressed(int x, int y) {
		if(mouseOnQuit(x,y)){
			menuState = MenuState.QUIT_BUTTON_PRESSED;
		} else if(mouseOnStart(x,y)){
			menuState = MenuState.START_BUTTON_PRESSED;
		} else {
			menuState = MenuState.UPDATE;
		}
	}
	public void mouseReleased(int x, int y) {
		if(mouseOnQuit(x,y)){
			pcs.firePropertyChange(MenuState.QUIT_BUTTON_RELEASED.toString(), true, false);
		} else if(mouseOnStart(x,y)){
			pcs.firePropertyChange(MenuState.START_BUTTON_RELEASED.toString(), true, false);
		} else {
		
		}
		
	}
	public void mouseMoved(int newX, int newY) {
		if(mouseOnQuit(newX,newY)){
			menuState = MenuState.CURSOR_ON_QUIT;
		} else if(mouseOnStart(newX,newY)){
			menuState = MenuState.CURSOR_ON_START;
		} else {
			menuState = MenuState.UPDATE;
		}
		
	}
	
}
