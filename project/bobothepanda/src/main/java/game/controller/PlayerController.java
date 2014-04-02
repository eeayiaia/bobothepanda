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
		if(i.isKeyDown(Input.KEY_LEFT)){
			bobo.moveLeft(delta);
		}else if(i.isKeyDown(Input.KEY_RIGHT)){
			bobo.moveRight(delta);
		}
	}
}
