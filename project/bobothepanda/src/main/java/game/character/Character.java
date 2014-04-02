package game.character;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Character {

	protected float x;
	protected float y;
	protected Image sprite;
	
	public Character (float x, float y) throws SlickException{
		this.x = x;
		this.y = y;
		
		sprite = new Image("Textures/Bobo-03.png");
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void render(){
		sprite.draw(x,y);
	}
	public void moveLeft(int delta){
		x = x - (0.15f*delta);
	}
	public void moveRight(int delta){
		x = x + (0.15f*delta);
	}
}
