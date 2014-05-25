package model.menu;

import java.beans.PropertyChangeListener;

import model.Position;
import model.Size;

/**
 * 
 * @author elvirajonsson
 *
 */

public abstract class AbstractMenuButton extends AbstractMenuItem{
	
	public AbstractMenuButton(Position position, Size size, String name){
		super(position,size, name);
	}
	abstract public void addListener(PropertyChangeListener listener);

	abstract public void mouseHovered(int x, int y);
	
	abstract public void mousePressed(int x, int y);
	
	abstract public void mouseReleased(int x, int y);

	abstract public void update();
	
	abstract public void reset();
	
}
