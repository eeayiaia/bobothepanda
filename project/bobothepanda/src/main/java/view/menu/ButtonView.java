package view.menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;
import model.menu.ButtonState;

import org.newdawn.slick.Image;

public class ButtonView implements PropertyChangeListener{
	private final Image defaultImage;
	private final Image hoverImage;
	private final Image clickedImage;
	private final float x;
	private final float y;
	
	public ButtonView(Image defaultImage, Image hoverImage, Image clickedImage, Position position){
		this.defaultImage = defaultImage;
		this. hoverImage = hoverImage;
		this.clickedImage = clickedImage;
		x = position.getX();
		y = position.getY();
		
	}

	public void propertyChange(PropertyChangeEvent evt) {
		String source = evt.getPropertyName();
		switch(ButtonState.valueOf(source)){
		case CLICKED:
			clickedImage.draw(x,y);
			break;
		case DEFAULT:
			defaultImage.draw(x,y);
			break;
		case HOVER:
			hoverImage.draw(x,y);
			break;
		default:
			break;
		
		}
		
	}
}
