package model;

import java.awt.Rectangle;

public abstract class AbstractMenuItem {
	private final Position position;
	private final Size size;
	private final Rectangle hitbox;
	
	public AbstractMenuItem(Position position, Size size){
		this.position = position;
		this.size = size;
		hitbox = new Rectangle((int)Math.round(position.getX()), (int)Math.round(position.getY()), 
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
	}
	public Position getPosition(){
		return position;
	}
	public Rectangle getHitbox(){
		return hitbox;
	}
	
}
