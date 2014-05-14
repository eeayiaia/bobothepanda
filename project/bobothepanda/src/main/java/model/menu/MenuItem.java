package model.menu;
/**
 * @author Elvira Jonsson
 */
import java.awt.Rectangle;

import model.Position;
import model.Size;

public class MenuItem {
	private final Position position;
	private final Rectangle hitbox;
	private final String type;
	
	public MenuItem(Position position, Size size, String type){
		this.position = position;
		hitbox = new Rectangle((int)Math.round(position.getX()), (int)Math.round(position.getY()), 
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
		this.type = type;
	}
	public Position getPosition(){
		return position;
	}
	public Rectangle getHitbox(){
		return hitbox;
	}
	public String getType(){
		return type;
	}
}
