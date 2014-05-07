package model;

import java.awt.Rectangle;

public interface IMenuItem {
	
	Position getPosition();
	
	Rectangle getHitbox();
	
	MenuObjectType getMenuObjectType();
	
	void setMenuObjectType(MenuObjectType type);
	
}
