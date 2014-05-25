package model.menu;


import model.Position;
import model.Size;
 /**
  * A class for menuitem without action handling
  * @author elvirajonsson
  *
  */
public class MenuItem extends AbstractMenuItem {
	
	
	public MenuItem(Position pos, Size size, String type){
		super(pos,size,type);
	}

	@Override
	public void update() {

	}

}
