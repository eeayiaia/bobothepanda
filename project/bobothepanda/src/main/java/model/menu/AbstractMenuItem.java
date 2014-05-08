package model.menu;
/**
 * @author Elvira Jonsson
 */
import java.awt.Rectangle;

import model.Position;
import model.Size;

public abstract class AbstractMenuItem {
	private final Position position;
	private final Rectangle hitbox;
	
	public AbstractMenuItem(Position position, Size size){
		this.position = position;
		hitbox = new Rectangle((int)Math.round(position.getX()), (int)Math.round(position.getY()), 
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
	}
	public Position getPosition(){
		return position;
	}
	public Rectangle getHitbox(){
		return hitbox;
	}
	public abstract void mouseClicked(Position pos);

	
}
