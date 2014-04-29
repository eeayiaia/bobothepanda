package controller;

import org.newdawn.slick.Input;

import model.Character;
import view.CharacterView;

public class CharacterController {
	
	private Character character;
	private long lastTimeMoved;
	private boolean facingRight = true;
	
	public CharacterController(Character character){
		
		this.character = character;
	}
	
	public void handleInput(Input i, int delta){
		handleKeyboardInput(i,delta);
	}
	private void handleKeyboardInput(Input i, int delta){
		if(i.isKeyDown(Input.KEY_LEFT) && character.getPosition().getX() > 0){
			character.moveLeft(delta);

		}else if(i.isKeyDown(Input.KEY_RIGHT) && character.getPosition().getX() < 608){
			character.moveRight(delta);
			
		}
		if(i.isKeyPressed(Input.KEY_SPACE)/* && characterModel.onGround()*/){
			character.jump(delta);
			
		}
		
		character.applyGravity(delta);
	}
	
}
