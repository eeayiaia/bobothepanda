package view.menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;
import model.menu.ButtonState;
import model.menu.MenuButton;

import org.newdawn.slick.Image;

public class ButtonView implements PropertyChangeListener{
	private final Image defaultImage;
	private final Image hoverImage;
	private final Image clickedImage;
	private final String type;
	
	public ButtonView(Image defaultImage, Image hoverImage, Image clickedImage, String type){
		this.defaultImage = defaultImage;
		this. hoverImage = hoverImage;
		this.clickedImage = clickedImage;
		this.type = type;
		
	}

	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getNewValue().equals(type) && evt.getSource().getClass() == MenuButton.class){
			String source = evt.getPropertyName();
			final Position cursorPos = (Position) evt.getOldValue();
			final float x = cursorPos.getX();
			final float y = cursorPos.getY();
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
}
