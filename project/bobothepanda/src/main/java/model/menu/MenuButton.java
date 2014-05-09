package model.menu;

import model.Position;
import model.Size;

/**
 * 
 * @author elvirajonsson
 *
 */

public class MenuButton extends AbstractMenuItem{
	private final String name;
	
	public MenuButton(Position position, Size size, String name){
		super(position,size);
		this.name = name;
	}
	public String getName(){
		return name;
	}
	@Override
	public void actionPerformed() {
		// TODO Auto-generated method stub
		
	}
}
