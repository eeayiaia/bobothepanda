package model;

import java.awt.Rectangle;

public class MenuItem {
	private final Position position;
	private final Size size;
	private MenuObjectType type;
	private final Rectangle hitbox;
	
	public MenuItem(Position position, Size size, MenuObjectType type){
		this.position = position;
		this.size = size;
		this.type = type;
		hitbox = new Rectangle((int)Math.round(position.getX()), (int)Math.round(position.getY()),
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
	}
	
	public Position getPosition(){
		return position;
	}
	public Rectangle getHitbox(){
		return hitbox;
	}
	public MenuObjectType getMenuObjectType(){
		return type;
	}
	public void setMenuObjectType(MenuObjectType type){
		this.type = type;
	}
}
