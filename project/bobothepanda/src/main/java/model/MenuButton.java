package model;
/**
 * 
 * @author elvirajonsson
 *
 */

public class MenuButton extends AbstractMenuItem{
	private String name;
	
	public MenuButton(Position position, Size size, String name){
		super(position,size);
		this.name = name;
	}
	public String getName(){
		return name;
	}
}
