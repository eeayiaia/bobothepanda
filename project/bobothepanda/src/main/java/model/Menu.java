package model;

import java.util.List;

public class Menu {
	private final List<AbstractMenuItem> menuItems;
	
	public Menu(List <AbstractMenuItem> menuItems){
		this.menuItems = menuItems;
	}
}
