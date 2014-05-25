package model.menu;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import model.Position;

public class Menu {
	private final List<AbstractMenuItem> menuItems;
	private Position characterPosition;
	private List <AbstractMenuButton> menuButtons;

	
	public Menu(List <AbstractMenuItem> menuItems){
		this.menuItems = menuItems;
		menuButtons = new ArrayList<AbstractMenuButton>();
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
		final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
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
	public void setAudioOn(boolean isOn){
		for(AbstractMenuButton butt:menuButtons){
			if(butt.getClass() == AudioButton.class){
				AudioButton tmp = (AudioButton) butt;
				tmp.setAudioOn(isOn);	
			}
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
