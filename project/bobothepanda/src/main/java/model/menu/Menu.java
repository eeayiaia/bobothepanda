package model.menu;

import java.util.List;

import model.Position;
import model.menu.*;

public class Menu {
	private final List<AbstractMenuItem> menuItems;
	
	public Menu(List <AbstractMenuItem> menuItems){
		this.menuItems = menuItems;
	}
	public List <AbstractMenuItem> getMenuItems(){
		return menuItems;
	}
	public void mouseClicked(Position position){
		for(AbstractMenuItem butt:menuItems){
			if("MenuButton".equals(butt.getClass().getName())){
				butt.mouseClicked(position);
			}
			
		}
	}
}
