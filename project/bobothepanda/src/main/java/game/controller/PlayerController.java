package game.controller;

import org.newdawn.slick.Input;
import game.character.Character;

public class PlayerController {
	protected Character bobo;
	
	public PlayerController(Character bobo){
		this.bobo = bobo;
	}
	public void handleInput(Input i, int delta){
		handleKeyboardInput(i,delta);
	}
	private void handleKeyboardInput(Input i, int delta){
		if(i.isKeyDown(Input.KEY_LEFT) && bobo.getX() > 0){
			bobo.moveLeft(delta);
		}else if(i.isKeyDown(Input.KEY_RIGHT) && bobo.getX() < 608){
			bobo.moveRight(delta);
		}
	/*	if(i.isKeyDown(Input.KEY_UP)){
			bobo.jump(delta);
		}*/
	}
}
