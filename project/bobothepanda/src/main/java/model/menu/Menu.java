package model.menu;

import java.awt.Point;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import model.Position;

public class Menu {
	private final List<AbstractMenuItem> menuItems;
	private final PropertyChangeSupport pcs;
	private Position characterPosition;
	private MenuButton startButton;
	private MenuButton quitButton;
	private MenuState menuState;
	private List <MenuButton> menuButtons;

	
	public Menu(List <AbstractMenuItem> menuItems){
		this.menuItems = menuItems;
		menuButtons = new ArrayList<MenuButton>();
		pcs = new PropertyChangeSupport(this);
		for(AbstractMenuItem butt:menuItems){
			if(butt.getClass() == MenuButton.class){
				if("Start".equals(butt.getType())){
					startButton = (MenuButton) butt;
				} else if("Quit".equals(butt.getType())){
					quitButton = (MenuButton) butt;
				}
				menuButtons.add((MenuButton) butt);
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
	
	public List <AbstractMenuItem> getMenuItems(){
		return menuItems;
	}
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	public MenuButton getStartButton(){
		return startButton;
	}
	public MenuButton getQuitButton(){
		return quitButton;
	}
	//Should run in game loop
	public void update() {
		//pcs.firePropertyChange(menuState.toString(), null, null);
		for(AbstractMenuItem item : menuItems){
			item.update();
		}
	}
	//sends information on where to put menu components
	public void startMenu() {
		//pcs.firePropertyChange(MenuState.START_UP.toString(), characterPosition, "Character");
		for(AbstractMenuItem butt:menuItems){
			pcs.firePropertyChange(MenuState.START_UP.toString(), butt.getPosition(), butt.getType());
		}
	}
	public boolean cursorOnButton(int x, int y){
		for(MenuButton butt:menuButtons){
			if(butt.getHitbox().contains(new Point(x,y))){
				return true;
			}
		}
		return false;
	}
	public void mousePressed(int x, int y) {
		for(MenuButton butt:menuButtons){
			if(cursorOnButton(x,y)){
				butt.clicked();
			}
		}
	}
	public void mouseReleased(int x, int y) {
		if(mouseOnQuit(x,y)){
			menuState = MenuState.QUIT_BUTTON_RELEASED;
		} else if(mouseOnStart(x,y)){
			menuState = MenuState.START_BUTTON_RELEASED;
		} else {
			menuState = MenuState.UPDATE;
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
