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
	private MenuState menuState;
	private List <AbstractMenuButton> menuButtons;

	
	public Menu(List <AbstractMenuItem> menuItems){
		this.menuItems = menuItems;
		menuButtons = new ArrayList<AbstractMenuButton>();
		pcs = new PropertyChangeSupport(this);
		for(AbstractMenuItem butt:menuItems){
			if(butt instanceof AbstractMenuButton){
				menuButtons.add((AbstractMenuButton)butt);
			} else {
				characterPosition = butt.getPosition();
			}
		}
	}

	public List <AbstractMenuItem> getMenuItems(){
		return menuItems;
	}
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
		for(AbstractMenuButton butt:menuButtons){
			butt.addListener(listener);
		}
	}
	public Position getCharacterPosition(){
		return characterPosition;
	}
	//Should run in game loop
	public void update() {
		for(AbstractMenuItem item : menuItems){
			item.update();
		}
	}
	
	public void mousePressed(int x, int y) {
		for(AbstractMenuButton butt:menuButtons){
			butt.mousePressed(x, y);
		}
	}
	public void mouseReleased(int x, int y) {
		for(AbstractMenuButton butt:menuButtons){
			butt.mouseReleased(x, y);
		}
	}
	public void mouseMoved(int x, int y) {
		for(AbstractMenuButton butt:menuButtons){
			butt.mouseHovered(x, y);
		}
		
	}
	
}
